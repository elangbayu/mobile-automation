package com.elangsegara.mobile_automation.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import io.appium.java_client.AppiumDriver;

/**
 * ScreenshotUtils provides methods for capturing and saving screenshots during
 * test execution.
 */
public final class ScreenshotUtils {
  // Private constructor to prevent instantiation.
  private ScreenshotUtils() {
  }

  /**
   * Captures a screenshot of the current screen and saves it to the specified
   * directory with a timestamp.
   *
   * @param driver   the AppiumDriver instance.
   * @param saveDir  the directory in which the screenshot will be saved.
   * @param fileName the base name for the screenshot file.
   * @return the File object representing the saved screenshot.
   */
  public static File captureScreenshot(AppiumDriver driver, String saveDir, String fileName) {
    // Generate timestamp for uniqueness
    String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    String filePath = saveDir + File.separator + fileName + "_" + timestamp + ".png";

    // Capture the screenshot
    File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    File destination = new File(filePath);
    try {
      Files.copy(screenshot.toPath(), destination.toPath());
    } catch (IOException e) {
      throw new RuntimeException("Failed to capture screenshot", e);
    }
    return destination;
  }
}
