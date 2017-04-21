package repository.queries.parameters.user;

import java.util.HashMap;
import java.util.Map;

import repository.entities.User;

public class UserParameters {

	private Map<String, Object> parameters;

	public static class Builder {

		private Map<String, Object> parameters;

		public Builder() {
			this.parameters = new HashMap<>();
		}

		public Builder withId(long id) {
			parameters.put(User.ID_FIELD, id);
			return this;
		}

		public Builder withFirstName(String firstName) {
			parameters.put(User.FIRST_NAME_FIELD, firstName);
			return this;
		}

		public Builder withLastName(String lastName) {
			parameters.put(User.LAST_NAME_FIELD, lastName);
			return this;
		}

		public Builder withEmailAddress(String emailAddress) {
			parameters.put(User.EMAIL_ADDRESS_FIELD, emailAddress);
			return this;
		}

		public Builder withAccountName(String accountName) {
			parameters.put(User.ACCOUNT_NAME_FIELD, accountName);
			return this;
		}

		public Builder withPassword(String password) {
			parameters.put(User.PASSWORD_FIELD, password);
			return this;
		}

		public Builder withRole(String role) {
			parameters.put(User.ROLE_FIELD, role);
			return this;
		}

		public Builder withIsActivated(boolean isActivated) {
			parameters.put(User.IS_ACTIVATED_FIELD, isActivated);
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
