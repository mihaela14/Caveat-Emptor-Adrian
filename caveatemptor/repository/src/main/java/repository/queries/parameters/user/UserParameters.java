package repository.queries.parameters.user;

import java.util.HashMap;
import java.util.Map;

import repository.entities.User;

public class UserParameters {

	private Map<String, String> parameters;

	static class Builder {

		private String id;
		private String firstName;
		private String lastName;
		private String emailAddress;
		private String accountName;
		private String password;
		private String role;
		private String isActivated;

		public Builder withId(String id) {
			this.id = id;
			return this;
		}

		public Builder withFirstName(String firstName) {
			this.firstName = firstName;
			return this;
		}

		public Builder withLastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		public Builder withEmailAddress(String emailAddress) {
			this.emailAddress = emailAddress;
			return this;
		}

		public Builder withAccountName(String accountName) {
			this.accountName = accountName;
			return this;
		}

		public Builder withPassword(String password) {
			this.password = password;
			return this;
		}

		public Builder withRole(String role) {
			this.role = role;
			return this;
		}

		public Builder withIsActivated(String isActivated) {
			this.isActivated = isActivated;
			return this;
		}

		public UserParameters build() {
			return new UserParameters(this);
		}
	}

	public UserParameters(Builder builder) {
		this.parameters = new HashMap<>();
		addParameter(parameters, User.ID, builder.id);
		addParameter(parameters, User.FIRST_NAME, builder.firstName);
		addParameter(parameters, User.LAST_NAME, builder.lastName);
		addParameter(parameters, User.EMAIL_ADDRESS, builder.emailAddress);
		addParameter(parameters, User.ACCOUNT_NAME, builder.accountName);
		addParameter(parameters, User.PASSWORD, builder.password);
		addParameter(parameters, User.ROLE, builder.role);
		addParameter(parameters, User.IS_ACTIVATED, builder.isActivated);
	}

	private void addParameter(Map<String, String> parameters,
			String parameterName, String parameterValue) {
		if (parameterValue != null) {
			parameters.put(parameterName, parameterValue);
		}
	}

	public Map<String, String> getParameters() {
		return parameters;
	}

}
