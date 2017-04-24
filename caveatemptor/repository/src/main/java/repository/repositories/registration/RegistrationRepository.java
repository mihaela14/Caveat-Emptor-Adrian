package repository.repositories.registration;

import java.util.Collection;
import java.util.Map.Entry;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import repository.entities.Registration;
import repository.queries.INamedQueryData;

@Stateless
@Remote(IRegistrationRepository.class)
public class RegistrationRepository implements IRegistrationRepository {

	private EntityManager entityManager;

	public RegistrationRepository() {
	}

	@Override
	public void add(Registration registration, EntityManager entityManager) {
		initializeEntityManagerIfNull(entityManager);
		if (registration.getId() <= 0) {
			create(registration);
		} else {
			update(registration);
		}
	}

	@Override
	public void remove(Registration registration, EntityManager entityManager) {
		initializeEntityManagerIfNull(entityManager);
		this.entityManager
				.remove(entityManager.contains(registration) ? registration
						: entityManager.merge(registration));
	}

	@Override
	public Collection<Registration> getCollection(
			INamedQueryData namedQueryData, EntityManager entityManager) {
		initializeEntityManagerIfNull(entityManager);

		try {
			return buildNamedQuery(namedQueryData).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public Registration getSingleEntityByQueryData(
			INamedQueryData namedQueryData, EntityManager entityManager) {
		initializeEntityManagerIfNull(entityManager);
		try {
			return (Registration) buildNamedQuery(namedQueryData)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public Registration getSingleEntityById(int id, EntityManager entityManager) {
		initializeEntityManagerIfNull(entityManager);
		return this.entityManager.find(Registration.class, id);
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

	private void create(Registration registration) {
		entityManager.persist(registration);
	}

	private void update(Registration registration) {
		entityManager.merge(registration);
	}

	private void initializeEntityManagerIfNull(EntityManager entityManager) {
		if (this.entityManager == null) {
			setEntityManager(entityManager);
		}
	}

}
