package repository.repositories.registration;

import repository.entities.Registration;

import java.util.Collection;

import javax.persistence.EntityManager;

import exceptions.RegistrationException;
import repository.queries.INamedQueryData;

public interface IRegistrationRepository {

	void add(Registration registration, EntityManager entityManager);

	void remove(Registration registration, EntityManager entityManager);

	Collection<Registration> getCollection(INamedQueryData namedQueryData,
			EntityManager entityManager) throws RegistrationException;

	Registration getSingleEntityByQueryData(INamedQueryData namedQueryData,
			EntityManager entityManager) throws RegistrationException;

	Registration getSingleEntityById(Long id, EntityManager entityManager)
			throws RegistrationException;

	void setEntityManager(EntityManager entityManager);
}
