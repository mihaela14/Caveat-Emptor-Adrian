package repository.queries;

import java.util.Map;

public interface INamedQueryData {

	Map<String, Object> getParameters();

	String getNamedQuery();
}
