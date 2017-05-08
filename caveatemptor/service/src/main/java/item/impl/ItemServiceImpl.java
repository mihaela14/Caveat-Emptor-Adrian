package item.impl;

import item.ItemService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mapping.CategoryMapper;
import mapping.ItemMapper;
import mapping.UserMapper;
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

		User user = userRepository.getSingleEntityById(userId, entityManager);
		Category category = categoryRepository.getSingleEntityById(categoryId,
				entityManager);

		itemDTO.setUserDTO(UserMapper.getUserDTO(user));
		itemDTO.setCategoryDTO(CategoryMapper.getCategoryDTO(category));

		Item item = ItemMapper.getItem(itemDTO);
		itemRepository.add(item, entityManager);
	}

	@Override
	public List<Item> getItems(Long userId) throws ItemException, UserException {

		User user = userRepository.getSingleEntityById(userId, entityManager);
		NamedQueryData queryData = getQueryData(user);

		return itemRepository.getCollection(queryData, entityManager);
	}

	private NamedQueryDataImpl getQueryData(User user) {

		ItemParameters itemParameters = new ItemParameters.Builder().withUser(
				user).build();

		Map<String, Object> parameters = itemParameters.getParameters();

		return new NamedQueryDataImpl(Item.QUERY_FIND_ITEMS_BY_USER, parameters);
	}
}
