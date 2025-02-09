package com.elangsegara.mobile_automation.utils;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * LoggingUtils provides a simple wrapper for logging messages in mobile
 * automation tests.
 */
public final class LoggingUtils {
  private static final Logger LOGGER = Logger.getLogger(LoggingUtils.class.getName());

  static {
    // Configure the logger to output all levels to the console.
    LOGGER.setLevel(Level.ALL);
    ConsoleHandler handler = new ConsoleHandler();
    handler.setLevel(Level.ALL);
    LOGGER.addHandler(handler);
    // Disable the parent handlers to avoid duplicate logging.
    LOGGER.setUseParentHandlers(false);
  }

  // Private constructor to prevent instantiation.
  private LoggingUtils() {
  }

  /**
   * Logs an informational message.
   *
   * @param message the message to log.
   */
  public static void info(String message) {
    LOGGER.info(message);
  }

  /**
   * Logs a warning message.
   *
   * @param message the warning message.
   */
  public static void warn(String message) {
    LOGGER.warning(message);
  }

  /**
   * Logs an error message.
   *
   * @param message the error message.
   */
  public static void error(String message) {
    LOGGER.severe(message);
  }

  /**
   * Logs an error message along with a throwable.
   *
   * @param message   the error message.
   * @param throwable the throwable to log.
   */
  public static void error(String message, Throwable throwable) {
    LOGGER.log(Level.SEVERE, message, throwable);
  }
}
