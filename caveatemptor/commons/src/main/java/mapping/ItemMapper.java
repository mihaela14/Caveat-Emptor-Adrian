package mapping;

import java.util.ArrayList;
import java.util.List;

import repository.entities.Item;
import dto.ItemDTO;
import dto.ItemRow;

public class ItemMapper {

	private ItemMapper() {
	}

	public static ItemDTO getItemDTO(Item item) {

		ItemDTO itemDTO = new ItemDTO();

		itemDTO.setId(item.getId());
		itemDTO.setName(item.getName());
		itemDTO.setDescription(item.getDescription());
		itemDTO.setOpeningDate(item.getOpeningDate());
		itemDTO.setClosingDate(item.getClosingDate());
		itemDTO.setUserDTO(UserMapper.getUserDTO(item.getUser()));
		itemDTO.setCategoryDTO(CategoryMapper.getCategoryDTO(item.getCategory()));
		itemDTO.setInitialPrice(item.getInitialPrice());

		return itemDTO;
	}

	public static Item getItem(ItemDTO itemDTO) {

		Item item = new Item();

		item.setId(itemDTO.getId());
		item.setName(itemDTO.getName());
		item.setDescription(itemDTO.getDescription());
		item.setOpeningDate(itemDTO.getOpeningDate());
		item.setClosingDate(itemDTO.getClosingDate());
		item.setUser(UserMapper.getUser(itemDTO.getUserDTO()));
		item.setCategory(CategoryMapper.getCategory(itemDTO.getCategoryDTO()));
		item.setInitialPrice(itemDTO.getInitialPrice());

		return item;
	}

	public static List<ItemRow> getItemRows(List<ItemDTO> itemsDTO) {

		List<ItemRow> itemRows = new ArrayList<>();

		Long index = 1L;
		for (ItemDTO itemDTO : itemsDTO) {
			ItemRow row = new ItemRow();

			row.setId(index++);
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
