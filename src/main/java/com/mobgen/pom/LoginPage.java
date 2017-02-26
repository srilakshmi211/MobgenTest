package com.mobgen.pom;

import com.mobgen.framework.Driver;
import com.mobgen.framework.Utilities;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

/**
 * Created by srilakshmishankar
 */
public class LoginPage {
    private static final String LOGIN_FORM = "com.mobgen.interview.mobgeninterviewtest:id/login_form";
    private static final String EMAIL = "com.mobgen.interview.mobgeninterviewtest:id/email";
    private static final String PASSWORD = "com.mobgen.interview.mobgeninterviewtest:id/password";
    private static final String SIGN_IN = "com.mobgen.interview.mobgeninterviewtest:id/email_sign_in_button";
    private static final String LOGIN_PROGRESS = "android.widget.ProgressBar";

    private static AndroidDriver<MobileElement> driver;

    public LoginPage() {
        driver = Driver.getDriver();
    }

    public static MobileElement getLoginForm() {
        return driver.findElement(By.id(LOGIN_FORM));
    }

    public static void waitForLoginForm() {
        Utilities.waitTillElementVisible(5, By.id(LOGIN_FORM));
    }

    public static boolean isLoginFormDisplayed() {
        if (getLoginForm().isDisplayed()) {
            return true;
        }
        return false;
    }

    public static MobileElement getLoginProgress() {
        return driver.findElement(By.className(LOGIN_PROGRESS));
    }

    public static boolean isLoginProgressDisplayed() {
        try {
            if (getLoginProgress().isDisplayed()) {
                return true;
            }
        } catch (NoSuchElementException e) {
        }
        return false;
    }


    public static MobileElement getEmail() {
        return driver.findElement(By.id(EMAIL));
    }

    public static MobileElement getPassword() {
        return driver.findElement(By.id(PASSWORD));
    }

    public static MobileElement getSignInButton() {
        return driver.findElement(By.id(SIGN_IN));
    }

    public static void setEmail(String email) {
        getEmail().sendKeys(email);
    }

    public static void setPassword(String password) {
        getPassword().sendKeys(password);
    }

    public static void clickSignInButton() {
        getSignInButton().click();
    }

    public static boolean isSignInButtonEnabled() {
        return getSignInButton().isEnabled();
    }

    public static void appLogin(String email, String password) {
        waitForLoginForm();
        setEmail(email);
        setPassword(password);
        clickSignInButton();
        PageOne.waitForTabOne();
    }

    public static void testLogin(String email, String password) {
        waitForLoginForm();
        setEmail(email);
        setPassword(password);
        clickSignInButton();
    }
}
