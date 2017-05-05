package beans.item;

import item.ItemService;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import mapping.ItemMapper;
import mapping.ItemRow;
import repository.entities.Item;
import beans.UserBean;

import com.google.gson.Gson;

import constants.Routes;
import dto.ItemDTO;
import exceptions.CategoryException;
import exceptions.ItemException;
import exceptions.UserException;

@ManagedBean(name = "itemBean")
@RequestScoped
public class ItemBean {

	@EJB
	private ItemService itemService;

	@ManagedProperty(value = "#{userBean}")
	private UserBean userBean;

	private List<Item> items;

	private ItemDTO itemDTO;

	private Long categoryId;

	private String itemsJSON;

	public ItemBean() {
	}

	@PostConstruct
	public void init() {

		Gson gson = new Gson();
		itemDTO = new ItemDTO();

		try {
			items = itemService.getItems(userBean.getId());
			List<ItemDTO> itemsDTO = new ArrayList<>();

			for (Item item : items) {
				ItemDTO itemDTO = ItemMapper.getItemDTO(item);
				itemsDTO.add(itemDTO);
			}

			List<ItemRow> itemRows = ItemMapper.getItemRows(itemsDTO);

			itemsJSON = gson.toJson(itemRows);
		} catch (UserException | ItemException e) {
		}
	}

	public String addItem() {

		Long userId = userBean.getId();

		try {
			itemService.addItem(itemDTO, userId, categoryId);
			return Routes.ITEM_REDIRECT.getUrl();
		} catch (UserException | CategoryException e) {
			return null;
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

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public String getItemsJSON() {
		return itemsJSON;
	}

	public void setItemsJSON(String itemsJSON) {
		this.itemsJSON = itemsJSON;
	}
}
