package com.elangsegara.mobile_automation.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * ConfigurationManager is responsible for loading and providing configuration
 * values
 * from a properties file, environment variables, and system properties.
 * <p>
 * Priority Order:
 * 1. System properties
 * 2. Environment Variables
 * 3. Properties file (config.properties)
 * </p>
 */

public final class ConfigurationManager {
  private static final String CONFIG_FILE = "config.properties";
  private static final Properties properties = new Properties();

  // Private constructor to prevent instantiation.
  private ConfigurationManager() {
  }

  static {
    try (InputStream inputStream = ConfigurationManager.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
      if (inputStream == null) {
        throw new RuntimeException("Configuration file " + CONFIG_FILE + " not found in classpath.");
      }
      properties.load(inputStream);
    } catch (IOException e) {
      throw new RuntimeException("Failed to load configuration file: " + CONFIG_FILE, e);
    }
  }

  /**
   * Retrieves the configuration property for the given key.
   * The method first checks system properties, then environment variables,
   * and finally the properties file.
   *
   * @param key The configuration key.
   * @return The configuration value, or null if the key is not found.
   */
  public static String getProperty(String key) {
    // Check system properties
    String value = System.getProperty(key);
    if (value != null) {
      return value;
    }

    // Check environment variables
    value = System.getenv(key);
    if (value != null) {
      return value;
    }

    // Fallback to properties file
    return properties.getProperty(key);
  }

  /**
   * Retrieves the configuration property for the given key,
   * returning a default value if the property is not found.
   *
   * @param key          The configuration key.
   * @param defaultValue The default value to return if the key is not present.
   * @return The configuration value or the default value if the key is not
   *         present.
   */
  public static String getProperty(String key, String defaultValue) {
    String value = getProperty(key);
    return value != null ? value : defaultValue;
  }
}
