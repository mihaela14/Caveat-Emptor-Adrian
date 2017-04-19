package exceptions.login;

public class InvalidPasswordException extends Exception {

	public static final String INVALID_PASSWORD_MESSAGE = "Received invalid password.";

	public InvalidPasswordException(String message) {
		super(message);
	}

}
