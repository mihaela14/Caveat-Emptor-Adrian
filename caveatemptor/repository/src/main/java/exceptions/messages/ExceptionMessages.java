package exceptions.messages;

public enum ExceptionMessages {

	INVALID_PASSWORD("Received invalid password for supplied account name."),
	USER_NOT_ACTIVATED("Received account name does not correspond to an activated user account."),
	USER_NOT_FOUND("Received account name does not correspond to a registered user account."),
	REGISTRATION_NOT_FOUND("Received activation key does not correspond to a valid registration.");

	private final String details;

	ExceptionMessages(String details) {
		this.details = details;
	}

	public String getDetails() {
		return details;
	}

}
