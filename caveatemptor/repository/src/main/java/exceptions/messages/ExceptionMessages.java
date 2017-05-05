package exceptions.messages;

public enum ExceptionMessages {

	INVALID_PASSWORD("Received invalid password for supplied account name."),
	USER_NOT_ACTIVATED("Received account name does not correspond to an activated user account."),
	USER_NOT_FOUND("Received account name does not correspond to a registered user account."),
	USER_ALREADY_REGISTERED("Reiceved account data corresponds to an already registered user."),
	REGISTRATION_NOT_FOUND("Received activation key does not correspond to a valid registration."),
	CATEGORY_NOT_FOUND("This category does not exist."),
	ITEM_NOT_FOUND("This item does not exist.");

	private final String details;

	ExceptionMessages(String details) {
		this.details = details;
	}

	public String getDetails() {
		return details;
	}

}
