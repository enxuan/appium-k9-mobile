package api_learning;

import Utils.Swipe;
import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import platform.Platform;

import java.time.Duration;
import java.util.List;

public class SwipeHorizontally {

    public static void main(String[] args) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

        try {
            // Navigate to Forms screen
            MobileElement navSwipeScreenBtn = appiumDriver.findElement(MobileBy.AccessibilityId("Swipe"));
            navSwipeScreenBtn.click();

            // Wait until user in on Forms screen
            WebDriverWait wait = new WebDriverWait(appiumDriver, 10L);
            wait.until(ExpectedConditions.
                    visibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"Swipe horizontal\")")));

            // Swipe from right to left 5 times
            Swipe.swipeHorizontally(appiumDriver, 90, 10, 5);
            // DEBUG PURPOSE ONLY
            Thread.sleep(3000);

            // Swipe from left to right 5 times
            Swipe.swipeHorizontally(appiumDriver, 10, 90, 5);
            // DEBUG PURPOSE ONLY
            Thread.sleep(3000);

            // Swipe to time see SUPPORT VIDEOS
            for (int i = 0; i < 5; i++) {
                List<MobileElement> swipeList = appiumDriver.findElements(MobileBy.AndroidUIAutomator(
                        "new UiSelector().text(\"SUPPORT VIDEOS\")"));
                if (!swipeList.isEmpty()) {
                    break;
                } else {
                    Swipe.swipeHorizontally(appiumDriver);
                }
            }


            // DEBUG PURPOSE ONLY
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        appiumDriver.quit();
    }
}
