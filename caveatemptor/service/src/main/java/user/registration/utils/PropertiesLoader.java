package user.registration.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {

	private PropertiesLoader() {
	}

	public static Properties getProperties(ClassLoader classLoader, String path) {

		Properties properties = new Properties();
		InputStream input = null;

		try {
			input = classLoader.getResourceAsStream(path);
			properties.load(input);
			input.close();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}

		return properties;
	}
}
