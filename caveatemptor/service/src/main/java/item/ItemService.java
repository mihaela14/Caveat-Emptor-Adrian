package item;

import java.util.List;

import dto.ItemDTO;
import dto.ItemPagination;
import dto.ItemRow;
import exceptions.CategoryException;
import exceptions.ItemException;
import exceptions.UserException;

public interface ItemService {

	void addItem(ItemDTO itemDTO, Long userId, Long categoryId)
			throws CategoryException, UserException;

	List<ItemRow> getItemRows(Long userId, ItemPagination viewConfig)
			throws UserException, ItemException;

	long getRowCount(Long userId) throws UserException;
}
