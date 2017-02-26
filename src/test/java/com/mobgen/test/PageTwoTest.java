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
public class PageTwoTest extends BaseTest {

    @Test (dataProvider = "ValidLogin")
    public void testClickOnTabTwo(String email,String password) {
        LoginPage.appLogin(email,password);
        PageTwo.clickOnTabTwo();
        Assert.assertEquals(PageTwo.isPageTwoDisplayed(), true);
    }

    @Test (dataProvider = "ValidLogin")
    public void testSwipeTabOneToTwo(String email,String password) {
        LoginPage.appLogin(email,password);
        PageOne.swipeRightToLeft();
        Assert.assertEquals(PageTwo.isPageTwoDisplayed(), true);
    }

    @Test (dataProvider = "EditNewDataTabTwo")
    public void testEditItemWithNewInput(String email,String password, String animal,
                                         String color, String newAnimal, String newColor) {
        LoginPage.appLogin(email,password);
        PageTwo.clickOnTabTwo();
        PageTwo.editItemWithNewString(animal,color,newAnimal,newColor);
        Assert.assertEquals(PageOne.searchItemInList(newAnimal,newColor), true);
    }

    @Test (dataProvider = "EditAppendDataTabTwo")
    public void testEditItemWithAppend(String email,String password,String animal, String color,
                                       String appendAnimal, String appendColor, String newAnimal,
                                       String newColor) {
        LoginPage.appLogin(email,password);
        PageTwo.clickOnTabTwo();
        PageTwo.editItemWithAppend(animal,color,appendAnimal,appendColor);
        Assert.assertEquals(PageOne.searchItemInList(newAnimal,newColor), true);
    }

    @Test (dataProvider = "ValidLogin")
    public void testClickBackFromPageTwo(String email,String password) {
        LoginPage.appLogin(email,password);
        PageTwo.clickOnTabTwo();
        Driver.getDriver().pressKeyCode(AndroidKeyCode.BACK);
        Assert.assertEquals(LoginPage.isLoginFormDisplayed(), false);
    }

    @Test (dataProvider = "SearchDataTabTwo")
    public void testEditPopupContent(String email,String password, String animal, String color) {
        LoginPage.appLogin(email,password);
        PageTwo.clickOnTabTwo();
        PageOne.clickOnListItem(animal,color);
        Assert.assertEquals(PageOne.getTextAtEditTextFieldOne(), animal);
        Assert.assertEquals(PageOne.getTextAtEditTextFieldTwo(), color);
    }

    @Test (dataProvider = "ValidLogin")
    public void testSortList(String email,String password){
        LoginPage.appLogin(email,password);
        PageTwo.clickOnTabTwo();
        Assert.assertEquals(PageTwo.checkItemSort(), true);
    }
}
