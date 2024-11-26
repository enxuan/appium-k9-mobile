package models.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

public class LoginScreenMod03 {

    private final AppiumDriver<MobileElement> appiumDriver;
    private final static By usernameSel = MobileBy.AccessibilityId("input-email");
    private final static By passwordSel = MobileBy.AccessibilityId("input-password");
    private final static By lobinBtnSel = MobileBy.AccessibilityId("button-LOGIN");

    public LoginScreenMod03(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    public LoginScreenMod03 inputUsername(String usernameTxt) {
        if (!usernameTxt.isEmpty()) {
            appiumDriver.findElement(usernameSel).sendKeys(usernameTxt);
        }
        return this;
    }

    public LoginScreenMod03 inputPassword(String password) {
        if (!password.isEmpty()) {
            appiumDriver.findElement(passwordSel).sendKeys(password);
        }
        return this;
    }

    public void clickLoginBtnElem() {
        appiumDriver.findElement(lobinBtnSel).click();
    }
}
