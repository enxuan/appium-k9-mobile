package api_learing;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import platform.Platform;

public class FormsFormTest {

    public static void main(String[] args) {
        String typeString = "abcdxyz";
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

        //click Forms button to open Forms screen
        MobileElement navFormsScreenBtn = appiumDriver.findElement(MobileBy.AccessibilityId("Forms"));
        navFormsScreenBtn.click();

        //Wait until user is on Forms screen
        WebDriverWait wait = new WebDriverWait(appiumDriver, 10L);
        wait.until(ExpectedConditions
                .visibilityOfElementLocated(MobileBy.AndroidUIAutomator(
                        "new UiSelector().textContains(\"Form components\")")));

        //find input field and fill text
        MobileElement inputFieldElm = appiumDriver.findElement(MobileBy.AccessibilityId("text-input"));
        inputFieldElm.sendKeys(typeString);

        //check text in you have typed text box
        MobileElement youHaveTypedElm = appiumDriver.findElement(MobileBy.AccessibilityId("input-text-result"));

        if (!youHaveTypedElm.getText().equals(typeString)) {
            throw new IllegalArgumentException("the input field and you have type field have difference value");
        }

        //get Switch bar and touch
        MobileElement switchElm = appiumDriver.findElement(MobileBy.AccessibilityId("switch"));
        System.out.println("The switch first value : " + switchElm.getText());
        switchElm.click();
        System.out.println("The switch last value : " + switchElm.getText());

        //get Dropdown element and click to choose one value
        MobileElement dropDownElm = appiumDriver.findElement(MobileBy.AccessibilityId("Dropdown"));
        dropDownElm.click();
        //check if the dropdown popup is showed
        //Wait until user is on Forms screen
        wait.until(ExpectedConditions
                .visibilityOfElementLocated(MobileBy.AndroidUIAutomator(
                        "new UiSelector().textContains(\"Appium is awesome\")")));

        //click to choose Appium is awesome option
        MobileElement appiumIsAwesomeElm = appiumDriver.findElement(MobileBy
                .AndroidUIAutomator("new UiSelector().textContains(\"Appium is awesome\")"));
        appiumIsAwesomeElm.click();

        //Check if screen contain selected item: "appium is awesome"
        wait.until(ExpectedConditions
                .visibilityOfElementLocated(MobileBy.AndroidUIAutomator(
                        "new UiSelector().textContains(\"Appium is awesome\")")));

        appiumDriver.quit();
    }

}
