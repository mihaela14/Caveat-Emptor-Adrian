package user.login;

import exceptions.UserException;

public interface LoginService {

	Long validateUserLoginData(String accountName, String password)throws UserException;
}
