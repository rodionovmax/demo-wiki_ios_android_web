package com.demo.ui.test.pageobjects;

import com.demo.ui.test.MainPageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPageObject extends MainPageObject {

    public LoginPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public static final By USERNAME_FIELD = By.xpath("//XCUIElementTypeTextField[@value='enter username']");
    public static final By PASSWORD_FIELD = By.xpath("//XCUIElementTypeSecureTextField[@value='enter password']");
    public static final By LOGIN_BUTTON = By.id("Log in");

    public void loginIntoAccount(String username, String password) {
        waitForElementAndSendKeys(USERNAME_FIELD, username, 5);
        waitForElementAndSendKeys(PASSWORD_FIELD, password, 5);
        waitForElementAndClick(LOGIN_BUTTON, 5);
    }
}
