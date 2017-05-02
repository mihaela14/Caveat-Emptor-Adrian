package constants;

public enum Email {

	KEY("&key="),
	USERNAME("username"),
	PASSWORD("password"),
	SCOPE("?scope=activation"),
	SUBJECT("Account activation"),
	TEXT("Hello. We are glad you have registered to Caveat Emptor. Please, activate your account by clicking this link %s. "
			+ "The activation link will expire after 24 hours. Best regards, the Caveat Emptor team."),
	ACTIVATE_ACCOUNT_MESSAGE("Your account has been created. Check your email to activate it.");

	private final String value;

	Email(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
