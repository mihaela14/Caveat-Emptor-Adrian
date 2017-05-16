package beans.item;

import item.ItemService;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import mapping.ItemMapper;
import beans.item.utils.EditStatus;
import beans.user.UserBean;
import constants.Pagination;
import dto.ItemDTO;
import dto.ItemPagination;
import dto.ItemRow;
import dto.Page;
import exceptions.CategoryException;
import exceptions.ItemException;
import exceptions.UserException;

@ManagedBean(name = "itemBean")
@SessionScoped
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

		Long loggedUserId = userBean.getId();

		paginator = new Paginator();

		try {
			long maxResultsDefault = Pagination.MAX_RESULTS_DEFAULT.getValue();
			ItemPagination itemPagination = new ItemPagination(0,
					maxResultsDefault);

			itemRows = itemService.getItemRows(loggedUserId, itemPagination);

			long itemCount = itemService.getRowCount(loggedUserId);

			paginator.run(itemCount, maxResultsDefault);
		} catch (UserException | ItemException e) {
		}
	}

	// TODO: handle exception
	public void addItem() {

		Long loggedUserId = userBean.getId();

		try {
			itemService.addItem(itemDTO, loggedUserId, categoryId);

			long rowCount = itemService.getRowCount(loggedUserId);
			long maxResultsDefault = Pagination.MAX_RESULTS_DEFAULT.getValue();

			long firstResult = maxResultsDefault
					* (rowCount / maxResultsDefault);

			ItemPagination itemPagination = new ItemPagination(firstResult,
					maxResultsDefault);

			itemRows = itemService.getItemRows(loggedUserId, itemPagination);

			paginator.run(rowCount, maxResultsDefault);
		} catch (ItemException | UserException | CategoryException e) {
		}
	}

	// TODO: handle exception
	public void editRow(int id) {

		boolean canEditRow = canEditRow(id);

		if (canEditRow) {
			ItemRow itemRow = itemRows.get(id - 1);
			ItemDTO itemDTO = ItemMapper.getItemDTO(itemRow);

			try {
				Long loggedUserId = userBean.getId();
				Long categoryId = itemRow.getCategoryId();

				itemService.addItem(itemDTO, loggedUserId, categoryId);
			} catch (UserException | CategoryException e) {
			}
		}

		itemRows.get(id - 1).setCanEdit(!canEditRow);
	}

	public boolean canEditRow(int id) {
		return itemRows.get(id - 1).canEdit();
	}

	public String getButtonValue(int id) {

		if (canEditRow(id)) {
			return EditStatus.UPDATE.getValue();
		} else {
			return EditStatus.EDIT.getValue();
		}
	}

	public void updateItemsPerPageCount(int count) throws UserException,
			ItemException {

		paginator.setCurrentPageId(1L);
		paginator.setCurrentMaxResults(count);

		Long pageCount = paginator
				.getPageCount(paginator.getItemCount(), count);

		paginator.setPageCount(pageCount);

		List<Page> pages = paginator.getPages(pageCount);

		paginator.setPages(pages);

		Page firstPage = new Page(paginator.getCurrentPageId());
		paginator.setPagination(firstPage, pageCount);
		paginator.setPaginationStyle(firstPage);

		ItemPagination itemPagination = new ItemPagination(0, count);

		List<ItemRow> itemRows = itemService.getItemRows(getUserBean().getId(),
				itemPagination);
		setItemRows(itemRows);
	}

	private void paginate(Long pageId) throws UserException, ItemException {

		Long currentMaxResults = paginator.getCurrentMaxResults();
		ItemPagination itemPagination = new ItemPagination((pageId - 1)
				* currentMaxResults, currentMaxResults);

		List<ItemRow> itemRows = itemService.getItemRows(userBean.getId(),
				itemPagination);
		setItemRows(itemRows);

		Page page = paginator.getPages().get((int) (pageId - 1));

		paginator.setPagination(page, paginator.getPageCount());
		paginator.setPaginationStyle(page);
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

	public static ItemPagination getDefaultItemPagination() {

		return new ItemPagination(Pagination.FIRST_RESULT_DEFAULT.getValue(),
				Pagination.MAX_RESULTS_DEFAULT.getValue());
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
