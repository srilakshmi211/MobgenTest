package com.mobgen.framework;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by srilakshmishankar
 */
public class ConfigData {

    private static final Properties properties;
    private static final Logger LOG = LoggerFactory.getLogger(ConfigData.class);

    private static final String PROPERTIES_FILE_NAME = "config.properties";

    static {
        properties = loadProperties();
        properties.putAll(System.getProperties());
    }

    private static Properties loadProperties() {
        InputStream is = null;
        Properties properties = new Properties();
        try {
            is = ConfigData.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE_NAME);
            properties.load(is);
        } catch (IOException e) {
            LOG.error("{} wasn't not found in classpath.", PROPERTIES_FILE_NAME);
        } finally {
            try {
                if (is != null)
                    is.close();
            } catch (IOException ignored) {
            }
        }
        return properties;
    }

    public static String getPathToNode() {
        return getPropertyAndFailIfMissing("path.to.node");
    }

    public static String getPathToAppium() {
        return getPropertyAndFailIfMissing("path.to.appium");
    }


    public static String getPathToScreenshots() {
        return System.getProperty("user.dir") + getPropertyAndFailIfMissing("path.to.screenshots");
    }

    public static String getPathToApk() {
        String pathToApk = getPropertyAndFailIfMissing("path.to.apk");
        return pathToApk.contains("/Users/") ? pathToApk : System.getProperty("user.dir") + pathToApk;
    }

    public static String getAppiumServerUrl(int port) {
        String urlFormat = getPropertyAndFailIfMissing("appium.url.format");
        return String.format(urlFormat, port);
    }

    private static String getPropertyAndFailIfMissing(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException(key + " system property is not defined");
        }
        return value;
    }

    public static int getAppiumPort() {
        return Integer.parseInt(getPropertyAndFailIfMissing("appium.port"));
    }

    public static String getDeviceName() {
        return getPropertyAndFailIfMissing("device.name");
    }

    public static String getDeviceOS() {
        return getPropertyAndFailIfMissing("device.os");
    }

}
