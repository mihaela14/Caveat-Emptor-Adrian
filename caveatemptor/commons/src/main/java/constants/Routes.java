package constants;

public enum Routes {

	CATEGORY_REDIRECT("category?faces-redirect=true&amp;includeViewParams=true"),
	INDEX_REDIRECT("index.xhtml?faces-redirect=true"),
	LOGIN_REDIRECT("login.xhtml?faces-redirect=true"),
	ACTIVATE_ABSOLUTE("http://localhost:8080/presentation/activate.xhtml");

	private final String url;

	Routes(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

}
