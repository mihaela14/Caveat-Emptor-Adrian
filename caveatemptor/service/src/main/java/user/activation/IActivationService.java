package user.activation;

import exceptions.RegistrationException;
import exceptions.UserException;

public interface IActivationService {

	void activate(String activationKey) throws UserException,
			RegistrationException;
}
