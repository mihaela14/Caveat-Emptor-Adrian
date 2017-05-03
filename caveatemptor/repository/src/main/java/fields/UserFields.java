package fields;

public enum UserFields {

	ID("id"),
	FIRST_NAME("firstName"),
	LAST_NAME("lastName"),
	EMAIL_ADDRESS("emailAddress"),
	ACCOUNT_NAME("accountName"),
	PASSWORD("password"),
	ROLE("role"),
	IS_ACTIVATED("isActivated");

	private final String value;

	UserFields(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
