/*
 * (C) Copyright 2018 by Pratik Patel (https://github.com/prat3ik/)
 */
package testcases;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import manager.AndroidDriverManager;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utills.WaitUtils;

import java.lang.reflect.Method;

public class BaseTestCase {

    WaitUtils waitUtils = new WaitUtils();

    @BeforeSuite
    public void beforeSuite() {

    }

    @BeforeClass
    public void beforeClass() {
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(final ITestContext context, Method m) {
        Thread.currentThread().setName(m.getName() + "_" + Thread.currentThread().getId());
        System.out.println("Thread:'" + Thread.currentThread().getName() + "' is executing");
        AndroidDriverManager.createThreadLocalWebDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(final ITestResult result) {
        String fileName = result.getTestClass().getName() + "_" + result.getName();
        System.out.println("Test Case: [" + fileName + "] executed..!");
        quitWebDriver();
    }

    @AfterClass
    public void afterClass() {

    }

    @AfterSuite
    public void afterSuite() {

    }

    private void quitWebDriver() {
        try {
            this.getDriver().quit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        AndroidDriverManager.setThreadLocalWebDriver(null);

    }

    protected void waitTillAppIsLaunched() {
        waitUtils.staticWait(5000);
    }

    protected AndroidDriver<AndroidElement> getDriver() {
        return AndroidDriverManager.getThreadLocalDriver();
    }

}