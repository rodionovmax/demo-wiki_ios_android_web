package com.demo.ui.test.ios;

import com.demo.ui.test.pageobjects.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSMyListsPageObject extends MyListsPageObject {

    static {
        ARTICLE_BY_TITLE_TPL = "//XCUIElementTypeLink[contains(@name,'{TITLE}')]/..";
    }

    public iOSMyListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
