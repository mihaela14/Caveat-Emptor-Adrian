package user.login;

import exceptions.login.InvalidPasswordException;
import exceptions.login.UserNotFoundException;

public interface ILoginService {

	boolean hasValidCredentials(String accountName, String password)
			throws UserNotFoundException, InvalidPasswordException;
}
