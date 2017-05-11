package mapping;

import java.util.ArrayList;
import java.util.List;

import mapping.utils.DateParser;
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
		itemDTO.setOpeningDate(DateParser.getTime(item.getOpeningDate(),
				"dd/mm/yyyy"));
		itemDTO.setClosingDate(DateParser.getTime(item.getClosingDate(),
				"dd/mm/yyyy"));
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
		item.setOpeningDate(DateParser.getTimestamp(itemDTO.getOpeningDate(),
				"dd/mm/yyyy"));
		item.setClosingDate(DateParser.getTimestamp(itemDTO.getClosingDate(),
				"dd/mm/yyyy"));
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

			row.setRowId(index++);
			row.setId(itemDTO.getId());
			row.setCategoryId(itemDTO.getCategoryDTO().getId());
			row.setName(itemDTO.getName());
			row.setDescription(itemDTO.getDescription());
			row.setCategoryName(itemDTO.getCategoryDTO().getName());
			row.setInitialPrice(itemDTO.getInitialPrice());
			row.setOpeningDate(itemDTO.getOpeningDate());
			row.setClosingDate(itemDTO.getClosingDate());

			itemRows.add(row);
		}

		return itemRows;
	}

	public static ItemDTO getItemDTO(ItemRow itemRow) {

		ItemDTO itemDTO = new ItemDTO();

		itemDTO.setId(itemRow.getId());
		itemDTO.setName(itemRow.getName());
		itemDTO.setDescription(itemRow.getDescription());
		itemDTO.setInitialPrice(itemRow.getInitialPrice());
		itemDTO.setOpeningDate(itemRow.getOpeningDate());
		itemDTO.setClosingDate(itemRow.getClosingDate());

		return itemDTO;
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
