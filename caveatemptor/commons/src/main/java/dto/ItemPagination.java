package dto;

import java.io.Serializable;

public class ItemPagination implements Serializable {

	private static final long serialVersionUID = -2733858328719604348L;

	private long firstResult;

	private long maxResults;

	private long pageId;

	private long pageCount;

	private boolean isUsed;

	public ItemPagination() {
	}

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

	public long getPageCount() {
		return pageCount;
	}

	public void setPageCount(long pageCount) {
		this.pageCount = pageCount;
	}

	public long getPageId() {
		return pageId;
	}

	public void setPageId(long pageId) {
		this.pageId = pageId;
	}

	public boolean isUsed() {
		return isUsed;
	}

	public void setUsed(boolean isUsed) {
		this.isUsed = isUsed;
	}

}
