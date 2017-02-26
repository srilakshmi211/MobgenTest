package com.mobgen.pom;

import com.mobgen.framework.Driver;
import com.mobgen.framework.Utilities;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by srilakshmishankar
 */

public class PageOne {
    private static final String MAIN_VIEW = "com.mobgen.interview.mobgeninterviewtest:id/viewpager";
    private static final String TAB_ONE = "//*[@class='android.support.v7.app.ActionBar$Tab'][@index=0]";
    private static final String LIST_HEADER = "com.mobgen.interview.mobgeninterviewtest:id/name_textview";
    private static final String LIST_SUBHEADER = "com.mobgen.interview.mobgeninterviewtest:id/job_textview";
    private static final String DELETE_OR_EDIT_BUTTON = "com.mobgen.interview.mobgeninterviewtest:id/imageHolder";
    private static final String ADD_BUTTON = "com.mobgen.interview.mobgeninterviewtest:id/floating_add_button";
    private static final String RECYCLER_VIEW = "com.mobgen.interview.mobgeninterviewtest:id/recycler_view";
    private static final String POPUP = "com.mobgen.interview.mobgeninterviewtest:id/parentPanel";
    private static final String POPUP_TITLE = "com.mobgen.interview.mobgeninterviewtest:id/alertTitle";
    private static final String POPUP_CONTENT = "android:id/message";
    private static final String POPUP_BUTTON_ONE = "android:id/button1";
    private static final String POPUP_BUTTON_TWO = "android:id/button2";
    private static final String EDIT_TEXT_FIELD_ONE = "//*[@class='android.widget.EditText'][@index=0]";
    private static final String EDIT_TEXT_FIELD_TWO = "//*[@class='android.widget.EditText'][@index=1]";


    public static AndroidDriver<MobileElement> driver;

    public PageOne() {
        driver = Driver.getDriver();
    }

    public static boolean isPageOneDisplayed() {
        if (getPageOne().isSelected()) {
            return true;
        }
        return false;
    }

    public static void waitForTabOne() {
        Utilities.waitTillElementVisible(5, By.xpath(PageOne.TAB_ONE));
    }

    public static MobileElement getPageOne() {
        return driver.findElement(By.xpath(TAB_ONE));
    }

    public static MobileElement getAddButton() {
        return driver.findElement(By.id(ADD_BUTTON));
    }

    public static MobileElement getDeleteButton() {
        return driver.findElement(By.id(DELETE_OR_EDIT_BUTTON));
    }

    public static MobileElement getListHeader() {
        return driver.findElement(By.id(LIST_HEADER));
    }

    public static MobileElement getListSubHeader() {
        return driver.findElement(By.id(LIST_SUBHEADER));
    }

    public static MobileElement getRecyclerView() {
        return driver.findElement(By.id(RECYCLER_VIEW));
    }

    public static int getNumberOfItemsInList() {
        MobileElement parent = getRecyclerView();
        List<MobileElement> child = parent.findElements(By.className("android.widget.FrameLayout"));
        return child.size();
    }

    public static MobileElement getMainView() {
        return driver.findElement(By.id(MAIN_VIEW));
    }


    public static String getPopupContent() {
        return driver.findElement(By.id(POPUP_CONTENT)).getText();
    }

    public static MobileElement getPopup() {
        return driver.findElement(By.id(POPUP));
    }

    public static String getPopupTitle() {
        return driver.findElement(By.id(POPUP_TITLE)).getText();
    }

    public static MobileElement getEditTextFieldOne() {
        return driver.findElement(By.xpath(EDIT_TEXT_FIELD_ONE));
    }

    public static MobileElement getEditTextFieldTwo() {
        return driver.findElement(By.xpath(EDIT_TEXT_FIELD_TWO));
    }

    public static String getTextAtEditTextFieldOne() {
        return getEditTextFieldOne().getText();
    }

    public static String getTextAtEditTextFieldTwo() {
        return getEditTextFieldTwo().getText();
    }

    public static MobileElement getPopupButtonOne() {
        return driver.findElement(By.id(POPUP_BUTTON_ONE));
    }

    public static MobileElement getPopupButtonTwo() {
        return driver.findElement(By.id(POPUP_BUTTON_TWO));
    }

    public static String getPopupButtonOneName() {
        return getPopupButtonOne().getText();
    }

    public static String getPopupButtonTwoName() {
        return getPopupButtonTwo().getText();
    }


    public static void setTextInEditTextFieldOne(String text) {
        getEditTextFieldOne().clear();
        getEditTextFieldOne().sendKeys(text);
    }

    public static void setTextInEditTextFieldTwo(String text) {
        getEditTextFieldTwo().clear();
        getEditTextFieldTwo().sendKeys(text);
    }

    public static void clickAddButton() {
        getAddButton().click();
    }

    public static void clickOnPopupButtonOne() {
        getPopupButtonOne().click();
    }

    public static void clickOnPopupButtonTwo() {
        getPopupButtonTwo().click();
    }

    public static int searchItemIndex(String header, String subheader) {
        int flag = 0;
        List<String> pageList1 = new ArrayList<String>();
        List<String> pageList2 = new ArrayList<String>();
        scrollToTopMost();
        int itemCount = getNumberOfItemsInList();
        for (int i = 0; i < itemCount; i++) {
            try {
                String headerInlist = getListHeaderText(i);
                String subheaderInList = getListSubheaderText(i);

                if (headerInlist.equals(header) && subheaderInList.equals(subheader)) {
                    return i;
                }

                pageList1.add(headerInlist + " " + subheaderInList);
            } catch (NoSuchElementException e) {
            }

            if (i == itemCount - 1) {
                flag = 1;
            }

            if (!(pageList2.equals(pageList1)) && (i == itemCount - 1)) {
                pageList2.clear();
                scrollToBottom();
                pageList2.addAll(pageList1);
                i = 0;
                flag = 0;
                pageList1.clear();
            }

            if (flag == 1) {
                break;
            }
        }
        return -1;
    }

    public static boolean searchItemInList(String header, String subheader) {
        if (searchItemIndex(header, subheader) != -1) {
            return true;
        } else
            return false;
    }

    public static boolean addItemToListAndVerify(String header, String subheader) {
        boolean result;
        addItemToList(header, subheader);
        if (searchItemInList(header, subheader)) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    public static void addItemToList(String header, String subheader) {
        Utilities.waitTillElementVisible(5, By.xpath(TAB_ONE));
        clickAddButton();
        setTextInEditTextFieldOne(header);
        setTextInEditTextFieldTwo(subheader);
        clickOnPopupButtonOne();
    }

    public static int topY() {
        return getRecyclerView().getLocation().getY();

    }

    public static int topYOffset() {
        return (topY() + 100);

    }

    public static int bottomY() {
        return (topY() + getRecyclerView().getSize().getHeight() - 100);

    }

    public static int leftX() {
        return getRecyclerView().getLocation().getX();
    }

    public static int leftXOffset() {
        return (leftX() + 100);
    }

    public static int rightX() {
        return (leftX() + getRecyclerView().getSize().getWidth() - 100);
    }

    public static int centerX() {
        return (getRecyclerView().getLocation().getX() + (getRecyclerView().getSize().getWidth() / 2));
    }

    public static int centerY() {
        return (getRecyclerView().getLocation().getY() + (getRecyclerView().getSize().getHeight() / 2));
    }

    public static void swipeRightToLeft() {
        driver.swipe(rightX(), centerY(), leftXOffset(), centerY(), 1000);
    }

    public static void swipeLeftToRight() {
        driver.swipe(leftXOffset(), centerY(), rightX(), centerY(), 1000);
    }


    public static void scrollToTop() {
        driver.swipe(centerX(), topYOffset(), centerX(), bottomY(), 1000);
    }

    public static void scrollToBottom() {
        driver.swipe(centerX(), bottomY(), centerX(), topYOffset(), 1000);
    }


    public static void scrollToTopMost() {
        int flag = 0;
        List<String> pageList1 = new ArrayList<String>();
        List<String> pageList2 = new ArrayList<String>();
        scrollToTop();
        int itemCount = getNumberOfItemsInList();
        for (int i = 0; i < itemCount; i++) {
            try {
                String headerInlist = getListHeaderText(i);
                String subheaderInList = getListSubheaderText(i);
                pageList1.add(headerInlist + " " + subheaderInList);
            } catch (NoSuchElementException e) {
            }

            if (i == itemCount - 1) {
                flag = 1;
            }

            if (!(pageList2.equals(pageList1)) && (i == itemCount - 1)) {
                pageList2.clear();
                scrollToTop();
                pageList2.addAll(pageList1);
                i = 0;
                flag = 0;
                pageList1.clear();
            }

            if (flag == 1) {
                break;
            }

        }

    }

    public static void scrollToBottomMost() {
        int flag = 0;
        List<String> pageList1 = new ArrayList<String>();
        List<String> pageList2 = new ArrayList<String>();
        scrollToBottom();
        int itemCount = getNumberOfItemsInList();
        for (int i = 0; i < itemCount; i++) {
            try {
                String headerInlist = getListHeaderText(i);
                String subheaderInList = getListSubheaderText(i);
                pageList1.add(headerInlist + " " + subheaderInList);
            } catch (NoSuchElementException e) {
            }

            if (i == itemCount - 1) {
                flag = 1;
            }

            if (!(pageList2.equals(pageList1)) && i == itemCount - 1) {
                pageList2.clear();
                scrollToBottom();
                pageList2.addAll(pageList1);
                i = 0;
                flag = 0;
                pageList1.clear();
            }

            if (flag == 1) {
                break;
            }
        }
    }

    public static String getListHeaderText(int i) {
        MobileElement listView = getRecyclerView();
        List<MobileElement> sublist = listView.findElements(By.className("android.widget.FrameLayout"));
        MobileElement childView = sublist.get(i);
        return childView.findElement(By.className("android.widget.LinearLayout")).findElement(By.id(LIST_HEADER)).getText();
    }

    public static String getListSubheaderText(int i) {
        MobileElement listView = getRecyclerView();
        List<MobileElement> sublist = listView.findElements(By.className("android.widget.FrameLayout"));
        MobileElement childView = sublist.get(i);
        return childView.findElement(By.className("android.widget.LinearLayout")).findElement(By.id(LIST_SUBHEADER)).getText();
    }

    public static String[] getLastElementInList() {
        scrollToBottomMost();
        String header = getListHeaderText(getNumberOfItemsInList() - 1);
        String subheader = getListSubheaderText(getNumberOfItemsInList() - 1);
        String result[] = {header, subheader};
        return result;
    }

    public static String[] getfirstElementInList() {
        scrollToTopMost();
        String header = getListHeaderText(0);
        String subheader = getListSubheaderText(0);
        String result[] = {header, subheader};
        return result;
    }

    public static void clickOnListItem(String header, String subheader) {
        int index = searchItemIndex(header, subheader);
        MobileElement listview = getRecyclerView();
        List<MobileElement> sublist = listview.findElements(By.className("android.widget.FrameLayout"));
        MobileElement childview = sublist.get(index);
        childview.click();
    }

    public static void deleteItemfromList(String header, String subheader) {
        clickOnListItem(header, subheader);
        clickOnPopupButtonOne();
    }
}
