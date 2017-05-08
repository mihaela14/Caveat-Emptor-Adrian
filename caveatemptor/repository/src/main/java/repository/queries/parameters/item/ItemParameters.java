package repository.queries.parameters.item;

import java.util.HashMap;
import java.util.Map;

import repository.entities.User;
import fields.ItemFields;

public class ItemParameters {

	private Map<String, Object> parameters;

	public static class Builder {

		private Map<String, Object> parameters;

		public Builder() {
			this.parameters = new HashMap<>();
		}

		public Builder withId(Long id) {
			parameters.put(ItemFields.ID.getValue(), id);
			return this;
		}

		public Builder withName(String name) {
			parameters.put(ItemFields.NAME.getValue(), name);
			return this;
		}

		public Builder withUser(User user) {
			parameters.put(ItemFields.USER.getValue(), user);
			return this;
		}

		public ItemParameters build() {
			return new ItemParameters(this);
		}
	}

	public ItemParameters(Builder builder) {
		this.parameters = builder.parameters;
	}

	public Map<String, Object> getParameters() {
		return parameters;
	}
}
