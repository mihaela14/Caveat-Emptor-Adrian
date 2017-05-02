package constants;

public enum Forms {

	LOGIN("loginForm"),
	REGISTER("registerForm"),
	ACTIVATION_CONTAINER("activationContainer");

	private final String name;

	Forms(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
