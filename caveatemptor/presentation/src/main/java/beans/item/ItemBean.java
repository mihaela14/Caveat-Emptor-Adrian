package beans.item;

import item.ItemService;

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
import beans.item.utils.EditStatus;
import beans.user.UserBean;
import dto.ItemDTO;
import dto.ItemRow;
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

	private List<ItemRow> itemRows;

	private ItemDTO itemDTO;

	private Long categoryId;

	// TODO: handle exception
	@PostConstruct
	public void init() {

		itemDTO = new ItemDTO();

		Long loggedUserId = userBean.getId();

		try {
			itemRows = itemService.getItemRows(loggedUserId);
		} catch (UserException | ItemException e) {
		}
	}

	// TODO: handle exception
	public void addItem() {

		Long loggedUserId = userBean.getId();

		try {
			itemService.addItem(itemDTO, loggedUserId, categoryId);
			itemRows = itemService.getItemRows(loggedUserId);
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

	// TODO: validate else
	public void validateNumber(FacesContext context, UIComponent component,
			Object value) {

		if (value == null) {
			throw new ValidatorException(new FacesMessage("Null value."));
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

}
