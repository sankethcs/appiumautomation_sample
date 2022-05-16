import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;

public class ioSampleTest {
    public IOSDriver driver;
    public WebDriverWait wait;
    public ioSampleTest () throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("udid", "00008030-001E2D391A47802E");
        caps.setCapability("automationName", "XCUITest");//DeviceId from "adb devices" command
        caps.setCapability("platformName", "iOS");
        caps.setCapability("bundleId","com.dreamplug.cred.qa");
/*        caps.setCapability("app","/Users/sankethcs/Downloads/CREDQA.app");*/
        caps.setCapability("deviceName","iPhone");

/*        caps.setCapability("appPackage", "com.dreamplug.androidapp.dev");
        caps.setCapability("appActivity","com.dreamplug.androidapp.SplashActivity");*/
        caps.setCapability("noReset","false");
        driver = new IOSDriver(new URL("http://0.0.0.0:4723/wd/hub"),caps);
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void test(){
        driver.launchApp();

    }

}