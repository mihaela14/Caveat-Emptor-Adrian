package exceptions;

public class UserException extends Exception {

	private static final long serialVersionUID = -7047007717611076130L;

	public UserException() {
	}

	public UserException(String message) {
		super(message);
	}

}
