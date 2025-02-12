package com.elangsegara.mobile_automation.drivers;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;

import java.net.MalformedURLException;
import java.net.URI;

public class DriverFactory {
  // Single instance of AppiumDriver to be used across tests
  private static AppiumDriver driver;

  /**
   * Returns an instance of AppiumDriver based on the specified platform.
   *
   * @param platform The mobile platform ("android" or "ios").
   * @return AppiumDriver instance.
   * @throws MalformedURLException    If the URL to the Appium server is
   *                                  malformed.
   * @throws IllegalArgumentException If an unsupported platform is specified.
   */
  public static AppiumDriver getDriver(String platform) throws MalformedURLException {
    if (driver == null) {
      if (platform.equalsIgnoreCase("android")) {
        driver = new AndroidDriver(URI.create("http://127.0.0.1:4723/wd/hub").toURL(), androidOptions());
      } else if (platform.equalsIgnoreCase("ios")) {
        driver = new IOSDriver(URI.create("http://127.0.0.1:4723/wd/hub").toURL(), iOsOptions());
      } else {
        throw new IllegalArgumentException("unsupported platform: " + platform);
      }
    }
    return driver;
  }

  /**
   * Creates and returns an instance of UiAutomator2Options.
   *
   * @return UiAutomator2Options instance.
   */
  private static UiAutomator2Options androidOptions() {
    return new UiAutomator2Options().setUdid("udid").setApp("/app.apk");
  }

  /**
   * Creates and returns an instance of XCUITestOptions.
   *
   * @return XCUITestOptions instance.
   */
  private static XCUITestOptions iOsOptions() {
    return new XCUITestOptions().setUdid("udid").setApp("/app.app");
  }

  /**
   * Quits the driver and resets the instance.
   */
  public static void quitDriver() {
    if (driver != null) {
      driver.quit();
      driver = null;
    }
  }
}
