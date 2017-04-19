package repository.queries;

import java.util.Map;

public interface INamedQueryData {

	Map<String, String> getParameters();

	String getNamedQuery();
}
