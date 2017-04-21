package user.login;

import exceptions.user.UserException;

public interface ILoginService {

	boolean isValidUserLoginData(String accountName, String password)
			throws UserException;
}
