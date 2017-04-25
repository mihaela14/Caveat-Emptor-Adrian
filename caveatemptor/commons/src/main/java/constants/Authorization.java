package constants;

public enum Authorization {

	KEY_SIZE(20),
	KEY_EXPIRATION(86400000);

	private final int value;

	Authorization(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

}
