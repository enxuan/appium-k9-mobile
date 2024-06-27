package api_learing;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import platform.Platform;
import util.Swipe;

import java.util.List;

public class SwipeFormTest {

    public static void main(String[] args) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

        //open Swipe Form
        MobileElement navSwipeScreenBtn = appiumDriver.findElement(MobileBy.AccessibilityId("Swipe"));
        navSwipeScreenBtn.click();

        //Wait until user is on Forms screen
        WebDriverWait wait = new WebDriverWait(appiumDriver, 10L);
        wait.until(ExpectedConditions
                .visibilityOfElementLocated(MobileBy.AndroidUIAutomator(
                        "new UiSelector().textContains(\"Swipe horizontal\")")));

        boolean isSupportVideosShowing = false;

        while (!isSupportVideosShowing) {
            Swipe.swipeHorizontally(appiumDriver, 70, 10, 70);
            List<MobileElement> mobileElement = appiumDriver.findElements(MobileBy.AndroidUIAutomator(
                    "new UiSelector().textContains(\"SUPPORT VIDEOS\")"
            ));
            if (mobileElement.size() > 0) {
                isSupportVideosShowing = true;
            }
        }

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        appiumDriver.quit();
    }
}
