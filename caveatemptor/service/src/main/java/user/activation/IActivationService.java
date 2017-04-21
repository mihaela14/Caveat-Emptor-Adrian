package user.activation;

import exceptions.registration.RegistrationException;

public interface IActivationService {

	void activate(String activationKey) throws RegistrationException;
}
