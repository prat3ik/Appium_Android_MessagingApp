/*
 * (C) Copyright 2018 by Pratik Patel (https://github.com/prat3ik/)
 */
package pageobject;

import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import utills.AssertionUtils;

public class AndroidMessageAppPO extends BasePO {

    @AndroidFindBy(id = "next")
    AndroidElement nextButton;

    public void clickOnNextButton() {
        nextButton.click();
    }

    @AndroidFindBy(id = "android:id/button1")
    AndroidElement messageDialogOKButton;

    public void clickOnMessageDialogOKButton() {
        messageDialogOKButton.click();
    }

    @AndroidFindBy(id = "com.google.android.apps.messaging:id/start_new_conversation_button")
    AndroidElement startNewConversationButton;

    public void clickOnStartNewConversationButton() {
        startNewConversationButton.click();
    }

    @AndroidFindBy(id = "com.google.android.apps.messaging:id/recipient_text_view")
    AndroidElement toTextField;

    public void typeInToTextField(String text) {
        toTextField.sendKeys(text);
        waitUtils.waitForElementToBeVisible(sendToButton, getDriver());

    }

    @AndroidFindBy(id = "com.google.android.apps.messaging:id/contact_picker_create_group")
    AndroidElement sendToButton;

    public void clickOnSendToButton() {
        sendToButton.click();
        waitUtils.waitForElementToBeVisible(smsButton, getDriver());
    }

    @AndroidFindBy(id = "com.google.android.apps.messaging:id/compose_message_text")
    AndroidElement smsTextFeild;

    public void typeInSMSTextField(String text) {
        smsTextFeild.sendKeys(text);
    }

    @AndroidFindBy(xpath = "//android.widget.LinearLayout[@content-desc='Send SMS']/android.widget.LinearLayout")
    AndroidElement smsButton;

    public void clickOnSMSButton() {
        smsButton.click();
    }

    @AndroidFindBy(id = "com.google.android.apps.messaging:id/message_text_and_info")
    AndroidElement sentMessageLayout;

    public boolean isMessageSent(){
        return AssertionUtils.isElementDisplayed(sentMessageLayout);
    }


}
