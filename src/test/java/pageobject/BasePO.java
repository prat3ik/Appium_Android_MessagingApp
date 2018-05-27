/*
 * (C) Copyright 2018 by Pratik Patel (https://github.com/prat3ik/)
 */
package pageobject;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import manager.AndroidDriverManager;
import org.openqa.selenium.support.PageFactory;
import utills.WaitUtils;

import java.util.concurrent.TimeUnit;

public class BasePO {

    WaitUtils waitUtils;

    public BasePO() {
        waitUtils = new WaitUtils();
        loadProperties();
        initElements();
    }

    private void initElements() {
        PageFactory.initElements(new AppiumFieldDecorator(getDriver(), 5, TimeUnit.SECONDS), this);
    }

    private void loadProperties() {

    }

    protected AndroidDriver<AndroidElement> getDriver() {
        return AndroidDriverManager.getThreadLocalDriver();
    }

}
