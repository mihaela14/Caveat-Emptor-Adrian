package dto;

import java.io.Serializable;

public class ItemPagination implements Serializable {

	private static final long serialVersionUID = -2733858328719604348L;

	private long firstResult;

	private long maxResults;

	public ItemPagination(long firstResult, long maxResults) {
		this.maxResults = maxResults;
		this.firstResult = firstResult;
	}

	public long getFirstResult() {
		return firstResult;
	}

	public void setFirstResult(long firstResult) {
		this.firstResult = firstResult;
	}

	public long getMaxResults() {
		return maxResults;
	}

	public void setMaxResults(long maxResults) {
		this.maxResults = maxResults;
	}

}
