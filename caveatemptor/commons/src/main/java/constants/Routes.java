package constants;

public enum Routes {

	CATEGORY_REDIRECT("category.xhtml?faces-redirect=true"),
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
