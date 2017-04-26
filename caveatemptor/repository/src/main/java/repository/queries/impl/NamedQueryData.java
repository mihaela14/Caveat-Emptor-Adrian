package repository.queries.impl;

import java.io.Serializable;
import java.util.Collections;
import java.util.Map;

import repository.queries.INamedQueryData;

public class NamedQueryData implements INamedQueryData, Serializable {

	private static final long serialVersionUID = 1L;

	private final String namedQuery;

	private final Map<String, Object> parameters;

	public NamedQueryData(String namedQuery, Map<String, Object> parameters) {
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

}
