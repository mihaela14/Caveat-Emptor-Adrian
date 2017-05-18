package beans.item;

import item.ItemService;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import mapping.ItemMapper;
import mapping.utils.DateParser;
import beans.item.utils.EditStatus;
import beans.user.UserBean;
import constants.DatePatterns;
import constants.ItemStatus;
import constants.Pagination;
import dto.ItemDTO;
import dto.ItemPagination;
import dto.ItemRow;
import dto.Page;
import exceptions.CategoryException;
import exceptions.ItemException;
import exceptions.UserException;

@ManagedBean(name = "itemBean")
@ViewScoped
public class ItemBean {

	@EJB
	private ItemService itemService;

	@ManagedProperty(value = "#{userBean}")
	private UserBean userBean;

	private Paginator paginator;

	private List<ItemRow> itemRows;

	private ItemDTO itemDTO;

	private Long categoryId;

	// TODO: handle exception
	@PostConstruct
	public void init() {

		itemDTO = new ItemDTO();

		paginator = new Paginator();

		Long loggedUserId = userBean.getId();

		try {
			long itemCount = itemService.getRowCount(loggedUserId);

			if (itemCount > 0) {
				paginator.run(itemCount,
						Pagination.MAX_RESULTS_DEFAULT.getValue(), false);

				ItemPagination itemPagination = Paginator
						.getDefaultItemPagination();

				itemPagination.setPageId(1L);
				itemPagination.setPageCount(paginator.getPageCount());
				itemPagination.setUsed(true);

				itemRows = itemService.getItemRowsByUser(loggedUserId,
						itemPagination);
			}
		} catch (UserException | ItemException e) {
		}
	}

	// TODO: handle exception
	public void addItem() {

		Long loggedUserId = userBean.getId();

		itemDTO.setStatus(getInitialStatus(itemDTO));

		try {
			itemService.addItem(itemDTO, loggedUserId, categoryId);

			long rowCount = itemService.getRowCount(loggedUserId);
			long maxResultsDefault = paginator.getCurrentMaxResults();

			long firstResult = (rowCount % maxResultsDefault == 0) ? (rowCount - maxResultsDefault)
					: (maxResultsDefault * (rowCount / maxResultsDefault));

			ItemPagination itemPagination = new ItemPagination(firstResult,
					maxResultsDefault);
			itemPagination.setPageId(Paginator.getPageCount(rowCount,
					maxResultsDefault));
			itemPagination.setUsed(true);

			itemRows = itemService.getItemRowsByUser(loggedUserId,
					itemPagination);

			paginator.run(rowCount, maxResultsDefault, true);
		} catch (UserException | CategoryException | ItemException e) {
		}
	}

	private String getInitialStatus(ItemDTO itemDTO) {

		Timestamp openingDate = DateParser.getTimestamp(
				itemDTO.getOpeningDate(), itemDTO.getOpeningTime(),
				DatePatterns.FULL.getValue());

		Timestamp closingDate = DateParser.getTimestamp(
				itemDTO.getClosingDate(), itemDTO.getClosingTime(),
				DatePatterns.FULL.getValue());

		Date date = new Date(System.currentTimeMillis());
		Timestamp currentDate = new Timestamp(date.getTime());

		if (currentDate.before(openingDate)) {
			return ItemStatus.NOT_YET_OPEN.toString();
		} else if (currentDate.before(closingDate)
				&& currentDate.after(openingDate)) {
			return ItemStatus.OPEN.toString();
		} else if (currentDate.after(closingDate)) {
			return ItemStatus.CLOSED.toString();
		} else {
			return null;
		}
	}

	// TODO: handle exception
	public void editRow(int id) {

		int rowId = getRowId(id);
		boolean canEditRow = canEditRow(id);

		if (canEditRow) {
			ItemRow itemRow = itemRows.get(rowId - 1);
			ItemDTO itemDTO = ItemMapper.getItemDTO(itemRow);

			Long loggedUserId = userBean.getId();
			Long categoryId = itemRow.getCategoryId();

			try {
				itemService.addItem(itemDTO, loggedUserId, categoryId);
			} catch (UserException | CategoryException e) {
			}
		}

		itemRows.get(rowId - 1).setCanEdit(!canEditRow);
	}

	private int getRowId(long id) {

		return (int) (id - paginator.getCurrentMaxResults()
				* (paginator.getCurrentPageId() - 1));
	}

	public boolean canEditRow(long id) {

		return itemRows.get(getRowId(id) - 1).canEdit();
	}

	public String getButtonValue(int id) {

		return canEditRow(id) ? EditStatus.UPDATE.getValue() : EditStatus.EDIT
				.getValue();
	}

	public void updateItemsPerPageCount(int count) throws UserException,
			ItemException {

		paginator.setCurrentPageId(1L);
		paginator.setCurrentMaxResults(count);

		Long pageCount = Paginator
				.getPageCount(paginator.getItemCount(), count);
		paginator.setPageCount(pageCount);

		List<Page> pages = paginator.getPages(pageCount);
		paginator.setPages(pages);

		Page firstPage = new Page(paginator.getCurrentPageId());
		paginator.setPagination(firstPage, pageCount);
		paginator.setPaginationStyleClass(firstPage);

		ItemPagination itemPagination = new ItemPagination(
				Pagination.FIRST_RESULT_DEFAULT.getValue(), count);
		itemPagination.setUsed(false);

		List<ItemRow> itemRows = itemService.getItemRowsByUser(
				userBean.getId(), itemPagination);
		setItemRows(itemRows);
	}

	private void paginate(Long pageId) throws UserException, ItemException {

		Long currentMaxResults = paginator.getCurrentMaxResults();

		ItemPagination itemPagination = new ItemPagination((pageId - 1)
				* currentMaxResults, currentMaxResults);

		itemPagination.setPageId(paginator.getCurrentPageId());
		itemPagination.setPageCount(paginator.getPageCount());
		itemPagination.setUsed(true);

		List<ItemRow> itemRows = itemService.getItemRowsByUser(
				userBean.getId(), itemPagination);
		setItemRows(itemRows);

		Page page = paginator.getPages().get((int) (pageId - 1));
		paginator.setPagination(page, paginator.getPageCount());
		paginator.setPaginationStyleClass(page);
	}

	public void getPage(Page page) throws UserException, ItemException {

		paginator.setCurrentPageId(page.getId());
		paginate(page.getId());
	}

	public void getNextPage() throws UserException, ItemException {

		paginator.incrementPageId();
		paginate(paginator.getCurrentPageId());
	}

	public void getPreviousPage() throws UserException, ItemException {

		paginator.decrementPageId();
		paginate(paginator.getCurrentPageId());
	}

	// TODO: validate else
	public void validateNumber(FacesContext context, UIComponent component,
			Object value) {

		if (value == null) {
			throw new ValidatorException(new FacesMessage("Empty field."));
		} else {
		}
	}

	// TODO: validate else
	public void validateString(FacesContext context, UIComponent component,
			Object value) {

		String fieldValue = (String) value;
		if (fieldValue.isEmpty()) {
			throw new ValidatorException(new FacesMessage("Empty field."));
		} else {
		}
	}

	public ItemDTO getItemDTO() {
		return itemDTO;
	}

	public void setItemDTO(ItemDTO itemDTO) {
		this.itemDTO = itemDTO;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	public List<ItemRow> getItemRows() {
		return itemRows;
	}

	public void setItemRows(List<ItemRow> itemRows) {
		this.itemRows = itemRows;
	}

	public Paginator getPaginator() {
		return paginator;
	}

	public void setPaginator(Paginator paginator) {
		this.paginator = paginator;
	}

}
