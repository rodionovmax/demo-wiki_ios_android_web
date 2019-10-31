package com.demo.ui.test.mobile_web;

import com.demo.ui.test.pageobjects.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MWSearchPageObject extends SearchPageObject {

    public MWSearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
