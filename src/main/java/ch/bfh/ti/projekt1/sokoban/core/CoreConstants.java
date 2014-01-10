package ch.bfh.ti.projekt1.sokoban.core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;

/**
 * Class is used to load properties of the application
 * 
 * @author svennyffenegger
 * @since 04/11/13 19:43
 */
public class CoreConstants {

	private static final Logger LOG = Logger.getLogger(CoreConstants.class);

	// path to properties file
	private static final String APP_PROPERTIES = "src/main/resources/ch/bfh/ti/projekt1/sokoban/config/sokoban.properties";

	private static CoreConstants instance = null;
	private Properties properties;

	/**
	 * private constructor for singleton, reads the properties from file
	 */
	private CoreConstants() {
		properties = new Properties();
		LOG.info("The properties file is being loaded: " + APP_PROPERTIES);
		try (FileInputStream inputStream = new FileInputStream(APP_PROPERTIES)) {
			properties.load(inputStream);
			LOG.info("Properties file load successful");
		} catch (FileNotFoundException e) {
			LOG.error(e);
		} catch (IOException e) {
			LOG.error(e);
		}
	}

	/**
	 * static method, but gets instance over getInstance()
	 * 
	 * @param property
	 *            key
	 * @return the value for the key
	 */
	public static String getProperty(String property) {
		return getInstance().properties.getProperty(property);
	}

	/**
	 * Getter Method for singleton instance
	 * 
	 * @return instance of this class
	 */
	public static synchronized CoreConstants getInstance() {
		if (instance == null) {
			instance = new CoreConstants();
		}
		return instance;
	}

}
