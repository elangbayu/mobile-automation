package com.elangsegara.mobile_automation.base;

import java.net.MalformedURLException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.elangsegara.mobile_automation.drivers.DriverFactory;

import io.appium.java_client.AppiumDriver;

/**
 * BaseTest provides the foundational setup for mobile tests.
 * It initializes the driver and configures common test parameters.
 */

public abstract class BaseTest {
  protected AppiumDriver driver;

  /**
   * Initializes the mobile driver before any tests in the extending class are
   * run.
   * The platform is determined via a system property (defaults to "android" if
   * not specified).
   *
   * @throws MalformedURLException if the Appium server URL is malformed.
   */
  @BeforeClass(alwaysRun = true)
  public void setUp() throws MalformedURLException {
    // Determine the platform (e.g., "android" or "ios"). Defaults to android.
    String platform = System.getProperty("platform", "android");
    driver = DriverFactory.getDriver(platform);
  }

  /**
   * Quits the mobile driver after all tests in the extending class have executed.
   */
  @AfterClass(alwaysRun = true)
  public void tearDown() {
    DriverFactory.quitDriver();
  }
}
