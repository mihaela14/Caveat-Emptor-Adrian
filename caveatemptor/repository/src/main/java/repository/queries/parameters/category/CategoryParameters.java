package repository.queries.parameters.category;

import java.util.HashMap;
import java.util.Map;

public class CategoryParameters {

	private Map<String, Object> parameters;
	
	public static class Builder {
		
		private Map<String, Object> parameters;
		
		public Builder() {
			this.parameters = new HashMap<>();
		}
	}
	
	public CategoryParameters(Builder builder) {
		this.parameters = builder.parameters;
	}

	public Map<String, Object> getParameters() {
		return parameters;
	}
}
