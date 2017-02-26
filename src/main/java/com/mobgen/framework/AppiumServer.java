package com.mobgen.framework;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * Created by srilakshmishankar
 */

public class AppiumServer {
    private static final Logger LOG = LoggerFactory.getLogger(AppiumServer.class);
    private static AppiumDriverLocalService appiumDriverLocalService;

    public static void buildAppiumService() {
        AppiumServiceBuilder builder = new AppiumServiceBuilder();
        builder.usingPort(ConfigData.getAppiumPort())
                .usingDriverExecutable(new File(ConfigData.getPathToNode()))
                .withAppiumJS(new File(ConfigData.getPathToAppium()));

        appiumDriverLocalService = builder.build();
    }

    public static void startAppiumServer() {
        if (appiumDriverLocalService != null) {
            appiumDriverLocalService.start();
            LOG.info("Server Status : " + appiumDriverLocalService.isRunning());
        }
    }

    public static void stopAppiumServer() {
        if (appiumDriverLocalService != null) {
            appiumDriverLocalService.stop();
            LOG.info("Server Status : " + appiumDriverLocalService.isRunning());
        }
    }
}
