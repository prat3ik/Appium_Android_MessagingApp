/*
 * (C) Copyright 2018 by Pratik Patel (https://github.com/prat3ik/)
 */
package manager;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.remote.DesiredCapabilities;
import utills.PropertyUtils;
import utills.WaitUtils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class AndroidDriverManager {

    public static ThreadLocal<AndroidDriver<AndroidElement>> webDriver = new ThreadLocal<AndroidDriver<AndroidElement>>();
    protected WaitUtils waitUtils = new WaitUtils();
    public AppiumDriverLocalService service;

    public final static String APPIUM_SERVER_URL = PropertyUtils.getProperty("appium.server.url");
    public final static String PLATFORM_NAME = PropertyUtils.getProperty("android.platform");
    public final static String PLATFORM_VERSION = PropertyUtils.getProperty("android.platform.version");
    public final static String APP_NAME = PropertyUtils.getProperty("android.app.name");
    public final static String APP_RELATIVE_PATH = PropertyUtils.getProperty("android.app.location") + APP_NAME;
    public final static String APP_PATH = getAbsolutePath(APP_RELATIVE_PATH);
    public final static String DEVICE_NAME = PropertyUtils.getProperty("android.device.name");
    public final static String APP_PACKAGE_NAME = PropertyUtils.getProperty("android.app.packageName");
    public final static String APP_ACTIVITY_NAME = PropertyUtils.getProperty("android.app.activityName");
    public final static String APP_FULL_RESET = PropertyUtils.getProperty("android.app.full.reset");
    public final static int IMPLICIT_WAIT = PropertyUtils.getIntegerProperty("implicitWait", 30);


    public static DesiredCapabilities getAndroidCaps() {
        DesiredCapabilities caps = DesiredCapabilities.android();
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, DEVICE_NAME);
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, PLATFORM_NAME);
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, PLATFORM_VERSION);
        caps.setCapability(MobileCapabilityType.APP, APP_PATH);
        caps.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, APP_PACKAGE_NAME);
        caps.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, APP_ACTIVITY_NAME);
        caps.setCapability(MobileCapabilityType.FULL_RESET, APP_FULL_RESET);
        return caps;
    }

    public static AndroidDriver<AndroidElement> getThreadLocalDriver() {
        AndroidDriver<AndroidElement> driver = webDriver.get();
        if (driver == null) {
            createThreadLocalWebDriver();
            driver = webDriver.get();
        }
        return driver;
    }

    public static void createThreadLocalWebDriver() {
        AndroidDriver<AndroidElement> driver = null;
        try {
            driver = new AndroidDriver<AndroidElement>(new URL(APPIUM_SERVER_URL), getAndroidCaps());
        } catch (Exception e) {
            try {
                driver = new AndroidDriver<AndroidElement>(new URL(APPIUM_SERVER_URL), getAndroidCaps());
            } catch (MalformedURLException e1) {
                System.out.println("Android Driver is not created..!, Please check capabilitis or make sure Appium Server is running.");
            }
            return;
        }
        setTimeOuts(driver);
        webDriver.set(driver);
    }

    private static String getAbsolutePath(String appRelativePath) {
        File file = new File(appRelativePath);
        return file.getAbsolutePath();
    }

    private static void setTimeOuts(AndroidDriver<AndroidElement> driver) {
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
    }

    public static void setThreadLocalWebDriver(final AndroidDriver driver) {
        webDriver.set(driver);
    }

}
