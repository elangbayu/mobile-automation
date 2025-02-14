package com.elangsegara.mobile_automation.tests;

import io.appium.java_client.AppiumDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.elangsegara.mobile_automation.drivers.DriverFactory;

public class BaseTest {
  protected AppiumDriver driver;

  @Parameters({ "platform" })
  @BeforeClass
  public void setUp(@Optional("android") String platform) throws Exception {
    driver = DriverFactory.getDriver(platform);
  }

  @AfterClass
  public void tearDown() {
    DriverFactory.quitDriver();
  }

  protected AppiumDriver getDriver() {
    return driver;
  }
}
