import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;

import static io.appium.java_client.remote.IOSMobileCapabilityType.USE_PREBUILT_WDA;

public class ioSampleTest {

    public AndroidDriver<MobileElement> driver;
    public WebDriverWait wait;

    //Elements
    String profileName_oldapp="Sanketh Sunkad", profileName_newapp;
    String old="/Users/sankethcs/Downloads/CRED-2.1.42-RC7-qa.apk";
    String newapp = "/Users/sankethcs/Downloads/CRED-3.0.4-RC6-qa.apk";

    @BeforeMethod
    public void setup () throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("udid", "emulator-5554"); //DeviceId from "adb devices" command
        caps.setCapability("platformName", "Android");
        caps.setCapability("skipUnlock","true");
        caps.setCapability("appPackage", "com.dreamplug.androidapp.dev");
        caps.setCapability("appActivity","com.dreamplug.androidapp.SplashActivity");
        caps.setCapability("noReset","false");
        caps.setCapability(USE_PREBUILT_WDA, true);
        driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"),caps);
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void sample(){
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.android.permissioncontroller:id/permission_allow_button")));
        MobileElement allo = driver.findElement(By.id("com.android.permissioncontroller:id/permission_allow_button"));
        allo.click();
    }

    @Test
    public void sampolenw(){
        WebDriverWait wait = new WebDriverWait(driver,30);
        driver.installApp(newapp);
        driver.launchApp();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.android.permissioncontroller:id/permission_allow_button")));
        MobileElement allo = driver.findElement(By.id("com.android.permissioncontroller:id/permission_allow_button"));
        allo.click();
    }
    public void oldapp() {
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.android.permissioncontroller:id/permission_allow_button")));

        MobileElement snp=driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"referrals\"]/android.view.ViewGroup"));
        MobileElement profileName=driver.findElement(By.id("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]/android.widget.TextView[1]"));
        profileName_oldapp = profileName.getText();
        driver.getCapabilities().getCapability("appVersion");

        Assert.assertTrue(snp.isDisplayed(), "SNP not supposed to be in old app");

        Assert.assertEquals(profileName.getText(), "profile name in old app");
    }
    public void newapp () {
        driver.installApp(newapp);
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]/android.widget.TextView[1]")));
        MobileElement snp=driver.findElement(By.id("//android.view.ViewGroup[@content-desc=\"referrals\"]/android.view.ViewGroup"));
        MobileElement profileName=driver.findElement(By.id("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]/android.widget.TextView[1]"));
        profileName_newapp = profileName.getText();
        Assert.assertFalse(snp.isDisplayed(),"Scan and pay not visible in new app");
        if(!profileName_newapp.equals(profileName_oldapp)) Assert.assertEquals(profileName.getText(),"need proper check on app installation");

    }

    @AfterClass
    public void teardown(){
        driver.quit();
    }
}
