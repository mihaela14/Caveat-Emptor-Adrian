package repository.queries.parameters.user;

import java.util.HashMap;
import java.util.Map;

import fields.UserFields;

public class UserParameters {

	private Map<String, Object> parameters;

	public static class Builder {

		private Map<String, Object> parameters;

		public Builder() {
			this.parameters = new HashMap<>();
		}

		public Builder withId(Long id) {
			parameters.put(UserFields.ID.getValue(), id);
			return this;
		}

		public Builder withFirstName(String firstName) {
			parameters.put(UserFields.FIRST_NAME.getValue(), firstName);
			return this;
		}

		public Builder withLastName(String lastName) {
			parameters.put(UserFields.LAST_NAME.getValue(), lastName);
			return this;
		}

		public Builder withEmailAddress(String emailAddress) {
			parameters.put(UserFields.EMAIL_ADDRESS.getValue(), emailAddress);
			return this;
		}

		public Builder withAccountName(String accountName) {
			parameters.put(UserFields.ACCOUNT_NAME.getValue(), accountName);
			return this;
		}

		public Builder withPassword(String password) {
			parameters.put(UserFields.PASSWORD.getValue(), password);
			return this;
		}

		public Builder withRole(String role) {
			parameters.put(UserFields.ROLE.getValue(), role);
			return this;
		}

		public Builder withIsActivated(Boolean isActivated) {
			parameters.put(UserFields.IS_ACTIVATED.getValue(), isActivated);
			return this;
		}

		public UserParameters build() {
			return new UserParameters(this);
		}
	}

	public UserParameters(Builder builder) {
		this.parameters = builder.parameters;
	}

	public Map<String, Object> getParameters() {
		return parameters;
	}

}
