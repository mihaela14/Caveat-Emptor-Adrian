package repository.repositories.category;

import java.util.Collection;

import javax.persistence.EntityManager;

import exceptions.CategoryException;
import repository.entities.Category;
import repository.queries.NamedQueryData;

public interface CategoryRepository {

	void add(Category category, EntityManager entityManager);

	void remove(Category category, EntityManager entityManager);

	Collection<Category> getCollection(NamedQueryData namedQueryData,
			EntityManager entityManager) throws CategoryException;

	Category getSingleEntityByQueryData(NamedQueryData namedQueryData,
			EntityManager entityManager) throws CategoryException;

	Category getSingleEntityById(Long id, EntityManager entityManager)
			throws CategoryException;

	void setEntityManager(EntityManager entityManager);
}
