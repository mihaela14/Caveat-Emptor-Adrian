package repository.repositories;

import java.util.Collection;

import javax.persistence.EntityManager;

import repository.entities.User;
import repository.queries.INamedQueryData;

public interface IUserRepository {

	void add(User user, EntityManager entityManager);

	void remove(User user, EntityManager entityManager);

	Collection<User> getCollection(INamedQueryData namedQueryData,
			EntityManager entityManager);

	User getSingleEntityByQueryData(INamedQueryData namedQueryData,
			EntityManager entityManager);

	User getSingleEntityById(int id, EntityManager entityManager);

	void setEntityManager(EntityManager entityManager);
}
