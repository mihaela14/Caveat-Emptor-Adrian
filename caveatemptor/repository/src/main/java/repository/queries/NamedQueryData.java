package repository.queries;

import java.util.Map;

public interface NamedQueryData {

	Map<String, Object> getParameters();

	String getNamedQuery();

	long getFirstResult();

	long getMaxResults();
}
