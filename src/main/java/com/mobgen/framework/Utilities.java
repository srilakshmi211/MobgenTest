package com.mobgen.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by srilakshmishankar
 */
public class Utilities {
    private static final Logger log = LoggerFactory.getLogger(Utilities.class);

    public static void waitTillElementVisible(int seconds, By by) {
        WebDriverWait webDriverWait = new WebDriverWait(Driver.getDriver(), seconds);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public static void waitTillElementToBeClickable(int seconds, By by) {
        WebDriverWait webDriverWait = new WebDriverWait(Driver.getDriver(), seconds);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public static void waitTillElementNotVisible(int seconds, By by) {
        WebDriverWait webDriverWait = new WebDriverWait(Driver.getDriver(), seconds);
        webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }
}
