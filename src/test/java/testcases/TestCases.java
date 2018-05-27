/*
 * (C) Copyright 2018 by Pratik Patel (https://github.com/prat3ik/)
 */
package testcases;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobject.AndroidMessageAppPO;

public class TestCases extends BaseTestCase {

    @Test
    public void verifyUserCanSendMessage() {
        final String phoneNo = "00011122233";
        final String messageText = "Hello, there";

        final String sendToIconId = "com.google.android.apps.messaging:id/contact_picker_create_group";
        final String smsTextFieldId = "com.google.android.apps.messaging:id/compose_message_text";
        final String smsButtonXpath = "//android.widget.LinearLayout[@content-desc='Send SMS']/android.widget.LinearLayout";
        AndroidDriver<AndroidElement> driver = getDriver();

        AndroidMessageAppPO po = new AndroidMessageAppPO();
        po.clickOnNextButton();
        po.clickOnMessageDialogOKButton();
        po.clickOnStartNewConversationButton();
        po.typeInToTextField(phoneNo);

        po.clickOnSendToButton();

        po.typeInSMSTextField(messageText);
        driver.findElementById(smsTextFieldId).sendKeys(messageText);
        po.clickOnSMSButton();

        Assert.assertTrue(po.isMessageSent(), "Message:'" + messageText + "' is not beint sent!");
    }

}