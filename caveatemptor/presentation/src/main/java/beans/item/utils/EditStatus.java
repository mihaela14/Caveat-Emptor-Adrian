package beans.item.utils;

public enum EditStatus {
	EDIT("Edit"), UPDATE("Update");

	private String value;

	EditStatus(String value) {
		this.setValue(value);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
