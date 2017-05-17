package beans.item;

import java.util.ArrayList;
import java.util.List;

import constants.Pagination;
import dto.ItemPagination;
import dto.Page;

public class Paginator {

	private List<Page> pages;

	private Long pageCount;

	private Long currentPageId;

	private Long itemCount;

	private Long currentMaxResults;

	private String previousPageStyle;

	private String nextPageStyle;

	public Paginator() {
		this.currentMaxResults = Pagination.MAX_RESULTS_DEFAULT.getValue();
	}

	public void run(long itemCount, long currentMaxResults,
			boolean isItemBeingAdded) {

		setItemCount(itemCount);
		setCurrentMaxResults(currentMaxResults);

		long pageCount = getPageCount(itemCount, currentMaxResults);
		setPageCount(pageCount);

		List<Page> pages = getPages(pageCount);
		setPages(pages);

		Long currentPageId = isItemBeingAdded ? pageCount : 1L;
		setCurrentPageId(currentPageId);

		if (isItemBeingAdded) {
			setPaginationStyleClass(pages.get(currentPageId.intValue() - 1));
		} else {
			setPaginationStyleClass(pages.get(0));
		}
	}

	public void incrementPageId() {
		this.currentPageId++;
	}

	public void decrementPageId() {
		this.currentPageId--;
	}

	public static long getPageCount(long itemCount, long maxResults) {

		return (itemCount % maxResults == 0) ? (itemCount / maxResults)
				: (itemCount / maxResults + 1);
	}

	public List<Page> getPages(long pageCount) {

		List<Page> pages = new ArrayList<>();

		for (Long i = 1L; i <= pageCount; i++) {
			Page page = new Page(i);
			setPagination(page, pageCount);

			pages.add(page);
		}

		return pages;
	}

	public void setPagination(Page page, long pageCount) {

		if (page.getId() == pageCount && pageCount == 1) {
			page.setCanPrevious(false);
			page.setCanNext(false);
		} else if (page.getId() == pageCount) {
			page.setCanPrevious(true);
			page.setCanNext(false);
		} else if (page.getId() == 1 && page.getId() < pageCount) {
			page.setCanPrevious(false);
			page.setCanNext(true);
		} else {
			page.setCanPrevious(true);
			page.setCanNext(true);
		}
	}

	public void setPaginationStyleClass(Page page) {

		nextPageStyle = page.canNext() ? "page-item" : "page-item disabled";
		previousPageStyle = page.canPrevious() ? "page-item"
				: "page-item disabled";
	}

	public static ItemPagination getDefaultItemPagination() {

		return new ItemPagination(Pagination.FIRST_RESULT_DEFAULT.getValue(),
				Pagination.MAX_RESULTS_DEFAULT.getValue());
	}

	public List<Page> getPages() {
		return pages;
	}

	public void setPages(List<Page> pages) {
		this.pages = pages;
	}

	public Long getPageCount() {
		return pageCount;
	}

	public void setPageCount(Long pageCount) {
		this.pageCount = pageCount;
	}

	public Long getCurrentPageId() {
		return currentPageId;
	}

	public void setCurrentPageId(Long currentPageId) {
		this.currentPageId = currentPageId;
	}

	public long getCurrentMaxResults() {
		return currentMaxResults;
	}

	public void setCurrentMaxResults(long currentMaxResults) {
		this.currentMaxResults = currentMaxResults;
	}

	public String getPreviousPageStyle() {
		return previousPageStyle;
	}

	public void setPreviousPageStyle(String previousPageStyle) {
		this.previousPageStyle = previousPageStyle;
	}

	public String getNextPageStyle() {
		return nextPageStyle;
	}

	public void setNextPageStyle(String nextPageStyle) {
		this.nextPageStyle = nextPageStyle;
	}

	public long getItemCount() {
		return itemCount;
	}

	public void setItemCount(long itemCount) {
		this.itemCount = itemCount;
	}

}
