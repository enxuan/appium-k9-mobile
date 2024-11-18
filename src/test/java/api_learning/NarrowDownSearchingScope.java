package api_learning;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import platform.Platform;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NarrowDownSearchingScope {

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
            Dimension windowSize = appiumDriver.manage().window().getSize();
            int screenHeight = windowSize.getHeight();
            int screenWidth = windowSize.getWidth();

            // Calculate touch points
            int xStartPoint = screenWidth / 2;
            int yStartPoint = 0;
            int xEndPoint = xStartPoint;
            int yEndPoint = 50 * screenHeight / 100;

            // Convert coordinates -> PointOption
            PointOption startPoint = new PointOption<>().withCoordinates(xStartPoint, yStartPoint);
            PointOption endPoint = new PointOption<>().withCoordinates(xEndPoint, yEndPoint);

            // Using TouchAction to swipe
            TouchAction touchAction = new TouchAction(appiumDriver);

            // Using touchAction to swipe
            touchAction.press(startPoint)
                    .waitAction(new WaitOptions().withDuration(Duration.ofMillis(500)))
                    .moveTo(endPoint)
                    .release()
                    .perform();

            List<MobileElement> notificationElems = appiumDriver.findElements(MobileBy.id("android:id/notification_headerless_view_row"));
            Map<String, String> notificationContents = new HashMap<>();
            for (MobileElement notificationElem : notificationElems) {
                List<MobileElement> titleElems = notificationElem.findElements(MobileBy.id("android:id/title"));
                List<MobileElement> contentElems = notificationElem.findElements(MobileBy.id("android:id/text"));
                if (!titleElems.isEmpty() && !contentElems.isEmpty()) {
                    MobileElement titleElem = titleElems.get(0);
                    MobileElement contentElem = contentElems.get(0);
                    notificationContents.put(titleElem.getText().trim(), contentElem.getText().trim());
                }
            }

            // Verification
            if (notificationContents.keySet().isEmpty()) {
                throw new RuntimeException("No notification");
            }
            for (String title : notificationContents.keySet()) {
                System.out.println("Title: " + title);
                System.out.println("Content: " + notificationContents.get(title));
            }

            // DEBUG PURPOSE ONLY
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        appiumDriver.quit();
    }
}
