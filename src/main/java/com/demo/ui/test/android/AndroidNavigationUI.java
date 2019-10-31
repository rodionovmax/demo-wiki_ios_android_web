package com.demo.ui.test.android;

import com.demo.ui.test.pageobjects.NavigationUI;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidNavigationUI extends NavigationUI {

    static {
        MY_LISTS_LINK = By.xpath("//android.widget.FrameLayout[@content-desc='My lists']");
    }


    public AndroidNavigationUI(RemoteWebDriver driver) {
        super(driver);
    }
}
