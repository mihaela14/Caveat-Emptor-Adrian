package user.activation;

import exceptions.RegistrationException;
import exceptions.UserException;

public interface ActivationService {

	void activate(String activationKey) throws UserException,
			RegistrationException;
}
