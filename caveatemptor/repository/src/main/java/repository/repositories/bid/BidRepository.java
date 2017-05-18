package repository.repositories.bid;

import java.util.Collection;

import javax.persistence.EntityManager;

import repository.entities.Bid;
import repository.queries.NamedQueryData;
import exceptions.BidException;

public interface BidRepository {

	void add(Bid bid, EntityManager entityManager);

	void delete(Bid bid, EntityManager entityManager);

	Collection<Bid> getCollection(NamedQueryData namedQueryData,
			EntityManager entityManager) throws BidException;

	Collection<Bid> getPaginatedCollection(NamedQueryData namedQueryData,
			EntityManager entityManager) throws BidException;

	Bid getSingleEntityByQueryData(NamedQueryData namedQueryData,
			EntityManager entityManager) throws BidException;

	Bid getSingleEntityById(Long id, EntityManager entityManager)
			throws BidException;

	void setEntityManager(EntityManager entityManager);
}
