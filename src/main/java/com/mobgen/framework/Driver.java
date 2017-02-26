package com.mobgen.framework;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by srilakshmishankar
 */

public class Driver {
    private static AndroidDriver<MobileElement> driver;
    private static final Logger LOG = LoggerFactory.getLogger(Driver.class);

    private static DesiredCapabilities getDesiredCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, ConfigData.getDeviceOS());
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, ConfigData.getDeviceName());
        capabilities.setCapability(MobileCapabilityType.APP, ConfigData.getPathToApk());
        return capabilities;
    }

    public static AndroidDriver<MobileElement> launchApp() {
        String serverURL = ConfigData.getAppiumServerUrl(ConfigData.getAppiumPort());
        try {
            driver = new AndroidDriver<MobileElement>(new URL(serverURL), getDesiredCapabilities());
        } catch (MalformedURLException e) {
            LOG.error("Malformed URL exception : " + e.getMessage());
        }
        return driver;
    }

    public static AndroidDriver<MobileElement> getDriver() {
        return driver;
    }
}
