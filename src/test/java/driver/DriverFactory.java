package driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import platform.Platform;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DriverFactory implements MobileCapabilityTypeEx {

    public static AppiumDriver<MobileElement> getDriver(Platform platform) {
        // Send a request to appium server > ask to launch the app
        AppiumDriver<MobileElement> appiumDriver = null;

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(PLATFORM_NAME, "Android");
        desiredCapabilities.setCapability(AUTOMATION_NAME, "uiautomator2");
        desiredCapabilities.setCapability(UDID, "RF8W40F0GKF");
        desiredCapabilities.setCapability(APP_PACKAGE, "com.wdiodemoapp");
        desiredCapabilities.setCapability(APP_ACTIVITY, "com.wdiodemoapp.MainActivity");

        URL appiumServer = null;
        try {// Init appium session
            appiumServer = new URL("http://localhost:4723/wd/hub");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (appiumServer == null) {
            throw new RuntimeException("Can't construct the appium server url: http://localhost:4723/wd/hub");
        }

        switch (platform) {
            case ANDROID:
                appiumDriver = new AndroidDriver<>(appiumServer, desiredCapabilities);
                break;
            case IOS:
                appiumDriver = new IOSDriver<>(appiumServer, desiredCapabilities);
                break;
        }

        // Implicit Wait | Interval time: 500ms
        appiumDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

        return appiumDriver;
    }
}
