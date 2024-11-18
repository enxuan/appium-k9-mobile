package Utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;

import java.time.Duration;

public class Swipe {
    private static Point getCoordinate(AppiumDriver appiumDriver, int startX, int startY, int endX, int endY) {
        // Get Mobile window size
        Dimension windowSize = appiumDriver.manage().window().getSize();
        int screenHeight = windowSize.getHeight();
        int screenWidth = windowSize.getWidth();

        // Calculate touch points
        int xStartPoint = startX * screenWidth / 100;
        int yStartPoint = startY * screenHeight / 100;
        int xEndPoint = endX * screenWidth / 100;
        int yEndPoint = endY * screenHeight / 100;

        // Convert coordinates -> PointOption
        PointOption startPoint = new PointOption<>().withCoordinates(xStartPoint, yStartPoint);
        PointOption endPoint = new PointOption<>().withCoordinates(xEndPoint, yEndPoint);
        return new Point(startPoint, endPoint);
    }

    private static Point getCoordinate(AppiumDriver appiumDriver) {
        return getCoordinate(appiumDriver, 50, 90, 50, 10);
    }

    private static void swipe(AppiumDriver appiumDriver, PointOption startPoint, PointOption endPoint) {
        TouchAction touchAction = new TouchAction(appiumDriver);
        touchAction
                .press(startPoint)
                .waitAction(new WaitOptions().withDuration(Duration.ofMillis(500)))
                .moveTo(endPoint)
                .release()
                .perform();
    }

    public static void swipeVertically(AppiumDriver appiumDriver) {
        Point p = getCoordinate(appiumDriver);
        swipe(appiumDriver, p.startPoint, p.endPoint);
    }

    public static void swipeVertically(AppiumDriver appiumDriver, int stepPercent, int swipeTimes) {
        Point p = getCoordinate(appiumDriver, 50, 50, 50, 50-stepPercent);
        for (int i = 0; i < swipeTimes; i++) {
            swipe(appiumDriver, p.getStartPoint(), p.getEndPoint());
        }
    }

    public static void swipeHorizontally(AppiumDriver appiumDriver) {
        Point p = getCoordinate(appiumDriver, 90, 70, 10, 70);
        swipe(appiumDriver, p.startPoint, p.endPoint);
    }

    public static void swipeHorizontally(AppiumDriver appiumDriver, int startX, int endX, int swipeTimes) {
        Point p = getCoordinate(appiumDriver, startX, 70, endX, 70);
        for (int i = 0; i < swipeTimes; i++) {
            swipe(appiumDriver, p.getStartPoint(), p.getEndPoint());
        }
    }

    public static class Point {
        public PointOption startPoint;
        public PointOption endPoint;

        public Point(PointOption startPoint, PointOption endPoint) {
            this.startPoint = startPoint;
            this.endPoint = endPoint;
        }

        public PointOption getStartPoint() {
            return startPoint;
        }

        public PointOption getEndPoint() {
            return endPoint;
        }
    }
}
