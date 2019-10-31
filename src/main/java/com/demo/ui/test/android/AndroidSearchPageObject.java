package com.demo.ui.test.android;

import com.demo.ui.test.pageobjects.SearchPageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidSearchPageObject extends SearchPageObject {


    static {
        SEARCH_INIT_ELEMENT = By.xpath("//*[contains(@text,'Search Wikipedia')]");
        SEARCH_INPUT = By.id("org.wikipedia:id/search_src_text");
//        SEARCH_INPUT = By.xpath("//*[contains(@text, 'Search…')]");
        SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[contains(@text,'{SUBSTRING}')]";
//        SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn";
//        SEARCH_RESULT_ELEMENT = "xpath://*[@resource-id = 'org.wikipedia:id/search_results_list']/*[@resource-id = 'org.wikipedia:id/page_list_item_container']";
//        SEARCH_EMPTY_RESULT_ELEMENT = "xpath://*[@text = 'No results found']";
    }

    public AndroidSearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
