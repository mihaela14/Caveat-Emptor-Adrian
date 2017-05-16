package repository.queries;

import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class QueryBuilder {

	private QueryBuilder() {
	}

	public static Query buildNamedQuery(final NamedQueryData namedQueryData,
			EntityManager entityManager) {

		final Query query = entityManager.createNamedQuery(namedQueryData
				.getNamedQuery());

		for (Entry<String, Object> entry : namedQueryData.getParameters()
				.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}

		return query;
	}

	public static Query buildNamedQueryWithPagination(
			final NamedQueryData namedQueryData, EntityManager entityManager) {

		final Query query = entityManager.createNamedQuery(namedQueryData
				.getNamedQuery());

		query.setFirstResult((int) namedQueryData.getFirstResult());

		query.setMaxResults((int) namedQueryData.getMaxResults());

		for (Entry<String, Object> entry : namedQueryData.getParameters()
				.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}

		return query;
	}

}
