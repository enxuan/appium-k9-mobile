package api_learing;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import platform.Platform;

import java.util.List;

public class XPathLearning {

    public static void main(String[] args) {

        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

        try {
            //navigate to login screen
            MobileElement navLoginScreenBtn = appiumDriver.findElement(MobileBy.AccessibilityId("Login"));
            navLoginScreenBtn.click();

            //find login form elements
            List<MobileElement> creFieldsElem = appiumDriver.findElements(MobileBy.xpath("//android.widget.EditText"));
            final int USERRNAME_INDEX = 0;
            final int PASSWORD_INDEX = 1;
            creFieldsElem.get(USERRNAME_INDEX).sendKeys("teo@sth.com");
            creFieldsElem.get(PASSWORD_INDEX).sendKeys("12345678");

            //Find logn info text by UiSelector
            MobileElement loginInstructionElem =
                    appiumDriver.findElement(MobileBy.AndroidUIAutomator(
                            "new UiSelector().textContains(\"When the device\")"));
            System.out.println(loginInstructionElem.getText());

            //DEBUG PURPOSE ONLY
            Thread.sleep(10000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        appiumDriver.quit();

    }
}
