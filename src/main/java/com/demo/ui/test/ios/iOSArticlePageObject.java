package com.demo.ui.test.ios;

import com.demo.ui.test.pageobjects.ArticlePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSArticlePageObject extends ArticlePageObject {

    static {
    //    SEARCH_INPUT = By.id("Java (programming language)");
        SEARCH_INPUT = "{SUBSTRING}";
    //    OPTIONS_ADD_TO_MY_LIST_BUTTON = By.id("Save for later");
        OPTIONS_ADD_TO_MY_LIST_BUTTON = By.xpath("//XCUIElementTypeButton[@name='Save for later']");
//        LOGIN_TO_SYNC_BUTTON = By.id("Log in to sync your saved articles");
//        ENABLE_SYNCING_BUTTON = By.id("Enable syncing");
//        SAVED_ARTICLES_FOUND = By.id("Saved articles found");
//        ADD_ARTICLES_TO_READING_LIST = By.id("Yes, add them to my reading lists");
        CLOSE_ARTICLE_BUTTON = By.id("Back");
    }

    public iOSArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
