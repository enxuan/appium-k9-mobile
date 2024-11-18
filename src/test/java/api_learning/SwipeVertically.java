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

public class SwipeVertically {

    public static void main(String[] args) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

        try {
            // Navigate to Forms screen
            MobileElement navLoginScreenBtn = appiumDriver.findElement(MobileBy.AccessibilityId("Forms"));
            navLoginScreenBtn.click();

            // Wait until user in on Forms screen
            WebDriverWait wait = new WebDriverWait(appiumDriver, 10L);
            wait.until(ExpectedConditions.
                    visibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"Form components\")")));

            // Get Mobile window size
//            Dimension windowSize = appiumDriver.manage().window().getSize();
//            int screenHeight = windowSize.getHeight();
//            int screenWidth = windowSize.getWidth();
//
//            // Calculate touch points
//            int xStartPoint = screenWidth / 2;
//            int yStartPoint = screenHeight / 2;
//            int xEndPoint = xStartPoint;
//            int yEndPoint = 10 * screenHeight / 100;
//
//            // Convert coordinates -> PointOption
//            PointOption startPoint = new PointOption<>().withCoordinates(xStartPoint, yStartPoint);
//            PointOption endPoint = new PointOption<>().withCoordinates(xEndPoint, yEndPoint);
//
//            // Using TouchAction to swipe
//            TouchAction touchAction = new TouchAction(appiumDriver);
//            touchAction
//                    .press(startPoint)
//                    .waitAction(new WaitOptions().withDuration(Duration.ofMillis(500)))
//                    .moveTo(endPoint)
//                    .release()
//                    .perform();
//
//            // Swipe up | trick: revert coordinates
//            // press + wait = longPress
//            touchAction
//                    .longPress(endPoint)
//                    .moveTo(startPoint)
//                    .release()
//                    .perform();
            Swipe.swipeVertically(appiumDriver);

            // click on Btn-Active
//            MobileElement activeBtnElem = appiumDriver.findElement(MobileBy.AccessibilityId("button-Active"));
//            activeBtnElem.click();

            // DEBUG PURPOSE ONLY
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        appiumDriver.quit();
    }
}
