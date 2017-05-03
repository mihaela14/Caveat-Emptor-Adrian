package repository.repositories.registration;

import repository.entities.Registration;

import java.util.Collection;

import javax.persistence.EntityManager;

import exceptions.RegistrationException;
import repository.queries.NamedQueryData;

public interface RegistrationRepository {

	void add(Registration registration, EntityManager entityManager);

	void remove(Registration registration, EntityManager entityManager);

	Collection<Registration> getCollection(NamedQueryData namedQueryData,
			EntityManager entityManager) throws RegistrationException;

	Registration getSingleEntityByQueryData(NamedQueryData namedQueryData,
			EntityManager entityManager) throws RegistrationException;

	Registration getSingleEntityById(Long id, EntityManager entityManager)
			throws RegistrationException;

	void setEntityManager(EntityManager entityManager);
}
