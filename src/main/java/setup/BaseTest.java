package setup;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import pageObjects.PageObject;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseTest implements IDriver {

    private static AppiumDriver appiumDriver; // singleton
    IPageObject po;

    @Override
    public AppiumDriver getDriver() {
        return appiumDriver;
    }

    public IPageObject getPo() {
        return po;
    }

    @Parameters({"platformName", "appType", "deviceName", "browserName", "app", "udid",
            "userEmail", "userName", "userPassword", "appPackage", "appActivity", "bundleId"})
    @BeforeSuite(alwaysRun = true)
    public void setUp(String platformName, String appType, String deviceName,
                      @Optional("") String browserName,
                      @Optional("") String app,
                      String udid,
                      @Optional("") String userEmail,
                      @Optional("") String userName,
                      @Optional("") String userPassword,
                      @Optional("") String appPackage,
                      @Optional("") String appActivity,
                      @Optional("") String bundleId) throws Exception {
        System.out.println("Before: app type - " + appType);
        setAppiumDriver(platformName, deviceName, browserName, app, udid,
                appPackage, appActivity, bundleId);
        setPageObject(appType, appiumDriver);

        // Perform registering a new account
        if (appType.equals("native")) {
            registerNewAccount(userEmail, userName, userPassword);
        }
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        System.out.println("After");
        appiumDriver.closeApp();
    }

    private void setAppiumDriver(String platformName, String deviceName,
                                 String browserName, String app, String udid,
                                 String appPackage, String appActivity, String bundleId) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        //mandatory Android capabilities
        capabilities.setCapability("platformName", platformName);
        capabilities.setCapability("deviceName", deviceName);
        capabilities.setCapability("udid", udid);

        if (app.endsWith(".apk")) capabilities.setCapability("app", (new File(app)).getAbsolutePath());

        capabilities.setCapability("browserName", browserName);
        capabilities.setCapability("chromedriverDisableBuildCheck", "true");

        // Capabilities for test of Android native app on EPAM Mobile Cloud
        capabilities.setCapability("appPackage", appPackage);
        capabilities.setCapability("appActivity", appActivity);

        // Capabilities for test of iOS native app on EPAM Mobile Cloud
        capabilities.setCapability("bundleId", bundleId);


        try {
            appiumDriver = new AppiumDriver(new URL(System.getProperty("ts.appium")), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        // Timeouts tuning
        appiumDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    private void setPageObject(String appType, AppiumDriver appiumDriver) throws Exception {
        po = new PageObject(appType, appiumDriver);
    }

    private void registerNewAccount(String email, String userName, String userPassword)
            throws IllegalAccessException, NoSuchFieldException, InstantiationException {
        getPo().getWelement("regBtn").click();
        getPo().getWelement("regEmailField").sendKeys(email);
        getPo().getWelement("regUserNameField").sendKeys(userName);
        getPo().getWelement("regPasswordField").sendKeys(userPassword);
        getPo().getWelement("regConfirmPasswordField").sendKeys(userPassword);
        getPo().getWelement("regNewAccBtn").click();
        System.out.println("New account has been registered");
    }
}
