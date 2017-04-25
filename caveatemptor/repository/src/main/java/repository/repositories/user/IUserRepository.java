package repository.repositories.user;

import java.util.Collection;

import javax.persistence.EntityManager;

import exceptions.UserException;
import repository.entities.User;
import repository.queries.INamedQueryData;

public interface IUserRepository {

	void add(User user, EntityManager entityManager);

	void remove(User user, EntityManager entityManager);

	Collection<User> getCollection(INamedQueryData namedQueryData,
			EntityManager entityManager) throws UserException;

	User getSingleEntityByQueryData(INamedQueryData namedQueryData,
			EntityManager entityManager) throws UserException;

	User getSingleEntityById(Long id, EntityManager entityManager)
			throws UserException;

	void setEntityManager(EntityManager entityManager);
}
