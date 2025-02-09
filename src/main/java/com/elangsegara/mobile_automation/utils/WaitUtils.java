package com.elangsegara.mobile_automation.utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;

/**
 * WaitUtils provides common wait methods for mobile automation tests.
 * It offers methods to wait for element visibility and clickability.
 */
public final class WaitUtils {
  // Private constructor to prevent instantiation.
  private WaitUtils() {
  }

  /**
   * Waits for the visibility of the element located by the provided locator.
   *
   * @param driver           the AppiumDriver instance.
   * @param locator          the By locator of the element.
   * @param timeoutInSeconds the maximum wait time in seconds.
   * @return the WebElement once it becomes visible.
   */
  public static WebElement waitForVisibility(AppiumDriver driver, By locator, int timeoutInSeconds) {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
    return (WebElement) wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
  }

  /**
   * Waits for the element located by the provided locator to be clickable.
   *
   * @param driver           the AppiumDriver instance.
   * @param locator          the By locator of the element.
   * @param timeoutInSeconds the maximum wait time in seconds.
   * @return the WebElement once it becomes clickable.
   */
  public static WebElement waitForClickability(AppiumDriver driver, By locator,
      int timeoutInSeconds) {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
    return (WebElement) wait.until(ExpectedConditions.elementToBeClickable(locator));
  }
}
