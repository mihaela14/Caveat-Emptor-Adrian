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
import repository.queries.INamedQueryData;
import repository.queries.impl.NamedQueryData;
import repository.queries.parameters.user.UserParameters;
import repository.repositories.registration.IRegistrationRepository;
import repository.repositories.user.IUserRepository;
import user.registration.IRegistrationService;
import user.registration.utils.AuthorizationData;
import user.registration.utils.EmailSender;

@Stateless
@Remote(IRegistrationService.class)
public class RegistrationService implements IRegistrationService {

	@PersistenceContext(unitName = "caveatemptor_pu")
	private EntityManager entityManager;

	@EJB
	private IUserRepository iUserRepository;

	@EJB
	private IRegistrationRepository iRegistrationRepository;

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

		INamedQueryData queryData = getQueryData(userDTO.getAccountName());

		try {
			iUserRepository
					.getSingleEntityByQueryData(queryData, entityManager);
		} catch (UserException e) {
			return false;
		}

		return true;
	}

	private NamedQueryData getQueryData(String accountName) {

		UserParameters userParameters = new UserParameters.Builder()
				.withAccountName(accountName).build();

		Map<String, Object> parameters = userParameters.getParameters();

		return new NamedQueryData(User.QUERY_FIND_USER_WITH_ACCOUNT_NAME,
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

		iRegistrationRepository.add(registration, entityManager);

		RegistrationDTO registrationDTO = RegistrationMapper
				.getRegistrationDTO(registration);
		return registrationDTO;
	}

}
