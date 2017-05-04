package user.login;

import exceptions.UserException;

public interface LoginService {

	void validateUserLoginData(String accountName, String password)throws UserException;
}
