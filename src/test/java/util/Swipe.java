package util;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import platform.Platform;

import java.time.Duration;

public class Swipe {

    public static PointOption getPointByPercent(AppiumDriver<MobileElement> appiumDriver, int xPercent, int yPercent) {
        Dimension windowSize = appiumDriver.manage().window().getSize();
        int screenHeight = windowSize.getHeight();
        int screenWidth = windowSize.getWidth();

        int xValue = xPercent * screenWidth / 100;
        int yValue = yPercent * screenHeight / 100;

        PointOption point = new PointOption<>().withCoordinates(xValue, yValue);
        return point;
    }

    //swipe vertically from yStartPercent to yEndPercent with xPercent
    public static void swipeVertically(AppiumDriver<MobileElement> appiumDriver, int xPercent, int yStartPercent, int yEndPercent) {

        PointOption startPoint = getPointByPercent(appiumDriver, xPercent, yStartPercent);
        PointOption endPoint = getPointByPercent(appiumDriver, xPercent, yEndPercent);

        TouchAction touchAction = new TouchAction(appiumDriver);

        touchAction
                .press(startPoint)
                .waitAction(new WaitOptions().withDuration(Duration.ofMillis(500)))
                .moveTo(endPoint)
                .release()
                .perform();
    }

    //Swipe vertically with loopTime, each time will swipe percent %
    public static void swipeVertically(AppiumDriver<MobileElement> appiumDriver, int percent, int loopTime) {
        //set final value for x and y percent
        final int X_PERCENT = 50;
        final int Y_PERCENT = 50;

        PointOption startPoint = getPointByPercent(appiumDriver, X_PERCENT, Y_PERCENT);
        PointOption endPoint = getPointByPercent(appiumDriver, X_PERCENT, Y_PERCENT + percent);

        TouchAction touchAction = new TouchAction(appiumDriver);

        for (int i = 0; i < loopTime; i++) {
            touchAction
                    .press(startPoint)
                    .waitAction(new WaitOptions().withDuration(Duration.ofMillis(500)))
                    .moveTo(endPoint)
                    .release()
                    .perform();
        }
    }

    //Swipe Horizontally from xstartPercent to xEndPercent with yPercent
    public static void swipeHorizontally(AppiumDriver<MobileElement> appiumDriver, int xStartPercent, int xEndPercent, int yPercent) {
        PointOption startPoint = getPointByPercent(appiumDriver, xStartPercent, yPercent);
        PointOption endPoint = getPointByPercent(appiumDriver, xEndPercent, yPercent);

        TouchAction touchAction = new TouchAction(appiumDriver);

        touchAction
                .press(startPoint)
                .waitAction(new WaitOptions().withDuration(Duration.ofMillis(500)))
                .moveTo(endPoint)
                .release()
                .perform();
    }

    //Swipe Horizontally with looptime, each time swipe percent %
    public static void swipeHorizontally(AppiumDriver<MobileElement> appiumDriver, int percent, int loopTime) {

        //set final value for x start percent and y start percent
        final int X_PERCENT = 20;
        final int Y_PERCENT = 50;

        PointOption startPoint = getPointByPercent(appiumDriver, X_PERCENT, Y_PERCENT);
        PointOption endPoint = getPointByPercent(appiumDriver, X_PERCENT + percent, Y_PERCENT);

        TouchAction touchAction = new TouchAction(appiumDriver);

        for (int i = 0; i < loopTime; i++) {
            touchAction
                    .press(startPoint)
                    .waitAction(new WaitOptions().withDuration(Duration.ofMillis(500)))
                    .moveTo(endPoint)
                    .release()
                    .perform();
        }
    }
}
