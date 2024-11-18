package api_learning;

import Utils.Swipe;
import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import platform.Platform;

public class FormsFormTest {

    private final static String INPUT_TEXT = "input sample text";

    public static void main(String[] args) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

        try {
            // Navigate to Forms screen
            MobileElement navLoginScreenBtn = appiumDriver.findElement(MobileBy.AccessibilityId("Forms"));
            navLoginScreenBtn.click();

            // Wait until user in on Forms screen
            WebDriverWait wait = new WebDriverWait(appiumDriver, 10L);
            // Input fields
            MobileElement inputFieldElem = appiumDriver.findElement(MobileBy.AccessibilityId("text-input"));
            MobileElement inputTextResultElem = appiumDriver.findElement(MobileBy.AccessibilityId("input-text-result"));
            MobileElement switchElem = appiumDriver.findElement(MobileBy.AccessibilityId("switch"));
            MobileElement switchTextElem = appiumDriver.findElement(MobileBy.AccessibilityId("switch-text"));
            MobileElement dropdownElem = appiumDriver.findElement(MobileBy.AccessibilityId("Dropdown"));

            //Interact with elem
            inputFieldElem.sendKeys(INPUT_TEXT);
            if (!inputTextResultElem.getText().equals(inputFieldElem.getText())) {
                throw new RuntimeException("the inputted text and text result must be same");
            }
            switchElem.click();
            // after click switch, must check the text under switch contains OFF text
            System.out.println("switch status: " + switchElem.getAttribute("checked"));
            if (switchElem.getAttribute("checked").equals("true")) {
                if (!switchTextElem.getText().contains("OFF")) {
                    throw new RuntimeException("Switch is ON, the switch text should contain OFF");
                }
            } else {
                if (!switchTextElem.getText().contains("ON")) {
                    throw new RuntimeException("Switch is OFF, the switch text should contain ON");
                }
            }
            // DropdownList
            dropdownElem.click();
            appiumDriver.findElement(MobileBy.AndroidUIAutomator(
                    "new UiSelector().text(\"Appium is awesome\")")).click();
            if (appiumDriver.findElements(MobileBy.AndroidUIAutomator(
                    "new UiSelector().text(\"Appium is awesome\")")).isEmpty()) {
                throw new RuntimeException("the selected dropdown elem is not correct");
            }

            // Swipe vertically
            Swipe.swipeVertically(appiumDriver);

            // TODO:  Swipe up from 90 -> 10 : Swipe.swipeVertically();
            // TODO:  Swipe up step 10% 5 times: Swipe.swipeVertically(10, 5);

            // click on Btn-Active
            MobileElement activeBtnElem = appiumDriver.findElement(MobileBy.AccessibilityId("button-Active"));
            activeBtnElem.click();

            // DEBUG PURPOSE ONLY
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        appiumDriver.quit();
    }
}
