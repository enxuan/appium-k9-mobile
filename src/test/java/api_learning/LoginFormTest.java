package api_learning;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import platform.Platform;

import java.time.Duration;

public class LoginFormTest {

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

            // Verification | Login dialog
            // ID | android:id/alertTitle
            WebDriverWait wait = new WebDriverWait(appiumDriver, 5);
            WebElement loginDialogTitleElem = wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.id("android:id/alertTitle")));
            System.out.println(loginDialogTitleElem.getText());

            // DEBUG PURPOSE ONLY
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        appiumDriver.quit();
    }
}
