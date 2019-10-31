package com.demo.ui.test.factories;

import com.demo.ui.test.DeviceCapabilities;
import com.demo.ui.test.android.AndroidSearchPageObject;
import com.demo.ui.test.ios.iOSSearchPageObject;
import com.demo.ui.test.mobile_web.MWSearchPageObject;
import com.demo.ui.test.pageobjects.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

import static com.demo.ui.test.DeviceCapabilities.devicePlatform;

public class SearchPageObjectFactory {

    public static SearchPageObject get(RemoteWebDriver driver) {
        if (devicePlatform.equals(DeviceCapabilities.DevicePlatform.ANDROID)) {
            return new AndroidSearchPageObject(driver);
        } else if (devicePlatform.equals(DeviceCapabilities.DevicePlatform.IOS)) {
            return new iOSSearchPageObject(driver);
        } else {
            return new MWSearchPageObject(driver);
        }
    }
}
