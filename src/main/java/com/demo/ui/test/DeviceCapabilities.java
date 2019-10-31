package com.demo.ui.test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class DeviceCapabilities {

    public static DevicePlatform devicePlatform;
    public AppleDevice appleDevice;
    public AndroidDevice androidDevice;
    public IOSVersion iosVersion;
    public AndroidVersion androidVersion;

    private RemoteWebDriver driver;

    public static final String APPIUM_URL = "http://127.0.0.1:4723/wd/hub";
    public static final String CURRENT_BUILD_IOS = "Wikipedia.app";
    public static final String CURRENT_BUILD_ANDROID = "org.wikipedia.apk";

    public enum DevicePlatform {
        ANDROID, IOS, MOBILE_WEB
    }

    public enum AppleDevice {
        IPHONE_7, IPHONE_X, IPAD_PRO_9_7_INCH
    }

    public enum IOSVersion {
        IOS_12_2, IOS_12_4, IOS_13_1
    }

    public enum AndroidDevice {
        NEXUS_5X, PIXEL_3A
    }

        public enum AndroidVersion {
        ANDROID_9_0, ANDROID_10_0
    }

    public RemoteWebDriver getDriver(String platform, String device, String os_version) throws Exception {
        URL URL = new URL(APPIUM_URL);

        if (platform.equalsIgnoreCase("ios")) {
            devicePlatform = DevicePlatform.IOS;
        } else if (platform.equalsIgnoreCase("android")) {
            devicePlatform = DevicePlatform.ANDROID;
        } else if (platform.equalsIgnoreCase("mobile_web")) {
            devicePlatform = DevicePlatform.MOBILE_WEB;
        } else {
            System.out.println("Platform parameter wasn't recognized. Launching default iOS platform");
            devicePlatform = DevicePlatform.IOS;
        }

        if (os_version.equalsIgnoreCase("android 9.0")) {
            androidVersion = AndroidVersion.ANDROID_9_0;
        } else if (os_version.equalsIgnoreCase("android 10.0")) {
            androidVersion = AndroidVersion.ANDROID_10_0;
        } else if (os_version.equalsIgnoreCase("ios 12.2")) {
            iosVersion = IOSVersion.IOS_12_2;
        } else if (os_version.equalsIgnoreCase("ios 12.4")) {
            iosVersion = IOSVersion.IOS_12_4;
        } else if (os_version.equalsIgnoreCase("ios 13.1")) {
            iosVersion = IOSVersion.IOS_13_1;
        } else {
            System.out.println("OS Version parameter wasn't recognized. Launching default iOS 12.2");
            devicePlatform = DevicePlatform.IOS;
        }

        if (device.equalsIgnoreCase("nexus 5X")) {
            androidDevice = AndroidDevice.NEXUS_5X;
        } else if (device.equalsIgnoreCase("pixel 3a")) {
            androidDevice = AndroidDevice.PIXEL_3A;
        } else if (device.equalsIgnoreCase("iphone 7")) {
            appleDevice = AppleDevice.IPHONE_7;
        } else if (device.equalsIgnoreCase("iphone X")) {
            appleDevice = AppleDevice.IPHONE_X;
        } else if (device.equalsIgnoreCase("ipad pro 9.7")) {
            appleDevice = AppleDevice.IPAD_PRO_9_7_INCH;
        } else {
            System.out.println("Device parameter wasn't recognized. Launching default device iPhone X");
            appleDevice = AppleDevice.IPHONE_X;
        }

        switch (devicePlatform) {

            case ANDROID:
                switch (androidVersion) {

                    case ANDROID_9_0:
                        switch (androidDevice) {
                            case NEXUS_5X:
                                driver = new AndroidDriver(URL, this.getCapabilitiesForNexus5XAndroid9());
                                break;
                            case PIXEL_3A:
                                driver = new AndroidDriver(URL, this.getCapabilitiesForPixel3AAndroid9());
                                break;
                        }
                        break;

                    case ANDROID_10_0:
                        switch (androidDevice) {
                            case NEXUS_5X:
                                driver = new AndroidDriver(URL, this.getCapabilitiesForNexus5XAndroid10());
                                break;
                            case PIXEL_3A:
                                driver = new AndroidDriver(URL, this.getCapabilitiesForPixel3AAndroid10());
                                break;
                        }
                        break;
                }
                break;

            case IOS:
                switch (iosVersion) {

                    case IOS_12_2:
                        switch (appleDevice) {
                            case IPHONE_7:
                                driver = new IOSDriver(URL, this.getCapabilitiesForIphone7Ios12_2());
                                break;
                            case IPHONE_X:
                                driver = new IOSDriver(URL, this.getCapabilitiesForIphoneXIos12_2());
                                break;
                            case IPAD_PRO_9_7_INCH:
                                driver = new IOSDriver(URL, this.getCapabilitiesForIpadPro9_7InchIos12_2());
                                break;
                        }
                        break;

                    case IOS_12_4:
                        switch (appleDevice) {
                            case IPHONE_7:
                                driver = new IOSDriver(URL, this.getCapabilitiesForIphone7Ios12_4());
                                break;
                            case IPHONE_X:
                                driver = new IOSDriver(URL, this.getCapabilitiesForIphoneXIos12_4());
                                break;
                            case IPAD_PRO_9_7_INCH:
                                driver = new IOSDriver(URL, this.getCapabilitiesForIpadPro9_7InchIos12_4());
                                break;
                        }
                        break;

                    case IOS_13_1:
                        switch (appleDevice) {
                            case IPHONE_7:
                                driver = new IOSDriver(URL, this.getCapabilitiesForIphone7Ios13_1());
                                break;
                            case IPHONE_X:
                                driver = new IOSDriver(URL, this.getCapabilitiesForIphoneXIos13_1());
                                break;
                            case IPAD_PRO_9_7_INCH:
                                driver = new IOSDriver(URL, this.getCapabilitiesForIpadPro9_7InchIos13_1());
                                break;
                        }
                        break;
                }
                break;

            case MOBILE_WEB:
                System.out.println("Mobile Chrome");
                Map<String, String> mobileEmulation = new HashMap<String, String>();
                mobileEmulation.put("deviceName", "Pixel 2");
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
                System.setProperty("webdriver.chrome.driver", "chromedriver");
                driver = new ChromeDriver(chromeOptions);
                driver.get("chrome://settings/clearBrowserData");
                break;
        }

        return driver;
    }

    private DesiredCapabilities getCapabilitiesForNexus5XAndroid9() {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "9.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", "main.MainActivity");
        capabilities.setCapability("app", System.getProperty("user.dir") + "/apks/" + CURRENT_BUILD_ANDROID);
        return capabilities;
    }

    private DesiredCapabilities getCapabilitiesForNexus5XAndroid10() {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "10.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", "main.MainActivity");
        capabilities.setCapability("app", System.getProperty("user.dir") + "/apks/" + CURRENT_BUILD_ANDROID);
        return capabilities;
    }

    private DesiredCapabilities getCapabilitiesForPixel3AAndroid9() {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "10.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", "main.MainActivity");
        capabilities.setCapability("app", System.getProperty("user.dir") + "/apks/" + CURRENT_BUILD_ANDROID);
        return capabilities;
    }

    private DesiredCapabilities getCapabilitiesForPixel3AAndroid10() {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "10.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", "main.MainActivity");
        capabilities.setCapability("app", System.getProperty("user.dir") + "/apks/" + CURRENT_BUILD_ANDROID);
        return capabilities;
    }

    private DesiredCapabilities getCapabilitiesForIphone7Ios12_2() {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("automationName", "XCUITest");
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("platformVersion", "12.2");
        capabilities.setCapability("deviceName", "iPhone 7");
        capabilities.setCapability("connectHardwareKeyboard", false);
        capabilities.setCapability("app", System.getProperty("user.dir") + "/apks/" + CURRENT_BUILD_IOS);
//        capabilities.setCapability("app", "/Users/max/sandbox/demo-framework/apks/Wikipedia.app");
        return capabilities;
    }

    private DesiredCapabilities getCapabilitiesForIphoneXIos12_2() {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("automationName", "XCUITest");
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("platformVersion", "12.2");
        capabilities.setCapability("deviceName", "iPhone X");
        capabilities.setCapability("connectHardwareKeyboard", false);
        capabilities.setCapability("app", System.getProperty("user.dir") + "/apks/" + CURRENT_BUILD_IOS);
        return capabilities;
    }

    private DesiredCapabilities getCapabilitiesForIpadPro9_7InchIos12_2() {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("automationName", "XCUITest");
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("platformVersion", "12.2");
        capabilities.setCapability("deviceName", "iPad Pro (9.7-inch)");
        capabilities.setCapability("connectHardwareKeyboard", false);
        capabilities.setCapability("app", System.getProperty("user.dir") + "/apks/" + CURRENT_BUILD_IOS);
        return capabilities;
    }

    private DesiredCapabilities getCapabilitiesForIphone7Ios12_4() {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("automationName", "XCUITest");
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("platformVersion", "12.4");
        capabilities.setCapability("deviceName", "iPhone 7");
        capabilities.setCapability("connectHardwareKeyboard", false);
        capabilities.setCapability("app", System.getProperty("user.dir") + "/apks/" + CURRENT_BUILD_IOS);
        return capabilities;
    }

    private DesiredCapabilities getCapabilitiesForIphoneXIos12_4() {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("automationName", "XCUITest");
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("platformVersion", "12.4");
        capabilities.setCapability("deviceName", "iPhone X");
        capabilities.setCapability("connectHardwareKeyboard", false);
        capabilities.setCapability("app", System.getProperty("user.dir") + "/apks/" + CURRENT_BUILD_IOS);
        return capabilities;
    }

    private DesiredCapabilities getCapabilitiesForIpadPro9_7InchIos12_4() {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("automationName", "XCUITest");
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("platformVersion", "12.4");
        capabilities.setCapability("deviceName", "iPad Pro (9.7-inch)");
        capabilities.setCapability("connectHardwareKeyboard", false);
        capabilities.setCapability("app", System.getProperty("user.dir") + "/apks/" + CURRENT_BUILD_IOS);
        return capabilities;
    }

    private DesiredCapabilities getCapabilitiesForIphone7Ios13_1() {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("automationName", "XCUITest");
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("platformVersion", "13.1");
        capabilities.setCapability("deviceName", "iPhone 7");
        capabilities.setCapability("connectHardwareKeyboard", false);
        capabilities.setCapability("app", System.getProperty("user.dir") + "/apks/" + CURRENT_BUILD_IOS);
        return capabilities;
    }

    private DesiredCapabilities getCapabilitiesForIphoneXIos13_1() {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("automationName", "XCUITest");
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("platformVersion", "13.1");
        capabilities.setCapability("deviceName", "iPhone X");
        capabilities.setCapability("connectHardwareKeyboard", false);
        capabilities.setCapability("app", System.getProperty("user.dir") + "/apks/" + CURRENT_BUILD_IOS);
        return capabilities;
    }

    private DesiredCapabilities getCapabilitiesForIpadPro9_7InchIos13_1() {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("automationName", "XCUITest");
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("platformVersion", "13.1");
        capabilities.setCapability("deviceName", "iPad Pro (9.7-inch)");
        capabilities.setCapability("connectHardwareKeyboard", false);
        capabilities.setCapability("app", System.getProperty("user.dir") + "/apks/" + CURRENT_BUILD_IOS);
        return capabilities;
    }
}
