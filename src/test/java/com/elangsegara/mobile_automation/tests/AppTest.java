package com.elangsegara.mobile_automation.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AppTest extends BaseTest {
    @Test()
    public void appCanOpenedNormally() {
        // Verify app is launched successfully
        Assert.assertTrue(getDriver().getPageSource().length() > 0, "App should be launched successfully");
    }
}
