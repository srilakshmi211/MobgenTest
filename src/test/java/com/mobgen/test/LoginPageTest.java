package com.mobgen.test;

import com.mobgen.pom.LoginPage;
import com.mobgen.pom.PageOne;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by srilakshmishankar.
 */
public class LoginPageTest extends BaseTest {

    @Test
    public void testLoginPageDisplayed() {
        Assert.assertEquals(LoginPage.isLoginFormDisplayed(), true);
    }

    @Test
    public void testSignInButtonDisabledOnAppLaunch() {
        Assert.assertEquals(LoginPage.isSignInButtonEnabled(), false);
    }

    @Test (dataProvider = "ValidLogin")
    public void testLoginWithValidCredentials(String email, String password) {
        LoginPage.appLogin(email,password);
        Assert.assertEquals(PageOne.isPageOneDisplayed(), true);
    }

    @Test (dataProvider = "InvalidEmail")
    public void testLoginWithInvalidEmail(String email, String password) {
        LoginPage.testLogin(email,password);
        Assert.assertEquals(LoginPage.isLoginProgressDisplayed(), false);
    }


    @Test (dataProvider = "InvalidPassword")
    public void testLoginWithInvalidPassword(String email, String password) {
        LoginPage.testLogin(email,password);
        Assert.assertEquals(LoginPage.isLoginProgressDisplayed(), false);
    }

    @Test (dataProvider = "ValidLogin")
    public void testSignInButtonDisabledOnEmailFieldClear(String email, String password) {
        LoginPage.setEmail(email);
        LoginPage.setPassword(password);
        LoginPage.getEmail().clear();
        Assert.assertEquals(LoginPage.isSignInButtonEnabled(), false);
    }
}
