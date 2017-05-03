package user.activation.impl;

import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mapping.RegistrationMapper;
import repository.entities.Registration;
import repository.entities.User;
import repository.queries.NamedQueryData;
import repository.queries.impl.NamedQueryDataImpl;
import repository.queries.parameters.registration.RegistrationParameters;
import repository.repositories.registration.RegistrationRepository;
import repository.repositories.user.UserRepository;
import user.activation.ActivationService;
import dto.RegistrationDTO;
import exceptions.RegistrationException;
import exceptions.UserException;

@Stateless
@Remote(ActivationService.class)
public class ActivationServiceImpl implements ActivationService {

	@PersistenceContext(unitName = "caveatemptor_pu")
	private EntityManager entityManager;

	@EJB
	private UserRepository userRepository;

	@EJB
	private RegistrationRepository registrationRepository;

	@Override
	public void activate(String authorizationKey) throws UserException,
			RegistrationException {

		Registration registration = getRegistration(authorizationKey);
		RegistrationDTO registrationDTO = RegistrationMapper
				.getRegistrationDTO(registration);

		updateUser(registrationDTO);
		
		registrationRepository.remove(registration, entityManager);
	}

	private void updateUser(RegistrationDTO registrationDTO)
			throws UserException {

		User user = getUser(registrationDTO);
		activateUser(user);
	}

	private User getUser(RegistrationDTO registrationDTO) throws UserException {

		Long userId = registrationDTO.getUser().getId();
		return userRepository.getSingleEntityById(userId, entityManager);
	}

	// TODO: check if user is already activated
	private void activateUser(User user) {

		user.setActivated(true);
		userRepository.add(user, entityManager);
	}

	private Registration getRegistration(String activationKey)
			throws RegistrationException {

		NamedQueryData queryData = getQueryData(activationKey);

		Registration registration = registrationRepository
				.getSingleEntityByQueryData(queryData, entityManager);

		return registration;
	}

	private NamedQueryDataImpl getQueryData(String authorizationKey) {

		RegistrationParameters registrationParameters = new RegistrationParameters.Builder()
				.withAuthorizationKey(authorizationKey).build();

		Map<String, Object> parameters = registrationParameters.getParameters();

		return new NamedQueryDataImpl(
				Registration.QUERY_FIND_REGISTRATION_WITH_ACTIVATION_KEY,
				parameters);
	}
}
