package repository.repositories.bid.impl;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import repository.entities.Bid;
import repository.queries.NamedQueryData;
import repository.queries.QueryBuilder;
import repository.repositories.bid.BidRepository;
import exceptions.BidException;
import exceptions.messages.ExceptionMessages;

@Stateless
@Remote(BidRepository.class)
public class BidRepositoryImpl implements BidRepository {

	private EntityManager entityManager;

	public BidRepositoryImpl() {
	}

	@Override
	public void add(Bid bid, EntityManager entityManager) {

		setEntityManager(entityManager);

		if (bid.getId() == null) {
			create(bid);
		} else {
			update(bid);
		}
	}

	private void create(Bid bid) {
		entityManager.persist(bid);
	}

	private void update(Bid bid) {
		entityManager.merge(bid);
	}

	@Override
	public void delete(Bid bid, EntityManager entityManager) {

		setEntityManager(entityManager);
		this.entityManager.remove(entityManager.contains(bid) ? bid
				: entityManager.merge(bid));
	}

	@Override
	public List<Bid> getCollection(NamedQueryData namedQueryData,
			EntityManager entityManager) throws BidException {

		try {
			return QueryBuilder.buildNamedQuery(namedQueryData, entityManager)
					.getResultList();
		} catch (PersistenceException e) {
			throw new BidException();
		}
	}

	@Override
	public List<Bid> getPaginatedCollection(NamedQueryData namedQueryData,
			EntityManager entityManager) throws BidException {

		try {
			return QueryBuilder.buildNamedQueryWithPagination(namedQueryData,
					entityManager).getResultList();
		} catch (PersistenceException e) {
			throw new BidException();
		}
	}

	@Override
	public Bid getSingleEntityByQueryData(NamedQueryData namedQueryData,
			EntityManager entityManager) throws BidException {

		try {
			return (Bid) QueryBuilder.buildNamedQuery(namedQueryData,
					entityManager).getSingleResult();
		} catch (PersistenceException e) {
			throw new BidException(ExceptionMessages.BID_NOT_FOUND.getDetails());
		}
	}

	@Override
	public Bid getSingleEntityById(Long id, EntityManager entityManager)
			throws BidException {

		setEntityManager(entityManager);

		try {
			return this.entityManager.find(Bid.class, id);
		} catch (PersistenceException e) {
			throw new BidException(ExceptionMessages.BID_NOT_FOUND.getDetails());
		}
	}

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
