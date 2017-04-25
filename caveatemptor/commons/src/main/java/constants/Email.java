package constants;

public enum Email {

	KEY("&key="),
	USERNAME("username"),
	PASSWORD("password"),
	SCOPE("?scope=activation"),
	SUBJECT("Account activation"),
	TEXT("Click on %s to activate your account. The link will expire in 24 hours.");

	private final String value;

	Email(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
