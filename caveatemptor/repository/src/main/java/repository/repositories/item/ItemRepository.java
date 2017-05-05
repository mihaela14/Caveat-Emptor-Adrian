package repository.repositories.item;

import java.util.List;

import javax.persistence.EntityManager;

import repository.entities.Item;
import repository.queries.NamedQueryData;
import exceptions.ItemException;

public interface ItemRepository {

	void add(Item item, EntityManager entityManager);

	void remove(Item item, EntityManager entityManager);

	List<Item> getCollection(NamedQueryData namedQueryData,
			EntityManager entityManager) throws ItemException;

	Item getSingleEntityByQueryData(NamedQueryData namedQueryData,
			EntityManager entityManager) throws ItemException;

	Item getSingleEntityById(Long id, EntityManager entityManager)
			throws ItemException;

	void setEntityManager(EntityManager entityManager);
}
