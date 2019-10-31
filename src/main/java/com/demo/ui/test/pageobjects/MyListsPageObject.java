package com.demo.ui.test.pageobjects;

import com.demo.ui.test.DeviceCapabilities;
import com.demo.ui.test.MainPageObject;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import java.time.Duration;

import static com.demo.ui.test.DeviceCapabilities.devicePlatform;
import static io.appium.java_client.touch.WaitOptions.waitOptions;

public class MyListsPageObject extends MainPageObject {

    public MyListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public static String ARTICLE_BY_TITLE_TPL;
    public static By FOLDER_OPTIONS;
    public static By DELETE_FOLDER;

    // can be deleted if Android passes and iOS passes
//    public void verifySavedArticlePresents(String article_title) {
//
//        By locator = By.xpath("//XCUIElementTypeLink[contains(@name, '" + article_title + "')]");
//        if (!isElementPresent(locator, 5)) {
//            System.out.println("The article doesn't present in saved list");
//        } else {
//            System.out.println("Article is found in list of saved articles");
//        }
//    }

    public void verifySavedArticlePresents(String article_title) {
        String article = getSavedArticleStringByTitle(article_title);
        By locator = By.xpath(article);
        if (!isElementPresent(locator, 5)) {
            System.out.println("The article doesn't present in saved list");
        } else {
            System.out.println("Article is found in list of saved articles");
        }
    }

    private String getSavedArticleStringByTitle(String article_title)
    {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }

    public void openFolderByName(String name_of_folder) {
        By folder_name_xpath = By.xpath("//*[@text='" + name_of_folder +"']");
        waitForElementAndClick(folder_name_xpath,5);
    }

    public void swipeByArticleToDelete(String article_title) {
        verifySavedArticlePresents(article_title);

        // Make a locator for article that we want to swipe
//        By locator = By.xpath("//XCUIElementTypeLink[contains(@name, '" + article_title + "')]/.."); Delete if runs on iOS and Android
        String article = getSavedArticleStringByTitle(article_title);
//        System.out.println("!!!" + article);
        By locator = By.xpath(article);

        // Perform swipe
        if (devicePlatform.equals(DeviceCapabilities.DevicePlatform.IOS) || devicePlatform.equals(DeviceCapabilities.DevicePlatform.ANDROID)) {
            elementSwipeToTheLeft(locator, 0.3, 600);
        }

        // If platform is iOS we need to do additional tap for deletion
        if (devicePlatform.equals(DeviceCapabilities.DevicePlatform.IOS)) {
            clickDeleteArticleIcon(article_title);
        }

        // Checking that article was deleted
        if (isElementPresent(locator, 5)) {
            Assert.fail("Article supposed to to be deleted but it showed up. Test failed");
        } else {
            System.out.println("Article wasn't found in the list of saved articles after deletion. Test passed");
        }
    }

    public void clickDeleteArticleIcon(String article_title) {
        if (driver instanceof AppiumDriver) {
            By locator = By.xpath("//XCUIElementTypeLink[contains(@name, '" + article_title + "')]/..");
            WebElement element = waitForElementPresent(locator, 5);

            int right_x = element.getLocation().getX();
            int upper_y = element.getLocation().getY();
            int lower_y = upper_y + element.getSize().getHeight();
            int middle_y = (upper_y + lower_y) / 2;
            int width = element.getSize().getWidth();

            int point_to_click_x = (right_x + width) - 3;
            int point_to_click_y = middle_y;

            TouchAction action = new TouchAction((AppiumDriver) driver);
            action
                    .press(PointOption.point(point_to_click_x, point_to_click_y))
                    .waitAction(waitOptions(Duration.ofMillis(500)))
                    .release()
                    .perform();
        } else {
            System.out.println("Method clickElementToTheRightUpperCorner() does nothing for Mobile Web platform");
        }

    }

    public void deleteArticlesFolder() {
        waitForElementAndClick(FOLDER_OPTIONS, 5);
        waitForElementAndClick(DELETE_FOLDER, 5);
        javaWaitInSec(1);
    }

//    public void myListForAndroid() {
//
//        if (devicePlatform.equals(DeviceCapabilities.DevicePlatform.ANDROID)) {
//            System.out.println("I called method from MyListsPageObjectFactory class for ANDROID and it worked");
//        } else if (devicePlatform.equals(DeviceCapabilities.DevicePlatform.IOS)){
//            System.out.println("I called method from MyListsPageObjectFactory class for IOS and it worked");
//        } else if (devicePlatform.equals(DeviceCapabilities.DevicePlatform.MOBILE_WEB)) {
//            System.out.println("I called method from MyListsPageObjectFactory class for MOBILE WEB and it worked");
//        } else {
//            System.out.println("something wrong here");
//        }
//
//        javaWaitInSec(3);
//    }
}
