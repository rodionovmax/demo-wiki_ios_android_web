package com.demo.ui.test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

import static io.appium.java_client.touch.WaitOptions.waitOptions;

public class MainPageObject {

    protected RemoteWebDriver driver;
//    protected WebDriverWait wait;

    public MainPageObject(RemoteWebDriver driver) {
        this.driver = driver;
//        this.wait = wait;
    }

    /**
     * Wait for element to be present with error message
     */
    public WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    /**
     * Wait for element to be present w/o error message
     */
    public WebElement waitForElementPresent(By by, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    /**
     * Wait for element to be present w/o error message
     */
    public List<WebElement> waitForManyElementsPresent(By by, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by)
        );
    }

    /**
     * Wait for element to be present w/o error message
     */
    public WebElement waitForElementVisible(By by, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(by)
//                ExpectedConditions.elementToBeClickable(by)
        );
    }

    /**
     * Wait for element to be present w/o error message with default wait = 5 seconds
     */
    public WebElement waitForElementPresent(By by) {
        return waitForElementPresent(by, 5);
    }

    /**
     * Wait for element to be present and Click
     */
    public void waitForElementAndClick(By by, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, timeoutInSeconds);
        element.click();
    }

    /**
     * Wait for element to be present and Send keys
     */
    public void waitForElementAndSendKeys(By by, String value, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, timeoutInSeconds);
        element.sendKeys(value);
    }

    /**
     * Wait for element to be present and Clear input
     */
    public WebElement waitForElementAndClear(By by, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, timeoutInSeconds);
        element.clear();
        return element;
    }

    /**
     * Method for implicit wait in Seconds
     */
    public void javaWaitInSec(int sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to verify does element present on the screen
     */
    public boolean isElementPresent(By by, int timeOutInSeconds) {
        WebDriverWait findElement = new WebDriverWait(driver, timeOutInSeconds);
//        ExpectedCondition<WebElement> element = ExpectedConditions.presenceOfElementLocated(by);
//        ExpectedCondition<WebElement> element = ExpectedConditions.elementToBeClickable(by);
        ExpectedCondition<WebElement> element = ExpectedConditions.visibilityOfElementLocated(by);
        try {
            findElement.until(element);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     * Method to replace default string on custom string entering in test
     */
    public String replaceStringOnCustomValueById(String custom_string) {
        return Data.defaultStringTpl.replace("{DEFAULT_STRING}", custom_string);
    }

    /**
     * == METHODS FOR SWIPES ==
     *
     * Single Swipe Up
     */
    public void swipeUp(int timeOfSwipe) {
        if (driver instanceof AppiumDriver) {
            TouchAction action = new TouchAction((AppiumDriver) driver);
            Dimension size = driver.manage().window().getSize();
            int x = size.width / 2;
            int start_y = (int) (size.height * 0.8);
            int end_y = (int) (size.height * 0.2);

            action.
                    press(PointOption.point(x, start_y))
                    .waitAction(waitOptions(Duration.ofMillis(timeOfSwipe)))
                    .moveTo(PointOption.point(x, end_y))
                    .release()
                    .perform();
        } else {
            System.out.println("Method swipeUp() does nothing for Web platform");
        }

    }

    /**
     * Adjustable Swipe
     */
    public void adjustableSwipe(double y1, double y2, int timeOfSwipe) {
        if (driver instanceof AppiumDriver) {
            TouchAction action = new TouchAction((AppiumDriver) driver);
            Dimension size = driver.manage().window().getSize();
            int x = size.width / 2;
            int start_y = (int) (size.height * y1);
            int end_y = (int) (size.height * y2);

            action.
                    press(PointOption.point(x, start_y))
                    .waitAction(waitOptions(Duration.ofMillis(timeOfSwipe)))
                    .moveTo(PointOption.point(x, end_y))
                    .release()
                    .perform();
        } else {
            System.out.println("Method adjustableSwipe() does nothing for Web platform");
        }

    }

    /**
     * Single Swipe Down
     */
    public void swipeDown(int timeOfSwipe) {
        if (driver instanceof AppiumDriver) {
            TouchAction action = new TouchAction((AppiumDriver) driver);
            Dimension size = driver.manage().window().getSize();
            int x = size.width / 2;
            int start_y = (int) (size.height * 0.3);
            int end_y = (int) (size.height * 0.8);

            action.
                    press(PointOption.point(x, start_y))
                    .waitAction(waitOptions(Duration.ofMillis(timeOfSwipe)))
                    .moveTo(PointOption.point(x, end_y))
                    .release()
                    .perform();
        } else {
            System.out.println("Method swipeDown() does nothing for Web platform");
        }

    }

    public void swipeUpUntilElementDisplays(By by, int max_swipes, int additional_swipes) {
        swipeUpToFindElement(by, max_swipes);
        doAdditionalSwipesUpIfElementIsNotDisplayed(by, additional_swipes);
    }

    /**
     * Swipe till element appears in DOM
     */
    public void swipeUpToFindElement(By by, int max_swipes) {
        int already_swiped = 0;
        while (driver.findElements(by).size() == 0) {

            if (already_swiped > max_swipes) {
                System.out.println("Reached max number of swipes");
//                waitForElementVisible(by, 0);
                waitForElementPresent(by, 0);
                return;
            }
            adjustableSwipe(0.8, 0.3, 600);
            ++already_swiped;
            System.out.println("Main Swipe " + already_swiped);
//            System.out.println(driver.getPageSource());
        }
    }

    public void doAdditionalSwipesUpIfElementIsNotDisplayed(By by, int max_swipes) {

        int already_swiped = 0;
        while (!driver.findElement(by).isDisplayed()) {
            if (already_swiped > max_swipes) {
                System.out.println("Reached max number of swipes");
//                waitForElementVisible(by, 0);
                waitForElementPresent(by, 1);
                return;
            }
            adjustableSwipe(0.7, 0.4, 600);
            ++already_swiped;
            System.out.println("Additional Swipe " + already_swiped);
        }
    }

    public void swipeDownUntilElementDisplays(By by, int max_swipes, int additional_swipes) {
        swipeDownToFindElement(by, max_swipes);
        doAdditionalSwipesDownIfElementIsNotDisplayed(by, additional_swipes);

    }

    /**
     * Swipe till element appears
     */
    public void swipeDownToFindElement(By by, int max_swipes) {
        int already_swiped = 0;
        while (driver.findElements(by).size() == 0) {

            if (already_swiped > max_swipes) {
                System.out.println("Reached max number of swipes");
                waitForElementVisible(by, 1);
                return;
            }
            adjustableSwipe(0.3, 0.8, 600);
            ++already_swiped;
            System.out.println("Main Swipe " + already_swiped);
        }
    }

    public void doAdditionalSwipesDownIfElementIsNotDisplayed(By by, int max_swipes) {
        int already_swiped = 0;
        while (!driver.findElement(by).isDisplayed()) {
            if (already_swiped > max_swipes) {
                System.out.println("Reached max number of swipes");
                waitForElementVisible(by, 1);
//                waitForElementPresent(by, 3);
                return;
            }
            adjustableSwipe(0.5, 0.7, 600); // swipe down
            ++already_swiped;
            System.out.println("Additional Swipe " + already_swiped);
        }
    }

    /**
     *
     */
    public void elementSwipeToTheLeft(By by, double k, int timeOfSwipe) {
        int[] coordinatesOfElementCenter = elementGetCoordinatesOfCenter(by);
        int middle_x = coordinatesOfElementCenter[0];
        int middle_y = coordinatesOfElementCenter[1];
//        System.out.println("X: " + middle_x + ", Y: " + middle_y);

        Dimension size = driver.manage().window().getSize(); // get size of the screen
        int end_x = (int) (size.width * k); // X coordinate where swipe stops

        if (driver instanceof AppiumDriver) {
            TouchAction action = new TouchAction((AppiumDriver) driver);
            action.
                    press(PointOption.point(middle_x, middle_y))
                    .waitAction(waitOptions(Duration.ofMillis(timeOfSwipe)))
                    .moveTo(PointOption.point(end_x, middle_y))
                    .release()
                    .perform();
        } else {
            System.out.println("Method elementSwipeToTheLeft() does nothing for Web platform");
        }

    }

    public int[] elementGetCoordinatesOfCenter(By by) {
        WebElement element = waitForElementPresent(by, 5);
        int left_x = element.getLocation().getX();
        int upper_y = element.getLocation().getY();
        int middle_x = left_x + element.getSize().getWidth()/2; // middle X coordinate of element
        int middle_y = upper_y + element.getSize().getHeight()/2; // middle Y coordinate of element
//        System.out.println("left_x: " + left_x + ", upper_y: " + upper_y + ", middle_x: " + middle_x + ", middle_y: " + middle_y);

        return new int[]{middle_x, middle_y};
    }

    /**
     * Method to generate random email from chars
     *
     * @param length the number of random chars to be generated
     * @return generatedEmail
     */
    public String generateRandomEmail(int length) {
        String candidateChars = "abcdefghijklmnopqrstuvwxyz1234567890"; // Chars using to generate email
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(candidateChars.charAt(random.nextInt(candidateChars.length())));
        }
        return sb.toString() + "@gmail.com";
    }

    /**
     * Method to get system date
     */
    public String getCurrentDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd");
        LocalDateTime now = LocalDateTime.now();
//        System.out.println(dtf.format(now));
        return dtf.format(now);
    }
}
