package com.mobgen.test;

import com.mobgen.framework.AppiumServer;
import com.mobgen.framework.ConfigData;
import com.mobgen.framework.Driver;
import com.mobgen.pom.LoginPage;
import com.mobgen.pom.PageOne;
import com.mobgen.pom.PageTwo;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;

/**
 * Created by srilakshmishankar
 */
public class BaseTest {
    private static final Logger LOG = LoggerFactory.getLogger(BaseTest.class);
    private static String temp = "";
    private static int count = 0;

    @BeforeSuite
    public static void beforeSuite() {
        killAllRunningNode();
        AppiumServer.buildAppiumService();
        AppiumServer.startAppiumServer();
        try {
            LOG.info("Waiting for 5 seconds after starting server...");
            Thread.sleep(5000);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }

    @BeforeMethod
    public static void beforeMethod() {
        Driver.launchApp();

        new LoginPage();
        new PageOne();
        new PageTwo();

        LoginPage.waitForLoginForm();
    }

    @AfterMethod
    public static void afterMethod(ITestResult result) {
        if (!result.isSuccess()) {
            File srcFile = Driver.getDriver().getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.forceMkdir(new File(ConfigData.getPathToScreenshots()));
                String imageName = result.getMethod().getTestClass().getName() + "_" + result.getMethod().getMethodName();
                if (temp.equals(imageName)) {
                    imageName = imageName + "_" + (count + 1);
                } else {
                    count = 0;
                }
                FileUtils.copyFile(srcFile, new File(ConfigData.getPathToScreenshots() + "/" + imageName + ".png"));
                temp = imageName;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Driver.getDriver().quit();
    }

    @AfterSuite
    public static void afterSuite() {
        AppiumServer.stopAppiumServer();
    }


    public static void killAllRunningNode() {
        String[] command = {"/usr/bin/killall", "-KILL", "node"};
        try {
            Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
        LOG.info("Node is killed");
    }

    @DataProvider(name = "ValidLogin")
    public static Object[][] validLogin() {

        return new Object[][]{{"qa@mobgen.com", "tester"}};

    }

    @DataProvider(name = "InvalidEmail")
    public static Object[][] invalidEmail() {
        return new Object[][]{{"qa", "tester"}, {"qa@", "tester"}, {"@", "tester"}};
    }

    @DataProvider(name = "InvalidPassword")
    public static Object[][] invalidPassword() {
        return new Object[][]{{"qa@mobgen.com", "test"}, {"qa@mobgen.comm", "     "}};
    }

    @DataProvider(name = "AddValidItems")
    public static Object[][] addValidItems() {
        return new Object[][]{{"qa@mobgen.com", "tester", "Name1", "Job1"}};
    }

    @DataProvider(name = "AddInvalidItems")
    public static Object[][] addInvalidItems() {
        return new Object[][]{{"qa@mobgen.com", "tester", "     ", "     "}};
    }

    @DataProvider(name = "SearchDataTabOne")
    public static Object[][] searchDataTabOne() {
        return new Object[][]{{"qa@mobgen.com", "tester", "Pam", "Office manager"}};
    }

    @DataProvider(name = "SearchDataTabTwo")
    public static Object[][] searchDataTabOTwo() {
        return new Object[][]{{"qa@mobgen.com", "tester", "Bat", "Black"}};
    }

    @DataProvider(name = "EditNewDataTabTwo")
    public static Object[][] editNewDataTabOTwo() {
        return new Object[][]{{"qa@mobgen.com", "tester", "Fish", "Blue", "ABC", "XYZ"}};
    }

    @DataProvider(name = "EditAppendDataTabTwo")
    public static Object[][] editAppendDataTabOTwo() {
        return new Object[][]{{"qa@mobgen.com", "tester", "Fish", "Blue", "ABC", "XYZ", "FishABC", "BlueXYZ"}};
    }
}
