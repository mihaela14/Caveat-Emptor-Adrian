package dto;

public class Page {

	private Long id;

	private Boolean canPrevious;

	private Boolean canNext;

	public Page(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean canPrevious() {
		return canPrevious;
	}

	public void setCanPrevious(Boolean canPrevious) {
		this.canPrevious = canPrevious;
	}

	public Boolean canNext() {
		return canNext;
	}

	public void setCanNext(Boolean canNext) {
		this.canNext = canNext;
	}

}
