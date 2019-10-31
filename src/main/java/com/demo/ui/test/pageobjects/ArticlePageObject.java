package com.demo.ui.test.pageobjects;

import com.demo.ui.test.DeviceCapabilities;
import com.demo.ui.test.MainPageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import static com.demo.ui.test.DeviceCapabilities.devicePlatform;

public class ArticlePageObject extends MainPageObject {

    public ArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }

//    public static final By SEARCH_INPUT = By.id("Java (programming language)");
    protected static String SEARCH_INPUT;
    protected static By OPTIONS_BUTTON;
//    public static final By OPTIONS_ADD_TO_MY_LIST_BUTTON = By.id("Save for later");
    public static By OPTIONS_ADD_TO_MY_LIST_BUTTON;
    public static By ADD_TO_MY_LIST_OVERLAY;
    public static By CREATE_NEW_READING_LIST;
    public static By MY_LIST_NAME_INPUT;
    public static By MY_LIST_OK_BUTTON;
    public static final By LOGIN_TO_SYNC_BUTTON = By.id("Log in to sync your saved articles");
    public static final By ENABLE_SYNCING_BUTTON = By.id("Enable syncing");
    public static final By SAVED_ARTICLES_FOUND = By.id("Saved articles found");
    public static final By ADD_ARTICLES_TO_READING_LIST = By.id("Yes, add them to my reading lists");
    public static By CLOSE_ARTICLE_BUTTON;

    public WebElement waitForTitleOfArticle(String input) {

        String article_title = getArticleTitle(input);
        By locator = By.xpath("//XCUIElementTypeStaticText[@name='" + article_title+ "']");
        return waitForManyElementsPresent(locator, 5).get(0);
    }

    public static String getArticleTitle(String substring) {
        return SEARCH_INPUT.replace("{SUBSTRING}", substring);
    }

    // Method for Android - add when will be working with test for Android

    public void addArticleToMyList(String name_of_folder) {
        waitForElementAndClick(OPTIONS_BUTTON,10);
        waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON,5);
        // If overlay shows up - tap GOT IT else - Tap Create new reading list
        if (isElementPresent(ADD_TO_MY_LIST_OVERLAY, 3)) {
            waitForElementAndClick(ADD_TO_MY_LIST_OVERLAY,3);
        } else {
            waitForElementAndClick(CREATE_NEW_READING_LIST, 3);
        }
        // To delete
//        if (isElementPresent(CREATE_NEW_READING_LIST, 2)) {
//            waitForElementAndClick(CREATE_NEW_READING_LIST, 2);
//        }
        waitForElementAndClear(MY_LIST_NAME_INPUT,5);
        waitForElementAndSendKeys(MY_LIST_NAME_INPUT, name_of_folder,5);
        waitForElementAndClick(MY_LIST_OK_BUTTON,5);
    }

    public void addArticlesToMySaved() {
        if (devicePlatform.equals(DeviceCapabilities.DevicePlatform.MOBILE_WEB)) {
            removeArticleFromSavedIfItsAdded();
        }
        javaWaitInSec(2);
        waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON, 10);
//        System.out.println("!!! I clicked on save to my list");
    }

    public void removeArticleFromSavedIfItsAdded() {
//        if (isElementPresent(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON)) {
//            waitForElementAndClick(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,1);
//            waitForElementPresent(OPTIONS_ADD_TO_MY_LIST_BUTTON,1);
//        }
    }

    public boolean isLoginToSyncPopupAppears() {
        if (!isElementPresent(LOGIN_TO_SYNC_BUTTON, 3)) {
            System.out.println("User already logged in");
            return false;
        }
        return true;
    }

    public void loginToSyncSavedArticles(String username, String password) {
        waitForElementAndClick(LOGIN_TO_SYNC_BUTTON, 3);
        LoginPageObject login = new LoginPageObject(driver);
        login.loginIntoAccount(username, password);
    }

    public boolean isEnableSyncingPopupAppears() {
        if (!isElementPresent(ENABLE_SYNCING_BUTTON, 3)) {
            System.out.println("Syncing already turned on.");
            return false;
        }
        return true;
    }

    public void clickEnableSyncing() {
        waitForElementAndClick(ENABLE_SYNCING_BUTTON, 5);
    }

    public boolean isSavedArticlesFoundPopupAppears() {
        if (!isElementPresent(SAVED_ARTICLES_FOUND, 3)) {
            System.out.println("Saved articles popup wasn't shown");
            return false;
        }
        return true;
    }

    public void clickAddArticlesToReadingList() {
        waitForElementAndClick(ADD_ARTICLES_TO_READING_LIST, 5);
    }

    public void closeArticle() {
        waitForElementAndClick(CLOSE_ARTICLE_BUTTON, 5);
    }






}
