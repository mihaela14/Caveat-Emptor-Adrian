package constants;

public enum Config {

	EMAIL("config_mail.properties");

	private final String fileName;

	Config(String fileName) {
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}

}
