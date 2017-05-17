package mapping;

import java.util.ArrayList;
import java.util.List;

import mapping.utils.DateParser;
import repository.entities.Item;
import dto.ItemDTO;
import dto.ItemPagination;
import dto.ItemRow;

public class ItemMapper {

	private ItemMapper() {
	}

	public static ItemDTO getItemDTO(Item item) {

		ItemDTO itemDTO = new ItemDTO();

		itemDTO.setId(item.getId());
		itemDTO.setName(item.getName());
		itemDTO.setDescription(item.getDescription());

		String openingDates[] = DateParser.getTime(item.getOpeningDate(),
				"dd/MM/yyyy", "hh:mm a");

		itemDTO.setOpeningDate(openingDates[0]);
		itemDTO.setOpeningTime(openingDates[1]);

		String closingDates[] = DateParser.getTime(item.getClosingDate(),
				"dd/MM/yyyy", "hh:mm a");

		itemDTO.setClosingDate(closingDates[0]);
		itemDTO.setClosingTime(closingDates[1]);

		itemDTO.setUserDTO(UserMapper.getUserDTO(item.getUser()));
		itemDTO.setCategoryDTO(CategoryMapper.getCategoryDTO(item.getCategory()));
		itemDTO.setInitialPrice(item.getInitialPrice());
		itemDTO.setStatus(item.getStatus());

		return itemDTO;
	}

	public static Item getItem(ItemDTO itemDTO) {

		Item item = new Item();

		item.setId(itemDTO.getId());
		item.setName(itemDTO.getName());
		item.setDescription(itemDTO.getDescription());

		item.setOpeningDate(DateParser.getTimestamp(itemDTO.getOpeningDate(),
				itemDTO.getOpeningTime(), "dd/MM/yyyy hh:mm a"));

		item.setClosingDate(DateParser.getTimestamp(itemDTO.getClosingDate(),
				itemDTO.getClosingTime(), "dd/MM/yyyy hh:mm a"));

		item.setUser(UserMapper.getUser(itemDTO.getUserDTO()));
		item.setCategory(CategoryMapper.getCategory(itemDTO.getCategoryDTO()));
		item.setInitialPrice(itemDTO.getInitialPrice());
		item.setStatus(itemDTO.getStatus());

		return item;
	}

	public static List<ItemRow> getItemRows(List<ItemDTO> itemsDTO,
			ItemPagination itemPagination) {

		List<ItemRow> itemRows = new ArrayList<>();

		Long index = itemPagination.isUsed() ? ((itemPagination.getPageId() - 1)
				* itemPagination.getMaxResults() + 1)
				: 1L;

		for (ItemDTO itemDTO : itemsDTO) {
			ItemRow row = new ItemRow();

			row.setRowId(index++);
			row.setId(itemDTO.getId());
			row.setCategoryId(itemDTO.getCategoryDTO().getId());
			row.setName(itemDTO.getName());
			row.setDescription(itemDTO.getDescription());
			row.setCategoryName(itemDTO.getCategoryDTO().getName());
			row.setInitialPrice(itemDTO.getInitialPrice());

			String openingDate = String.format("%s %s",
					itemDTO.getOpeningDate(), itemDTO.getOpeningTime());
			String closingDate = String.format("%s %s",
					itemDTO.getClosingDate(), itemDTO.getClosingTime());

			row.setOpeningDate(openingDate);
			row.setClosingDate(closingDate);

			row.setStatus(itemDTO.getStatus());

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

		String[] openingDates = itemRow.getOpeningDate().split(" ");
		String[] closingDates = itemRow.getClosingDate().split(" ");

		itemDTO.setOpeningDate(openingDates[0]);
		itemDTO.setOpeningTime(String.format("%s %s", openingDates[1],
				openingDates[2]));

		itemDTO.setClosingDate(closingDates[0]);
		itemDTO.setClosingTime(String.format("%s %s", closingDates[1],
				closingDates[2]));

		itemDTO.setStatus(itemRow.getStatus());

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
