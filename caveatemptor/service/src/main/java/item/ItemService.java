package item;

import java.util.List;

import repository.entities.Item;
import dto.ItemDTO;
import dto.ItemRow;
import exceptions.CategoryException;
import exceptions.ItemException;
import exceptions.UserException;

public interface ItemService {

	void addItem(ItemDTO itemDTO, Long userId, Long categoryId)
			throws CategoryException, UserException;

	void updateItem(ItemDTO itemDTO, Long userId, Long categoryId)
			throws ItemException, UserException, CategoryException;

	List<Item> getItems(Long userId) throws ItemException, UserException;

	List<ItemRow> getItemRows(Long userId) throws ItemException, UserException;
}
