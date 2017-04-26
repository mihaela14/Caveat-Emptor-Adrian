package repository.repositories.user.impl;

import java.util.Collection;
import java.util.Map.Entry;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import repository.entities.User;
import repository.queries.INamedQueryData;
import repository.repositories.user.IUserRepository;
import exceptions.UserException;
import exceptions.messages.ExceptionMessages;

@Stateless
@Remote(IUserRepository.class)
public class UserRepository implements IUserRepository {

	private EntityManager entityManager;

	public UserRepository() {
	}

	@Override
	public void add(User user, EntityManager entityManager) {

		setEntityManager(entityManager);

		if (user.getId() <= 0) {
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

	// TODO: add message to exception
	@Override
	public Collection<User> getCollection(INamedQueryData namedQueryData,
			EntityManager entityManager) throws UserException {

		setEntityManager(entityManager);

		try {
			return buildNamedQuery(namedQueryData).getResultList();
		} catch (PersistenceException e) {
			throw new UserException();
		}
	}

	@Override
	public User getSingleEntityByQueryData(INamedQueryData namedQueryData,
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

	private Query buildNamedQuery(final INamedQueryData namedQueryData) {

		final Query query = entityManager.createNamedQuery(namedQueryData
				.getNamedQuery());

		for (Entry<String, Object> entry : namedQueryData.getParameters()
				.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}

		return query;
	}

}
