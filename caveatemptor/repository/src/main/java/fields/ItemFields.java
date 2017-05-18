package fields;

public enum ItemFields {

	ID("id"),
	NAME("name"),
	USER("user"),
	CATEGORY("category");

	private final String value;

	ItemFields(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
