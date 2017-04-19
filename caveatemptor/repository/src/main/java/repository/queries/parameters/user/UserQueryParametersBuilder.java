package repository.queries.parameters.user;

import java.util.Map;

public class UserQueryParametersBuilder {

	private UserQueryParametersBuilder() {
	}

	public static Map<String, String> buildWithAccountName(String accountName) {
		UserParameters builder = new UserParameters.Builder().withAccountName(
				accountName).build();
		return builder.getParameters();
	}

	public static Map<String, String> buildWithRole(String role) {
		UserParameters userParameters = new UserParameters.Builder().withRole(
				role).build();
		return userParameters.getParameters();
	}

}
