package repository.repositories.item.impl;

import java.util.List;
import java.util.Map.Entry;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import repository.entities.Item;
import repository.queries.NamedQueryData;
import repository.repositories.item.ItemRepository;
import exceptions.ItemException;
import exceptions.messages.ExceptionMessages;

@Stateless
@Remote(ItemRepository.class)
public class ItemRepositoryImpl implements ItemRepository {

	private EntityManager entityManager;

	public ItemRepositoryImpl() {
	}

	@Override
	public void add(Item item, EntityManager entityManager) {

		setEntityManager(entityManager);

		if (item.getId() == null) {
			create(item);
		} else {
			update(item);
		}
	}

	private void create(Item item) {
		entityManager.persist(item);
	}

	private void update(Item item) {
		entityManager.merge(item);
	}

	@Override
	public void remove(Item item, EntityManager entityManager) {

		setEntityManager(entityManager);
		this.entityManager.remove(entityManager.contains(item) ? item
				: entityManager.merge(item));
	}

	@Override
	public List<Item> getCollection(NamedQueryData namedQueryData,
			EntityManager entityManager) throws ItemException {

		setEntityManager(entityManager);

		try {
			return buildNamedQuery(namedQueryData).getResultList();
		} catch (PersistenceException e) {
			throw new ItemException();
		}
	}

	@Override
	public Item getSingleEntityByQueryData(NamedQueryData namedQueryData,
			EntityManager entityManager) throws ItemException {

		setEntityManager(entityManager);

		try {
			return (Item) buildNamedQuery(namedQueryData).getSingleResult();
		} catch (PersistenceException e) {
			throw new ItemException(
					ExceptionMessages.ITEM_NOT_FOUND.getDetails());
		}
	}

	@Override
	public Item getSingleEntityById(Long id, EntityManager entityManager)
			throws ItemException {

		setEntityManager(entityManager);

		try {
			return this.entityManager.find(Item.class, id);
		} catch (PersistenceException e) {
			throw new ItemException(
					ExceptionMessages.ITEM_NOT_FOUND.getDetails());
		}
	}

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	private Query buildNamedQuery(final NamedQueryData namedQueryData) {

		final Query query = entityManager.createNamedQuery(namedQueryData
				.getNamedQuery());

		for (Entry<String, Object> entry : namedQueryData.getParameters()
				.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}

		return query;
	}
}
