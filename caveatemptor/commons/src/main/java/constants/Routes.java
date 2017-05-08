package constants;

public enum Routes {

	POST_REGISTER_REDIRECT("post_register?faces-redirect=true"),
	ITEM_REDIRECT("item_main?faces-redirect=true"),
	CATEGORY_REDIRECT("category?faces-redirect=true"),
	INDEX_REDIRECT("index?faces-redirect=true"),
	LOGIN_REDIRECT("login?faces-redirect=true"),
	ACTIVATE_ABSOLUTE("http://localhost:8080/presentation/activate.xhtml");

	private final String url;

	Routes(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

}
