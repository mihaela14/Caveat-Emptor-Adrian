package user.registration;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mapping.RegistrationMapper;
import mapping.UserMapper;
import repository.entities.Registration;
import repository.entities.User;
import repository.repositories.registration.IRegistrationRepository;
import repository.repositories.user.IUserRepository;
import user.registration.utils.AuthorizationData;
import user.registration.utils.EmailSender;
import constants.Authorization;
import constants.Config;
import constants.Roles;
import constants.Routes;
import dto.RegistrationDTO;
import dto.UserDTO;

//TODO: general refactoring
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
	public void registerUser(UserDTO userDTO) {

		User user = createUser(userDTO);
		RegistrationDTO registration = generateRegistration(user);

		String url = Routes.ACTIVATE_ABSOLUTE.getUrl();
		String emailConfig = Config.EMAIL.getFileName();

		EmailSender.send(userDTO, registration.getAuthorizationKey(),
				emailConfig, url);
	}

	// TODO: user already registered exception
	private User createUser(UserDTO userDTO) {

		userDTO.setRole(Roles.REGULAR_USER.toString());
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
