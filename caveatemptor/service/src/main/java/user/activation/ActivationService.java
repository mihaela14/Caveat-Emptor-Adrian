package user.activation;

import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mapping.RegistrationMapper;
import repository.entities.Registration;
import repository.entities.User;
import repository.queries.INamedQueryData;
import repository.queries.NamedQueryData;
import repository.queries.parameters.registration.RegistrationParameters;
import repository.repositories.registration.IRegistrationRepository;
import repository.repositories.user.IUserRepository;
import constants.ErrorMessages;
import dto.RegistrationDTO;
import exceptions.registration.RegistrationException;

//TODO: general refactoring
@Stateless
@Remote(IActivationService.class)
public class ActivationService implements IActivationService {

	@PersistenceContext(unitName = "caveatemptor_pu")
	private EntityManager entityManager;

	@EJB
	private IUserRepository iUserRepository;

	@EJB
	private IRegistrationRepository iRegistrationRepository;

	@Override
	public void activate(String authorizationKey) throws RegistrationException {

		Registration registration = getRegistration(authorizationKey);
		RegistrationDTO registrationDTO = RegistrationMapper
				.getRegistrationDTO(registration);

		updateUser(registrationDTO);
		iRegistrationRepository.remove(registration, entityManager);
	}

	private void updateUser(RegistrationDTO registrationDTO) {

		User user = getUser(registrationDTO);
		activateUser(user);
	}

	private User getUser(RegistrationDTO registrationDTO) {

		int userId = registrationDTO.getUser().getId();
		return iUserRepository.getSingleEntityById(userId, entityManager);
	}

	// TODO: check if user is already activated
	private void activateUser(User user) {

		user.setActivated(true);
		iUserRepository.add(user, entityManager);
	}

	private Registration getRegistration(String activationKey)
			throws RegistrationException {

		INamedQueryData queryData = getQueryData(activationKey);

		Registration registration = iRegistrationRepository
				.getSingleEntityByQueryData(queryData, entityManager);

		if (registration == null) {
			throw new RegistrationException(
					ErrorMessages.REGISTRATION_NOT_FOUND.getDetails());
		} else {
			return registration;
		}

	}

	private NamedQueryData getQueryData(String authorizationKey) {

		RegistrationParameters registrationParameters = new RegistrationParameters.Builder()
				.withAuthorizationKey(authorizationKey).build();

		Map<String, Object> parameters = registrationParameters.getParameters();

		return new NamedQueryData(
				Registration.QUERY_FIND_REGISTRATION_WITH_ACTIVATION_KEY,
				parameters);
	}
}
