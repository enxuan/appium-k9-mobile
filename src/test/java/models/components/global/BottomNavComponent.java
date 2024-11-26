package models.components.global;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

public class BottomNavComponent {

    private final AppiumDriver<MobileElement> appiumDriver;
    public final static By loginIconSel = MobileBy.AccessibilityId("Login");

    public BottomNavComponent(AppiumDriver<MobileElement> driver) {
        this.appiumDriver = driver;
    }

    public void clickLoginIcon() {
        appiumDriver.findElement(loginIconSel).click();
    }
}
