package item;

import java.util.List;

import repository.entities.Category;
import dto.ItemDTO;
import dto.ItemPagination;
import dto.ItemRow;
import exceptions.CategoryException;
import exceptions.ItemException;
import exceptions.UserException;

public interface ItemService {

	void addItem(ItemDTO itemDTO, Long userId, Long categoryId)
			throws CategoryException, UserException;

	List<ItemRow> getItemRowsByCategory(Category category,
			ItemPagination itemPagination) throws CategoryException,
			ItemException;

	List<ItemRow> getItemRowsByUser(Long userId, ItemPagination itemPagination)
			throws UserException, ItemException;

	long getRowCount(Long userId) throws UserException;
}
