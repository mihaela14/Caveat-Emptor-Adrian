package mapping;

import java.util.ArrayList;
import java.util.List;

import repository.entities.Item;
import dto.ItemDTO;

public class ItemMapper {

	private ItemMapper() {
	}

	public static ItemDTO getItemDTO(Item item) {

		ItemDTO itemDTO = new ItemDTO();

		itemDTO.setId(item.getId());
		itemDTO.setName(item.getName());
		itemDTO.setUserDTO(UserMapper.getUserDTO(item.getUser()));
		itemDTO.setCategoryDTO(CategoryMapper.getCategoryDTO(item.getCategory()));
		itemDTO.setInitialPrice(item.getInitialPrice());

		return itemDTO;
	}

	public static Item getItem(ItemDTO itemDTO) {

		Item item = new Item();

		item.setId(itemDTO.getId());
		item.setName(itemDTO.getName());
		item.setUser(UserMapper.getUser(itemDTO.getUserDTO()));
		item.setCategory(CategoryMapper.getCategory(itemDTO.getCategoryDTO()));
		item.setInitialPrice(itemDTO.getInitialPrice());

		return item;
	}

	public static List<ItemRow> getItemRows(List<ItemDTO> itemsDTO) {

		List<ItemRow> itemRows = new ArrayList<>();

		for (ItemDTO itemDTO : itemsDTO) {
			ItemRow row = new ItemRow();

			row.setId(itemDTO.getId());
			row.setName(itemDTO.getName());
			row.setCategoryName(itemDTO.getCategoryDTO().getName());
			row.setInitialPrice(itemDTO.getInitialPrice());

			itemRows.add(row);
		}

		return itemRows;
	}

	public static List<ItemDTO> getItemDTOs(List<Item> items) {

		List<ItemDTO> itemDTOs = new ArrayList<>();

		for (Item item : items) {
			ItemDTO itemDTO = ItemMapper.getItemDTO(item);
			itemDTOs.add(itemDTO);
		}

		return itemDTOs;
	}
}
