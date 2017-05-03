package fields;

public enum CategoryFields {

	ID("id"),
	NAME("name"),
	DESCRIPTION("description");

	private final String value;

	CategoryFields(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
