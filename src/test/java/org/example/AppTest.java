package org.example;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.options.BaseOptions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class AppTest{

    private AndroidDriver driver;

    @BeforeSuite
    public void setUp() throws MalformedURLException {
        File f = new File("src/test/resources");
        File apk = new File(f,"General-Store.apk");
//        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
//        desiredCapabilities.setCapability("appium:deviceName", "batch22");
//        desiredCapabilities.setCapability("platformName", "android");
//        desiredCapabilities.setCapability("appium:automationName", "UiAutomator2");
//        desiredCapabilities.setCapability("appium:udid", "emulator-5554");
//        desiredCapabilities.setCapability("appium:appPackage", "com.androidsample.generalstore");
//        desiredCapabilities.setCapability("appium:appActivity", "com.androidsample.generalstore.SplashActivity");
//        desiredCapabilities.setCapability("appium:app", "Desktop\\General-Store.apk");

        var options = new BaseOptions()
                .amend("platformName", "android")
                .amend("appium:automationName", "UiAutomator2")
                .amend("appium:deviceName", "local")
                .amend("appium:udid", "emulator-5554")
                .amend("appium:app", apk.getAbsolutePath())
                .amend("appium:appPackage", "com.androidsample.generalstore")
                .amend("appium:appActivity", "com.androidsample.generalstore.SplashActivity");

        URL remoteUrl = new URL("http://127.0.0.1:4723");

        driver = new AndroidDriver(remoteUrl, options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void sampleTest() throws InterruptedException {
        System.out.println("App open");
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/spinnerCountry")).click();
        WebElement el2 =  driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\"Bangladesh\"))"));
        el2.click();
        WebElement el3 = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/nameField"));
        el3.sendKeys("BD");
        WebElement el4 = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/radioFemale"));
        el4.click();
        WebElement el5 = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop"));
        el5.click();
        WebElement el6 = driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.RelativeLayout/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/android.widget.RelativeLayout[1]/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.TextView[2]"));
        el6.click();
        WebElement el7 = driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/appbar_btn_cart"));
        el7.click();
        Thread.sleep(5000);

    }

    @AfterSuite
    public void tearDown() {
        driver.removeApp("com.androidsample.generalstore");
//        driver.quit();
    }

}

