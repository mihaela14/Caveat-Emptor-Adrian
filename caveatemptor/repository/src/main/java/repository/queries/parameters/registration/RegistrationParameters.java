package repository.queries.parameters.registration;

import java.util.HashMap;
import java.util.Map;

import fields.RegistrationFields;

public class RegistrationParameters {

	private Map<String, Object> parameters;

	public static class Builder {

		private Map<String, Object> parameters;

		public Builder() {
			this.parameters = new HashMap<>();
		}

		public Builder withId(Long id) {
			parameters.put(RegistrationFields.ID.getValue(), id);
			return this;
		}

		public Builder withUserId(Long userId) {
			parameters.put(RegistrationFields.USER_ID.getValue(), userId);
			return this;
		}

		public Builder withAuthorizationKey(String authorizationKey) {
			parameters.put(
					fields.RegistrationFields.AUTHORIZATION_KEY.getValue(),
					authorizationKey);
			return this;
		}

		public Builder withAuthorizationKeyExpiration(
				String authorizationKeyExpiration) {
			parameters.put(
					RegistrationFields.AUTHORIZATION_KEY_EXPIRATION.getValue(),
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
