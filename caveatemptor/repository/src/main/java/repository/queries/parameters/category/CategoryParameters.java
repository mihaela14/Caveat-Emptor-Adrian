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
}
