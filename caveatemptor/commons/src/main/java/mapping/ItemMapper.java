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

		return itemDTO;
	}

	public static Item getItem(ItemDTO itemDTO) {

		Item item = new Item();

		item.setId(itemDTO.getId());
		item.setName(itemDTO.getName());
		item.setUser(UserMapper.getUser(itemDTO.getUserDTO()));
		item.setCategory(CategoryMapper.getCategory(itemDTO.getCategoryDTO()));

		return item;
	}

	public static List<ItemRow> getItemRows(List<ItemDTO> itemsDTO) {

		List<ItemRow> itemRows = new ArrayList<>();

		for (ItemDTO itemDTO : itemsDTO) {
			ItemRow row = new ItemRow();

			row.setId(itemDTO.getId());
			row.setName(itemDTO.getName());
			row.setCategoryName(itemDTO.getCategoryDTO().getName());

			itemRows.add(row);
		}

		return itemRows;
	}
}
