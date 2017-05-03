package fields;

public enum RegistrationFields {

	ID("id"),
	USER_ID("userId"),
	AUTHORIZATION_KEY("authorizationKey"),
	AUTHORIZATION_KEY_EXPIRATION("authorizationKeyExpiration");

	private final String value;

	RegistrationFields(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
