package com.demo.ui.test.ios;

import com.demo.ui.test.pageobjects.SearchPageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSSearchPageObject extends SearchPageObject {

    static
    {
        SEARCH_INIT_ELEMENT = By.xpath("//XCUIElementTypeSearchField[@name='Search Wikipedia']");
        SEARCH_INPUT = By.xpath("//XCUIElementTypeNavigationBar[@name='Wikipedia, scroll to top of Explore']");
        SEARCH_RESULT_BY_SUBSTRING_TPL = "//XCUIElementTypeLink[contains(@name, '{SUBSTRING}')]";
//        SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn";
//        SEARCH_RESULT_ELEMENT = "xpath://*[@resource-id = 'org.wikipedia:id/search_results_list']/*[@resource-id = 'org.wikipedia:id/page_list_item_container']";
//        SEARCH_EMPTY_RESULT_ELEMENT = "xpath://*[@text = 'No results found']";
    }

    public iOSSearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
