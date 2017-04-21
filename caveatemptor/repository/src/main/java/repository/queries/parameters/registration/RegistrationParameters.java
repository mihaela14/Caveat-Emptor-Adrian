package repository.queries.parameters.registration;

import java.util.HashMap;
import java.util.Map;

import repository.entities.Registration;

public class RegistrationParameters {

	private Map<String, Object> parameters;

	public static class Builder {

		private Map<String, Object> parameters;

		public Builder() {
			this.parameters = new HashMap<>();
		}
		
		public Builder withId(long id) {
			parameters.put(Registration.ID_FIELD, id);
			return this;
		}

		public Builder withUserId(long userId) {
			parameters.put(Registration.USER_ID_FIELD, userId);
			return this;
		}

		public Builder withAuthorizationKey(String authorizationKey) {
			parameters.put(Registration.AUTHORIZATION_KEY_FIELD,
					authorizationKey);
			return this;
		}

		public Builder withAuthorizationKeyExpiration(
				String authorizationKeyExpiration) {
			parameters.put(Registration.AUTHORIZATION_KEY_EXPIRATION_FIELD,
					authorizationKeyExpiration);
			return this;
		}

		public RegistrationParameters build() {
			return new RegistrationParameters(this);
		}
	}

	public RegistrationParameters(Builder builder) {
		this.parameters = builder.parameters;
	}

	public Map<String, Object> getParameters() {
		return parameters;
	}

}
