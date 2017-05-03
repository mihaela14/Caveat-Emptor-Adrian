package user.login;

import exceptions.UserException;

public interface LoginService {

	boolean isValidUserLoginData(String accountName, String password)throws UserException;
}
