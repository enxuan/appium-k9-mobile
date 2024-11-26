package api_learning;

import context.WaitMoreThanOneContext;
import driver.DriverFactory;
import driver.MobileCapabilityTypeEx;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.internal.CapabilityHelpers;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import platform.Platform;

import java.util.ArrayList;
import java.util.List;

import static context.Contexts.NATIVE;
import static context.Contexts.WEB_VIEW;

public class HandleVariantBehaviour implements MobileCapabilityTypeEx {

    public static void main(String[] args) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.ANDROID);
        try {
            // Get platform
            Capabilities caps = appiumDriver.getCapabilities();
            String platfromName = CapabilityHelpers.getCapability(caps, PLATFORM_NAME, String.class);
            System.out.println("[INFO] testing on: " + platfromName);

            // DEBUG PURPOSE ONLY
            Thread.sleep(3000);

        } catch (Exception e) {
            e.printStackTrace();
        }
        appiumDriver.quit();
    }
}
