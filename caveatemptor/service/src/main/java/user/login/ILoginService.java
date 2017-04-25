package user.login;

import exceptions.UserException;

public interface ILoginService {

	boolean isValidUserLoginData(String accountName, String password)throws UserException;
}
