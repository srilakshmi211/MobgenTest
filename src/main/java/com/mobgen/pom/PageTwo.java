package com.mobgen.pom;

import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by srilakshmishankar
 */
public class PageTwo extends PageOne {
    private static final String SORT_BUTTON = "com.mobgen.interview.mobgeninterviewtest:id/floating_sort_button";
    private static final String TAB_TWO = "//*[@class='android.support.v7.app.ActionBar$Tab'][@index=1]";
    private static final Logger LOG = LoggerFactory.getLogger(PageOne.class);

    public static MobileElement getSortButton() {
        return driver.findElement(By.id(SORT_BUTTON));
    }

    public static MobileElement getTabTwo() {
        return driver.findElement(By.xpath(TAB_TWO));
    }

    public static boolean isPageTwoDisplayed() {
        if (getTabTwo().isSelected()) {
            return true;
        }
        return false;
    }

    public static void tapOnSortButton() {
        getSortButton().click();
    }

    public static void editItemWithNewString(String header, String subheader, String newHeader, String newSubheader) {
        clickOnListItem(header, subheader);
        setTextInEditTextFieldOne(newHeader);
        setTextInEditTextFieldTwo(newSubheader);
        clickOnPopupButtonOne();
    }

    public static void editItemWithAppend(String header, String subheader, String newHeader, String newSubheader) {
        clickOnListItem(header, subheader);
        getEditTextFieldOne().sendKeys(newHeader);
        getEditTextFieldTwo().sendKeys(newSubheader);
        clickOnPopupButtonOne();
    }

    public static void clickOnTabTwo() {
        getTabTwo().click();
    }

    public static boolean checkItemSort() {
        tapOnSortButton();
        int flag = 0;
        int sortFlag = 0;
        List<String> pageList1 = new ArrayList<String>();
        List<String> pageList2 = new ArrayList<String>();
        List<String> pageList3 = new ArrayList<String>();
        scrollToTopMost();
        int itemCount = getNumberOfItemsInList();
        for (int i = 0; i < itemCount; i++) {
            try {
                pageList1.add(getListHeaderText(i));
            } catch (NoSuchElementException e) {
            }

            if (i == itemCount - 1) {
                flag = 1;
                sortFlag = 0;
                pageList3.addAll(pageList1);
                Collections.sort(pageList1);
                if (pageList1.equals(pageList3)) {
                    sortFlag = 1;
                } else {
                    break;
                }
            }

            if (!(pageList2.equals(pageList3)) && (i == itemCount - 1)) {
                pageList2.clear();
                scrollToBottom();
                pageList2.addAll(pageList3);
                i = 0;
                flag = 0;
                pageList1.clear();
                pageList3.clear();
            }

            if (flag == 1) {
                break;
            }
        }

        if (sortFlag == 1) {
            return true;
        }
        return false;

    }
}



