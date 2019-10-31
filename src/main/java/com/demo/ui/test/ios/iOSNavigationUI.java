package com.demo.ui.test.ios;

import com.demo.ui.test.pageobjects.NavigationUI;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSNavigationUI extends NavigationUI {

    static {
        MY_LISTS_LINK = By.id("Saved");
    }

    public iOSNavigationUI(RemoteWebDriver driver) {
        super(driver);
    }
}
