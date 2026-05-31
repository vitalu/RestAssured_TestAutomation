package com.api.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
//WAP to read the properties file from src/test/resources/config/config.properties
	private static Properties properies = new Properties();
	private static String propertiesFilePath="config/config.properties";
	private static String env;

	private ConfigManager() {

	}

	static {
		env = System.getProperty("env","qa");
		env = env.toLowerCase().trim();
		System.out.println("*** Running tests in "+env+ " environment ***");
		switch (env) {
		case "dev" -> propertiesFilePath = "config/config.dev.properties";
		case "qa" -> propertiesFilePath = "config/config.qa.properties";
		case "uat" -> propertiesFilePath = "config/config.uat.properties";
		default -> propertiesFilePath = "config/config.properties";
		}
		InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(propertiesFilePath);
		// load the properties file using the load()
//		File configFile = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator
//				+ "test" + File.separator + "resources" + File.separator + "config" + File.separator
//				+ "config.properties");
//		FileReader fileReader = null;
		if (propertiesFilePath == null) {
			throw new RuntimeException("Can not find the file at the provided path " + propertiesFilePath);
		}
		try {
			// fileReader = new FileReader(input);
			properies.load(input);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String getProperty(String key) throws IOException {

		return properies.getProperty("BASE_URI");
	}
}
