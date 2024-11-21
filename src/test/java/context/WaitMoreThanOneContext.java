package context;

import io.appium.java_client.AppiumDriver;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class WaitMoreThanOneContext implements ExpectedCondition<Boolean> {

    private final AppiumDriver appiumDriver;

    public WaitMoreThanOneContext(AppiumDriver appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    @Override
    public Boolean apply(WebDriver driver) {
        return appiumDriver.getContextHandles().size() > 1;
    }
}
