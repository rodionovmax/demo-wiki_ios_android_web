package com.demo.ui.test.pageobjects;

import com.demo.ui.test.MainPageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class NavigationUI extends MainPageObject {

    public NavigationUI(RemoteWebDriver driver) {
        super(driver);
    }

    public static By MY_LISTS_LINK;

    public void ClickMyList() {
        waitForElementAndClick(MY_LISTS_LINK, 5);
    }
}
