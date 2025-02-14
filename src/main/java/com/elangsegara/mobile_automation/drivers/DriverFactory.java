package com.elangsegara.mobile_automation.drivers;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import com.elangsegara.mobile_automation.exceptions.AppiumServerException;

public class DriverFactory {
  // Single instance of AppiumDriver to be used across tests
  private static AppiumDriver driver;

  private static AppiumDriverLocalService appiumServer;

  private static URL appiumUrl;

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
      // Start Appium server if not already running
      if (appiumServer == null || !appiumServer.isRunning()) {
        try {
          AppiumServiceBuilder builder = new AppiumServiceBuilder()
              .usingAnyFreePort()
              .withArgument(GeneralServerFlag.USE_DRIVERS,
                  platform.equalsIgnoreCase("android") ? "uiautomator2" : "xcuitest")
              .withArgument(GeneralServerFlag.RELAXED_SECURITY)
              .withArgument(GeneralServerFlag.USE_PLUGINS, "device-farm")
              .withArgument(GeneralServerFlag.BASEPATH, "/wd/hub");

          appiumServer = AppiumDriverLocalService.buildService(builder);
          appiumServer.start();
          appiumUrl = appiumServer.getUrl();

          if (!appiumServer.isRunning()) {
            throw new AppiumServerException("Failed to start Appium server");
          }
        } catch (Exception e) {
          throw new AppiumServerException("Error creating/starting Appium server", e);
        }
      } else {
        appiumUrl = URI.create("http://127.0.0.1:4723/wd/hub").toURL();
      }

      if (platform.equalsIgnoreCase("android")) {
        driver = new AndroidDriver(appiumUrl, androidOptions());
      } else if (platform.equalsIgnoreCase("ios")) {
        driver = new IOSDriver(appiumUrl, iOsOptions());
      } else {
        throw new IllegalArgumentException("unsupported platform: " + platform);
      }
    }
    return driver;
  }

  /**
   * Stops the Appium server if it's running.
   */
  private static void stopAppiumServer() {
    if (appiumServer != null && appiumServer.isRunning()) {
      appiumServer.stop();
    }
  }

  /**
   * Creates and returns an instance of UiAutomator2Options.
   *
   * @return UiAutomator2Options instance.
   */
  private static UiAutomator2Options androidOptions() {
    return new UiAutomator2Options().autoGrantPermissions().setApp("app.apk");
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
      stopAppiumServer();
    }
  }
}
