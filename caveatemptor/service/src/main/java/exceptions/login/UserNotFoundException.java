package exceptions.login;

public class UserNotFoundException extends Exception {

	public static final String USER_NOT_FOUND_MESSAGE = "Received invalid account name: user does not exist.";

	public UserNotFoundException(String message) {
		super(message);
	}

}
