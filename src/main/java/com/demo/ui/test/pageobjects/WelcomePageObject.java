package com.demo.ui.test.pageobjects;

import com.demo.ui.test.MainPageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WelcomePageObject extends MainPageObject {

    public WelcomePageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public static final By SKIP = By.id("Skip");


    public void clickSkip(){
        waitForElementAndClick(SKIP, 5);
    }
}
