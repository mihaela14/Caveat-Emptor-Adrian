package repository.repositories.category;

import java.util.Collection;
import java.util.Map.Entry;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import repository.entities.Category;
import repository.queries.INamedQueryData;
import exceptions.CategoryException;

@Stateless
@Remote(ICategoryRepository.class)
public class CategoryRepository implements ICategoryRepository {

	private EntityManager entityManager;

	public CategoryRepository() {
	}

	@Override
	public void add(Category category, EntityManager entityManager) {

		initializeEntityManagerIfNull(entityManager);

		if (category.getId() <= 0) {
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
	public void remove(Category category, EntityManager entityManager) {

		initializeEntityManagerIfNull(entityManager);
		this.entityManager.remove(entityManager.contains(category) ? category
				: entityManager.merge(category));
	}

	@Override
	public Collection<Category> getCollection(INamedQueryData namedQueryData,
			EntityManager entityManager) throws CategoryException {

		initializeEntityManagerIfNull(entityManager);

		try {
			return buildNamedQuery(namedQueryData).getResultList();
		} catch (PersistenceException e) {
			throw new CategoryException();
		}
	}

	@Override
	public Category getSingleEntityByQueryData(INamedQueryData namedQueryData,
			EntityManager entityManager) throws CategoryException {

		initializeEntityManagerIfNull(entityManager);

		try {
			return (Category) buildNamedQuery(namedQueryData).getSingleResult();
		} catch (PersistenceException e) {
			throw new CategoryException();
		}
	}

	@Override
	public Category getSingleEntityById(Long id, EntityManager entityManager)
			throws CategoryException {

		initializeEntityManagerIfNull(entityManager);

		try {
			return this.entityManager.find(Category.class, id);
		} catch (PersistenceException e) {
			throw new CategoryException();
		}
	}

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	private Query buildNamedQuery(final INamedQueryData namedQueryData) {

		final Query query = entityManager.createNamedQuery(namedQueryData
				.getNamedQuery());

		for (Entry<String, Object> entry : namedQueryData.getParameters()
				.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}

		return query;
	}

	private void initializeEntityManagerIfNull(EntityManager entityManager) {
		if (this.entityManager == null) {
			setEntityManager(entityManager);
		}
	}
}
