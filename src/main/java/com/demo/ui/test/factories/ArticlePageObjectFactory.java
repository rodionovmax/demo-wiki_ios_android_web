package com.demo.ui.test.factories;

import com.demo.ui.test.DeviceCapabilities;
import com.demo.ui.test.android.AndroidArticlePageObject;
import com.demo.ui.test.ios.iOSArticlePageObject;
import com.demo.ui.test.mobile_web.MWArticlePageObject;
import com.demo.ui.test.pageobjects.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

import static com.demo.ui.test.DeviceCapabilities.devicePlatform;

public class ArticlePageObjectFactory {

    public static ArticlePageObject get(RemoteWebDriver driver) {
        if (devicePlatform.equals(DeviceCapabilities.DevicePlatform.ANDROID)) {
            return new AndroidArticlePageObject(driver);
        } else if (devicePlatform.equals(DeviceCapabilities.DevicePlatform.IOS)) {
            return new iOSArticlePageObject(driver);
        } else {
            return new MWArticlePageObject(driver);
        }
    }
}
