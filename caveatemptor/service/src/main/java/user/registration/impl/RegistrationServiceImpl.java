package user.registration.impl;

import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import constants.Authorization;
import constants.Roles;
import dto.RegistrationDTO;
import dto.UserDTO;
import exceptions.RegistrationException;
import exceptions.UserException;
import exceptions.messages.ExceptionMessages;
import mapping.RegistrationMapper;
import mapping.UserMapper;
import repository.entities.Registration;
import repository.entities.User;
import repository.queries.NamedQueryData;
import repository.queries.impl.NamedQueryDataImpl;
import repository.queries.parameters.user.UserParameters;
import repository.repositories.registration.RegistrationRepository;
import repository.repositories.user.UserRepository;
import user.registration.RegistrationService;
import user.registration.utils.AuthorizationData;
import user.registration.utils.EmailSender;

@Stateless
@Remote(RegistrationService.class)
public class RegistrationServiceImpl implements RegistrationService {

	@PersistenceContext(unitName = "caveatemptor_pu")
	private EntityManager entityManager;

	@EJB
	private UserRepository userRepository;

	@EJB
	private RegistrationRepository registrationRepository;

	@Override
	public void registerUser(UserDTO userDTO) throws RegistrationException {

		if (isUserAlreadyRegistered(userDTO)) {
			throw new RegistrationException(
					ExceptionMessages.USER_ALREADY_REGISTERED.getDetails());
		} else {
			User user = createUser(userDTO);
			RegistrationDTO registration = generateRegistration(user);

			EmailSender.send(userDTO, registration.getAuthorizationKey());
		}
	}

	private boolean isUserAlreadyRegistered(UserDTO userDTO) {

		NamedQueryData queryData = getQueryData(userDTO.getAccountName(),
				userDTO.getEmailAddress());

		try {
			userRepository
					.getSingleEntityByQueryData(queryData, entityManager);
		} catch (UserException e) {
			return false;
		}

		return true;
	}

	private NamedQueryDataImpl getQueryData(String accountName, String emailAddress) {

		UserParameters userParameters = new UserParameters.Builder()
				.withAccountName(accountName).withEmailAddress(emailAddress)
				.build();

		Map<String, Object> parameters = userParameters.getParameters();

		return new NamedQueryDataImpl(
				User.QUERY_FIND_USER_WITH_ACCOUNT_NAME_OR_EMAIL_ADDRESS,
				parameters);
	}

	private User createUser(UserDTO userDTO) {

		userDTO.setRole(Roles.REGULAR_USER.toString());
		userDTO.setActivated(false);

		User user = UserMapper.getUser(userDTO);

		return user;
	}

	private RegistrationDTO generateRegistration(User user) {

		Registration registration = new Registration();

		registration.setUser(user);

		String authorizationKey = AuthorizationData
				.getAuthorizationKey(Authorization.KEY_SIZE.getValue());
		registration.setAuthorizationKey(authorizationKey);

		long authorizationKeyExpiration = AuthorizationData
				.getAuthorizationDate(System.currentTimeMillis());
		registration.setAuthorizationKeyExpiration(authorizationKeyExpiration);

		registrationRepository.add(registration, entityManager);

		RegistrationDTO registrationDTO = RegistrationMapper
				.getRegistrationDTO(registration);
		return registrationDTO;
	}

}
