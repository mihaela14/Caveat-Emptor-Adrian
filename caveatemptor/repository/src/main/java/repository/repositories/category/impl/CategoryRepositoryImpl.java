package repository.repositories.category.impl;

import java.util.Collection;
import java.util.Map.Entry;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import repository.entities.Category;
import repository.queries.NamedQueryData;
import repository.repositories.category.CategoryRepository;
import exceptions.CategoryException;
import exceptions.messages.ExceptionMessages;

@Stateless
@Remote(CategoryRepository.class)
public class CategoryRepositoryImpl implements CategoryRepository {

	private EntityManager entityManager;

	public CategoryRepositoryImpl() {
	}

	@Override
	public void add(Category category, EntityManager entityManager) {

		setEntityManager(entityManager);

		if (category.getId() == null) {
			create(category);
		} else {
			update(category);
		}
	}

	private void create(Category category) {
		entityManager.persist(category);
	}

	private void update(Category category) {
		entityManager.merge(category);
	}

	@Override
	public void delete(Category category, EntityManager entityManager) {

		setEntityManager(entityManager);
		this.entityManager.remove(entityManager.contains(category) ? category
				: entityManager.merge(category));
	}

	@Override
	public Collection<Category> getCollection(NamedQueryData namedQueryData,
			EntityManager entityManager) throws CategoryException {

		setEntityManager(entityManager);

		try {
			return buildNamedQuery(namedQueryData).getResultList();
		} catch (PersistenceException e) {
			throw new CategoryException();
		}
	}

	@Override
	public Category getSingleEntityByQueryData(NamedQueryData namedQueryData,
			EntityManager entityManager) throws CategoryException {

		setEntityManager(entityManager);

		try {
			return (Category) buildNamedQuery(namedQueryData).getSingleResult();
		} catch (PersistenceException e) {
			throw new CategoryException(
					ExceptionMessages.CATEGORY_NOT_FOUND.getDetails());
		}
	}

	@Override
	public Category getSingleEntityById(Long id, EntityManager entityManager)
			throws CategoryException {

		setEntityManager(entityManager);

		try {
			return this.entityManager.find(Category.class, id);
		} catch (PersistenceException e) {
			throw new CategoryException(
					ExceptionMessages.CATEGORY_NOT_FOUND.getDetails());
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
