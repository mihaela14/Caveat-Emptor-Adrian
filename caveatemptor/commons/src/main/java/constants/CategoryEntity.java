package constants;

public enum CategoryEntity {

	ROOT_ID(1L);

	private final Long value;

	CategoryEntity(Long value) {
		this.value = value;
	}

	public Long getValue() {
		return value;
	}
}
