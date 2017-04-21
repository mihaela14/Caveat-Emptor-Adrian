package repository.repositories.registration;

import repository.entities.Registration;

import java.util.Collection;

import javax.persistence.EntityManager;

import repository.queries.INamedQueryData;

public interface IRegistrationRepository {

	void add(Registration registration, EntityManager entityManager);

	void remove(Registration registration, EntityManager entityManager);

	Collection<Registration> getCollection(INamedQueryData namedQueryData,
			EntityManager entityManager);

	Registration getSingleEntityByQueryData(INamedQueryData namedQueryData,
			EntityManager entityManager);

	Registration getSingleEntityById(int id, EntityManager entityManager);

	void setEntityManager(EntityManager entityManager);
}
