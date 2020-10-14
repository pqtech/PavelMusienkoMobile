package pageObjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class NativePageObject {

    private static final String actionBarTextLocator =
            "//android.view.ViewGroup[contains(@resource-id,'action_bar')]/*";

    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/email_sign_in_button")
    WebElement signInBtn;

    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/register_button")
    WebElement regBtn;

    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/registration_email")
    WebElement regEmailField;

    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/registration_username")
    WebElement regUserNameField;

    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/registration_password")
    WebElement regPasswordField;

    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/registration_confirm_password")
    WebElement regConfirmPasswordField;

    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/register_new_account_button")
    WebElement regNewAccBtn;

    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/login_email")
    WebElement signInEmailField;

    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/login_pwd")
    WebElement signInPasswordField;

    @AndroidFindBy(xpath = actionBarTextLocator)
    WebElement actionBarText;

    public NativePageObject(AppiumDriver appiumDriver) {
        PageFactory.initElements(new AppiumFieldDecorator(appiumDriver), this);
    }

    public static String getActionBarTextLocator() {
        return actionBarTextLocator;
    }
}
