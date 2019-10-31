package com.demo.ui.test.mobile_web;

import com.demo.ui.test.pageobjects.NavigationUI;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWNavigationUI extends NavigationUI {

    static {
        MY_LISTS_LINK = By.id("Saved");
    }

    public MWNavigationUI(RemoteWebDriver driver) {
        super(driver);
    }
}
