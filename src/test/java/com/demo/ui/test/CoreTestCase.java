package com.demo.ui.test;

import com.demo.ui.test.pageobjects.*;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.lang.reflect.Method;

public class CoreTestCase {

    protected RemoteWebDriver driver;
//    protected WebDriverWait wait;

    protected MainPageObject main;
    protected WelcomePageObject welcomePage;
    protected SearchPageObject searchPage;
    protected ArticlePageObject articlePage;
    protected NavigationUI navigation;
    protected MyListsPageObject myLists;

    public DeviceCapabilities capabilities = new DeviceCapabilities();



    @BeforeMethod
    @Parameters({"platform", "device", "os_version"})
    public void setupAppium(@Optional("ios") String platform,
                            @Optional("iphone X") String device,
                            @Optional("ios 12.2") String os_version,
                            Method method, ITestContext context) throws Exception {

        Reports.start(method.getName());

        // Initialize driver with capabilities
        driver = capabilities.getDriver(platform, device, os_version);
//        wait = new WebDriverWait(driver, 20);

        main = new MainPageObject(driver);
        welcomePage = new WelcomePageObject(driver);
//        searchPage = new SearchPageObjectFactory(driver);
        searchPage = new SearchPageObject(driver);
        articlePage = new ArticlePageObject(driver);
        navigation = new NavigationUI(driver);
        myLists = new MyListsPageObject(driver);


        rotateScreenPortrait();
        skipWelcomePageForIOSApp();
        openWikiWebPageForMobileWeb();

    }

    @AfterMethod
    public void tearDown(ITestResult testResult, Method method) {

        if(testResult.getStatus()==ITestResult.FAILURE){
            Reports.fail(driver, testResult.getName());
        }
        Reports.stop();
    }

    protected void rotateScreenPortrait()
    {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.PORTRAIT);
        } else {
            System.out.println("Method rotateScreenPortrait() does nothing for Mobile Web platform" );
        }
    }

    protected void rotateScreenLandscape()
    {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.LANDSCAPE);
        } else {
            System.out.println("Method rotateScreenLandscape() does nothing for Mobile Web platform");
        }
    }

    protected void openWikiWebPageForMobileWeb() {
        if (capabilities.devicePlatform.equals(DeviceCapabilities.DevicePlatform.MOBILE_WEB)) {
//        if (Platform.getInstance().isMW()) {
            driver.get("https://en.m.wikipedia.org");
        } else {
            System.out.println("Method openWikiWebPageForMobileWeb() does nothing for Native platforms" );
        }
    }

    private void skipWelcomePageForIOSApp() {
        if (capabilities.devicePlatform.equals(DeviceCapabilities.DevicePlatform.IOS)) {
//        if(Platform.getInstance().isIOS()){
            AppiumDriver driver = (AppiumDriver) this.driver;
//            WelcomePageObject welcomePage = new WelcomePageObject(driver);
            welcomePage.clickSkip();
        }
    }
}
