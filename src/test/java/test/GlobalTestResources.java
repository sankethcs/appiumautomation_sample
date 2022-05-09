package test;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.*;
import io.appium.java_client.AppiumDriver;
import java.net.URL;
import org.openqa.selenium.remote.DesiredCapabilities;
import lombok.Getter;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.remote.DesiredCapabilities;

@Getter
public class GlobalTestResources {
    private AppiumDriver<?> driver;
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability("appPackage", "com.dreamplug.androidapp.dev”);
    capabilities.setCapability("appActivity", "com.dreamplug.androidapp.SplashActivity");
    capabilities.setCapability("noReset", true);
}