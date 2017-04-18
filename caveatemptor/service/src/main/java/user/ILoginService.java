package user;

import dto.UserDTO;
import exceptions.login.InvalidPasswordException;
import exceptions.login.UserNotFoundException;

public interface ILoginService {

	UserDTO findUser(String accountName) throws UserNotFoundException;

	boolean hasValidPassword(UserDTO userDTO, String password)
			throws InvalidPasswordException;

	boolean hasValidCredentials(String accountName, String password)
			throws UserNotFoundException, InvalidPasswordException;
}
