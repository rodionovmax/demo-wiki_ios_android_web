package com.demo.ui.test.factories;

import com.demo.ui.test.DeviceCapabilities;
import com.demo.ui.test.android.AndroidMyListsPageObject;
import com.demo.ui.test.ios.iOSMyListsPageObject;
import com.demo.ui.test.mobile_web.MWMyListsPageObject;
import com.demo.ui.test.pageobjects.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

import static com.demo.ui.test.DeviceCapabilities.devicePlatform;

public class MyListsPageObjectFactory {

    public static MyListsPageObject get(RemoteWebDriver driver) {
        if(devicePlatform.equals(DeviceCapabilities.DevicePlatform.ANDROID)){
            return new AndroidMyListsPageObject(driver);
        } else if(devicePlatform.equals(DeviceCapabilities.DevicePlatform.IOS)){
            return new iOSMyListsPageObject(driver);
        } else {
            return new MWMyListsPageObject(driver);
        }
    }
}
