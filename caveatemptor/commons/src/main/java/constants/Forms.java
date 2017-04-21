package constants;

public enum Forms {

	LOGIN("loginForm");

	private final String name;

	Forms(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
