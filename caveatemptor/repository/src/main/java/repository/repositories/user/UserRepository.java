package repository.repositories.user;

import java.util.Collection;

import javax.persistence.EntityManager;

import exceptions.UserException;
import repository.entities.User;
import repository.queries.NamedQueryData;

public interface UserRepository {

	void add(User user, EntityManager entityManager);

	void remove(User user, EntityManager entityManager);

	Collection<User> getCollection(NamedQueryData namedQueryData,
			EntityManager entityManager) throws UserException;

	User getSingleEntityByQueryData(NamedQueryData namedQueryData,
			EntityManager entityManager) throws UserException;

	User getSingleEntityById(Long id, EntityManager entityManager)
			throws UserException;

	void setEntityManager(EntityManager entityManager);
}
