package constants;

public enum Pagination {

	FIRST_RESULT_DEFAULT(0L),
	MAX_RESULTS_DEFAULT(5L),
	MAX_RESULTS_10(10L),
	MAX_RESULTS_20(20L);

	private final Long value;

	private Pagination(Long value) {
		this.value = value;
	}

	public Long getValue() {
		return value;
	}

}
