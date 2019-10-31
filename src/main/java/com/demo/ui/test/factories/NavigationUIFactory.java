package com.demo.ui.test.factories;

import com.demo.ui.test.DeviceCapabilities;
import com.demo.ui.test.android.AndroidNavigationUI;
import com.demo.ui.test.ios.iOSNavigationUI;
import com.demo.ui.test.mobile_web.MWNavigationUI;
import com.demo.ui.test.pageobjects.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

import static com.demo.ui.test.DeviceCapabilities.devicePlatform;

public class NavigationUIFactory {

    public static NavigationUI get(RemoteWebDriver driver) {
        if(devicePlatform.equals(DeviceCapabilities.DevicePlatform.ANDROID)){
            return new AndroidNavigationUI(driver);
        } else if(devicePlatform.equals(DeviceCapabilities.DevicePlatform.IOS)){
            return new iOSNavigationUI(driver);
        } else {
            return new MWNavigationUI(driver);
        }
    }
}
