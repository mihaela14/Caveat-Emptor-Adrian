package repository.queries.impl;

import java.io.Serializable;
import java.util.Collections;
import java.util.Map;

import repository.queries.NamedQueryData;

public class NamedQueryDataImpl implements NamedQueryData, Serializable {

	private static final long serialVersionUID = 1L;

	private final String namedQuery;

	private final Map<String, Object> parameters;

	private long maxResults;

	private long firstResult;

	public NamedQueryDataImpl(String namedQuery, Map<String, Object> parameters) {
		this.namedQuery = namedQuery;
		this.parameters = parameters;
	}

	@Override
	public Map<String, Object> getParameters() {
		return Collections.unmodifiableMap(parameters);
	}

	@Override
	public String getNamedQuery() {
		return namedQuery;
	}

	public long getMaxResults() {
		return maxResults;
	}

	public void setMaxResults(long maxResults) {
		this.maxResults = maxResults;
	}

	public long getFirstResult() {
		return firstResult;
	}

	public void setFirstResult(long firstResult) {
		this.firstResult = firstResult;
	}

}
