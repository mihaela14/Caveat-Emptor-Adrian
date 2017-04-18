package repository.queries.parameters.user;

import java.util.Map;

public class UserQueryParametersBuilder {

	private UserQueryParametersBuilder() {
	}

	public static Map<String, String> buildWithAccountName(String accountName) {
		UserParametersBuilder userParameters = new UserParametersBuilder.Builder().withAccountName(accountName).build();
		return userParameters.getParameters();
	}

	public static Map<String, String> buildWithRole(String role) {
		UserParametersBuilder userParameters = new UserParametersBuilder.Builder().withRole(role).build();
		return userParameters.getParameters();
	}
	
}
