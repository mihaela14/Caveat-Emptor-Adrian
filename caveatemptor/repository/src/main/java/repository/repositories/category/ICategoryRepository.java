package repository.repositories.category;

import java.util.Collection;

import javax.persistence.EntityManager;

import exceptions.CategoryException;
import repository.entities.Category;
import repository.queries.INamedQueryData;

public interface ICategoryRepository {

	void add(Category category, EntityManager entityManager);

	void remove(Category category, EntityManager entityManager);

	Collection<Category> getCollection(INamedQueryData namedQueryData,
			EntityManager entityManager) throws CategoryException;

	Category getSingleEntityByQueryData(INamedQueryData namedQueryData,
			EntityManager entityManager) throws CategoryException;

	Category getSingleEntityById(Long id, EntityManager entityManager)
			throws CategoryException;

	void setEntityManager(EntityManager entityManager);
}
