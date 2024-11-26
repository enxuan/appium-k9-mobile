package api_learning;

import driver.AppPackages;
import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import platform.Platform;

import java.time.Duration;

public class HandleMultipleApps {

    public static void main(String[] args) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

        try {
            MobileElement navLoginScreenBtn = appiumDriver.findElement(MobileBy.AccessibilityId("Login"));
            navLoginScreenBtn.click();

            // Interact on login form
            MobileElement emailTextbox = appiumDriver.findElement(MobileBy.AccessibilityId("input-email"));
            MobileElement passwordTextbox = appiumDriver.findElement(MobileBy.AccessibilityId("input-password"));
            MobileElement loginBtnElem = appiumDriver.findElement(MobileBy.AccessibilityId("button-LOGIN"));

            emailTextbox.sendKeys("teo@sth.com");
            passwordTextbox.sendKeys("12345678");
            loginBtnElem.click();

            // Put the app under test to background in a certain time| simulate pressing home button > relaunch
//            appiumDriver.runAppInBackground(Duration.ofSeconds(3));
//
            // Put the app under test to background till we call it back
            appiumDriver.runAppInBackground(Duration.ofSeconds(-1));

            // Switch into another app | Go to Settings toggle wifi
            appiumDriver.activateApp(AppPackages.SETTINGS);

            // Navigate to network list
            By connectionLabelSel = MobileBy.xpath("//*[@text='Connections']");
            By wifiLabelSel = MobileBy.xpath("//*[@text='Wi-Fi']");

            appiumDriver.findElement(connectionLabelSel).click();
            appiumDriver.findElement(wifiLabelSel).click();

            // Tobble ON/OFF
            By wifiStatusSel = MobileBy.id("com.android.settings:id/switch_text");
            MobileElement wifiStatusElem = appiumDriver.findElement(wifiStatusSel);
            String wifiStatusStr = wifiStatusElem.getText().trim();
            boolean isWifiOn = wifiStatusStr.equalsIgnoreCase("on");
            if (isWifiOn) {
                wifiStatusElem.click();
            }

            // Come back to the app > interact with other elements
            appiumDriver.activateApp(AppPackages.WEBDRIVER_IO);
            appiumDriver.findElement(MobileBy.xpath("//*[@text='OK']")).click();

            // Debug purpose only
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        appiumDriver.quit();
    }
}
