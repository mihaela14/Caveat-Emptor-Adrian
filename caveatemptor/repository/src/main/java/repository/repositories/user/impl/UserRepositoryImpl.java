package repository.repositories.user.impl;

import java.util.Collection;
import java.util.Map.Entry;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import repository.entities.User;
import repository.queries.NamedQueryData;
import repository.repositories.user.UserRepository;
import exceptions.UserException;
import exceptions.messages.ExceptionMessages;

@Stateless
@Remote(UserRepository.class)
public class UserRepositoryImpl implements UserRepository {

	private EntityManager entityManager;

	public UserRepositoryImpl() {
	}

	@Override
	public void add(User user, EntityManager entityManager) {

		setEntityManager(entityManager);

		if (user.getId() == null) {
			create(user);
		} else {
			update(user);
		}
	}

	private void create(User user) {
		entityManager.persist(user);
	}

	private void update(User user) {
		entityManager.merge(user);
	}

	@Override
	public void remove(User user, EntityManager entityManager) {

		setEntityManager(entityManager);
		this.entityManager.remove(entityManager.contains(user) ? user
				: entityManager.merge(user));
	}

	@Override
	public Collection<User> getCollection(NamedQueryData namedQueryData,
			EntityManager entityManager) throws UserException {

		setEntityManager(entityManager);

		try {
			return buildNamedQuery(namedQueryData).getResultList();
		} catch (PersistenceException e) {
			throw new UserException();
		}
	}

	@Override
	public User getSingleEntityByQueryData(NamedQueryData namedQueryData,
			EntityManager entityManager) throws UserException {

		setEntityManager(entityManager);

		try {
			return (User) buildNamedQuery(namedQueryData).getSingleResult();
		} catch (PersistenceException e) {
			throw new UserException(
					ExceptionMessages.USER_NOT_FOUND.getDetails());
		}
	}

	@Override
	public User getSingleEntityById(Long id, EntityManager entityManager)
			throws UserException {

		setEntityManager(entityManager);

		try {
			return this.entityManager.find(User.class, id);
		} catch (PersistenceException e) {
			throw new UserException(
					ExceptionMessages.USER_NOT_FOUND.getDetails());
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
