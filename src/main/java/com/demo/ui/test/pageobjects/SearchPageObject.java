package com.demo.ui.test.pageobjects;

import com.demo.ui.test.MainPageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SearchPageObject extends MainPageObject {

    public SearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    protected static By SEARCH_INIT_ELEMENT;
    protected static By SEARCH_INPUT;
    protected static String SEARCH_RESULT_BY_SUBSTRING_TPL;
//    public static final By SEARCH_INPUT = By.xpath("//XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeSearchField");
//    public static final By SEARCH_INIT_ELEMENT = By.xpath("//XCUIElementTypeSearchField[@name='Search Wikipedia']");



    public static String getResultSearchElement(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    public void initSearchInput() {
        waitForElementAndClick(SEARCH_INIT_ELEMENT, 10);
    }

    public void typeSearchLine(String search_line) {
        waitForElementAndSendKeys(SEARCH_INPUT, search_line, 10);
    }

    public void clickOnTheArticleWithSubstring(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        waitForElementAndClick(By.xpath(search_result_xpath), 10);
    }


}
