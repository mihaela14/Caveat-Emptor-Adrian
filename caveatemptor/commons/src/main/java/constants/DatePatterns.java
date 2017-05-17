package constants;

public enum DatePatterns {

	FULL("dd/MM/yyyy hh:mm a"), DATE("dd/MM/yyyy"), TIME("hh:mm a");

	private final String value;

	DatePatterns(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
