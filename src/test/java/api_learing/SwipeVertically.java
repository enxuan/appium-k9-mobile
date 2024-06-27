package api_learing;

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
import util.Swipe;

import java.time.Duration;

public class SwipeVertically {

    public static void main(String[] args) {

        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

        try {
            //navigate to login screen
            MobileElement navFormsScreenBtn = appiumDriver.findElement(MobileBy.AccessibilityId("Forms"));
            navFormsScreenBtn.click();

            //Wait until user is on Forms screen
            WebDriverWait wait = new WebDriverWait(appiumDriver, 10L);
            wait.until(ExpectedConditions
                            .visibilityOfElementLocated(MobileBy.AndroidUIAutomator(
                            "new UiSelector().textContains(\"Form components\")")));

            //Get Mobile window size
//            Dimension windowSize = appiumDriver.manage().window().getSize();
//            int screenHeight = windowSize.getHeight();
//            int screenWidth = windowSize.getWidth();
//
//            //calculate touch points
//            int xStartPoint = 50 * screenWidth / 100;
//            int xEndPoint = 50 * screenWidth / 100;
//
//            int yStartPoint = 50 * screenHeight / 100;
//            int yEndPoint = 10 * screenHeight / 100;

            Swipe.swipeVertically(appiumDriver, 50, 50, 10);

            // Convert coordinate -> PointOption
//            PointOption startPoint = new PointOption<>().withCoordinates(xStartPoint, yStartPoint);
//            PointOption endPoint = new PointOption<>().withCoordinates(xEndPoint, yEndPoint);
//
//            //Using TouchAction to swipe
//            TouchAction touchAction = new TouchAction(appiumDriver);
//            touchAction
//                    .press(startPoint)
//                    .waitAction(new WaitOptions().withDuration(Duration.ofMillis(500)))
//                    .moveTo(endPoint)
//                    .release()
//                    .perform();
//
//            //swipe down | trick: revert coordinates
//            //long press = press + wait about 1000ms
//            touchAction
//                    .longPress(endPoint)
//                    .moveTo(startPoint)
//                    .release()
//                    .perform();

            //click on button active
            MobileElement activeBtnElem = appiumDriver.findElement(MobileBy.AccessibilityId("button-Active"));
            activeBtnElem.click();

            //DEBUG PURPOSE ONLY
            Thread.sleep(10000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        appiumDriver.quit();

    }
}
