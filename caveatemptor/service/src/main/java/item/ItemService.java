package item;

import java.util.List;

import repository.entities.Item;
import dto.ItemDTO;
import exceptions.CategoryException;
import exceptions.ItemException;
import exceptions.UserException;

public interface ItemService {

	void addItem(ItemDTO itemDTO, Long userId, Long categoryId)
			throws CategoryException, UserException;

	List<Item> getItems(Long userId) throws ItemException, UserException;
}
