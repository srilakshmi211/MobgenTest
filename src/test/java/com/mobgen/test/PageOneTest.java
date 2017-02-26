package com.mobgen.test;

import com.mobgen.framework.Driver;
import com.mobgen.pom.LoginPage;
import com.mobgen.pom.PageOne;
import com.mobgen.pom.PageTwo;
import io.appium.java_client.android.AndroidKeyCode;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by srilakshmishankar
 */
public class PageOneTest extends BaseTest {

    @Test (dataProvider = "AddValidItems")
    public void testAddItemToList(String email, String password, String name, String job) {
        LoginPage.appLogin(email, password);
        Assert.assertEquals(PageOne.addItemToListAndVerify(name, job), true);
    }

    @Test (dataProvider = "AddInvalidItems")
    public void testAddItemWithEmptyFields(String email, String password, String name, String job) {
        LoginPage.appLogin(email, password);
        Assert.assertEquals(PageOne.addItemToListAndVerify(name, job), false);
    }


    @Test (dataProvider = "AddValidItems")
    public void testDeleteItem(String email, String password, String name, String job) {
        LoginPage.appLogin(email, password);
        PageOne.addItemToList(name, job);
        PageOne.deleteItemfromList(name, job);
        Assert.assertEquals(PageOne.searchItemInList(name, job), false);
    }

    @Test (dataProvider = "ValidLogin")
    public void testSwipeTabTwoToOne(String email, String password) {
        LoginPage.appLogin(email, password);
        PageTwo.clickOnTabTwo();
        PageOne.swipeLeftToRight();
        Assert.assertEquals(PageOne.isPageOneDisplayed(), true);
    }

    @Test (dataProvider = "ValidLogin")
    public void testClickBackFromPageOne(String email, String password) {
        LoginPage.appLogin(email, password);
        PageTwo.clickOnTabTwo();
        Driver.getDriver().pressKeyCode(AndroidKeyCode.BACK);
        Assert.assertEquals(LoginPage.isLoginFormDisplayed(), false);
    }

    @Test (dataProvider = "SearchDataTabOne")
    public void testDeletePopupCancelButton(String email, String password, String name, String job) {
        LoginPage.appLogin(email, password);
        PageOne.clickOnListItem(name, job);
        PageOne.clickOnPopupButtonTwo();
        Assert.assertEquals(PageOne.searchItemInList(name, job), true);
    }
}
