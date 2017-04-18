package repository.queries;

import java.util.Map;

public interface INamedQueryData {

	void addParameter(String key, String value);

	void addParameters(Map<String, String> parameters);

	void removeParameter(String key);

	Map<String, String> getParameters();

	String getNamedQuery();
}
