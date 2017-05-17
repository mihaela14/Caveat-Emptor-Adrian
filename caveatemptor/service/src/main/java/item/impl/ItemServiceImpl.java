package item.impl;

import item.ItemService;

import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mapping.ItemMapper;
import repository.entities.Category;
import repository.entities.Item;
import repository.entities.User;
import repository.queries.NamedQueryData;
import repository.queries.impl.NamedQueryDataImpl;
import repository.queries.parameters.item.ItemParameters;
import repository.repositories.category.CategoryRepository;
import repository.repositories.item.ItemRepository;
import repository.repositories.user.UserRepository;
import dto.ItemDTO;
import dto.ItemPagination;
import dto.ItemRow;
import exceptions.CategoryException;
import exceptions.ItemException;
import exceptions.UserException;

@Stateless
@Remote(ItemService.class)
public class ItemServiceImpl implements ItemService {

	@PersistenceContext(unitName = "caveatemptor_pu")
	private EntityManager entityManager;

	@EJB
	private UserRepository userRepository;

	@EJB
	private CategoryRepository categoryRepository;

	@EJB
	private ItemRepository itemRepository;

	@Override
	public void addItem(ItemDTO itemDTO, Long userId, Long categoryId)
			throws CategoryException, UserException {

		Item item = ItemMapper.getItem(itemDTO);

		User user = userRepository.getSingleEntityById(userId, entityManager);
		Category category = categoryRepository.getSingleEntityById(categoryId,
				entityManager);

		item.setUser(user);
		item.setCategory(category);

		itemRepository.add(item, entityManager);
	}

	@Override
	public List<ItemRow> getItemRows(Long userId, ItemPagination itemPagination)
			throws UserException, ItemException {

		User user = userRepository.getSingleEntityById(userId, entityManager);

		NamedQueryData namedQueryData = getItemRowsQueryData(user,
				itemPagination);

		List<Item> items = itemRepository.getCollection(namedQueryData,
				entityManager);

		List<ItemDTO> itemDTOs = ItemMapper.getItemDTOs(items);
		List<ItemRow> itemRows = ItemMapper.getItemRows(itemDTOs, itemPagination);

		return itemRows;
	}

	@Override
	public long getRowCount(Long userId) throws UserException {

		User user = userRepository.getSingleEntityById(userId, entityManager);

		NamedQueryData namedQueryData = getRowCountQueryData(user);

		return itemRepository.getEntityCount(namedQueryData, entityManager);
	}

	private NamedQueryData getItemRowsQueryData(User user,
			ItemPagination itemPagination) {

		ItemParameters itemParameters = new ItemParameters.Builder().withUser(
				user).build();

		Map<String, Object> parameters = itemParameters.getParameters();

		NamedQueryDataImpl namedQueryData = new NamedQueryDataImpl(
				Item.QUERY_FIND_ITEMS_BY_USER, parameters);

		namedQueryData.setFirstResult(itemPagination.getFirstResult());

		namedQueryData.setMaxResults(itemPagination.getMaxResults());

		return namedQueryData;
	}

	private NamedQueryData getRowCountQueryData(User user) {

		ItemParameters itemParameters = new ItemParameters.Builder().withUser(
				user).build();

		Map<String, Object> parameters = itemParameters.getParameters();

		NamedQueryDataImpl namedQueryData = new NamedQueryDataImpl(
				Item.QUERY_GET_ITEM_COUNT_BY_USER, parameters);

		return namedQueryData;
	}

}
