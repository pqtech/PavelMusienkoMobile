package scenarios;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import setup.BaseTest;

public class nativeMobileTests extends BaseTest {

    @Test(priority = 0, groups = {"native"}, description = "This simple test just click on the Sign In button")
    public void simpleNativeTest() throws IllegalAccessException, NoSuchFieldException, InstantiationException {
        getPo().getWelement("signInBtn").click();
        System.out.println("Simplest Android native test done");
    }

    @Parameters({"userEmail", "userName", "userPassword"})
    @Test(priority = 1, groups = {"native"}, description = "Registering a new account")
    public void RegisteringAccountNativeTest(String email, String userName, String userPassword)
            throws IllegalAccessException, NoSuchFieldException, InstantiationException {

        // Perform actions for registering an account
        getPo().getWelement("regBtn").click();
        getPo().getWelement("regEmailField").sendKeys(email);
        getPo().getWelement("regUserNameField").sendKeys(userName);
        getPo().getWelement("regPasswordField").sendKeys(userPassword);
        getPo().getWelement("regConfirmPasswordField").sendKeys(userPassword);
        getPo().getWelement("regNewAccBtn").click();
        System.out.println("Registering a new Account native test done");
    }

    @Parameters({"userEmail", "userPassword", "budgetPageName", "actionBarId", "budgetPageNameClass"})
    @Test(priority = 2, groups = {"native"}, description = "Sign in with a registered account")
    public void SignInAccountNativeTest(String userEmail, String userPassword, String budgetPageName,
                                        String actionBarId, String budgetPageNameClass)
            throws IllegalAccessException, NoSuchFieldException, InstantiationException {

        // Perform signing in actions
        getPo().getWelement("signInEmailField").sendKeys(userEmail);
        getPo().getWelement("signInPasswordField").sendKeys(userPassword);
        getPo().getWelement("signInBtn").click();

        // Making sure that the Budged page has loaded
        new WebDriverWait(getDriver(), 1)
                .until(ExpectedConditions.presenceOfElementLocated(By.id(actionBarId)));

        // Get Budget page's name
        String actualPageName = getPo().getWelement("actionBar")
                .findElement(By.className(budgetPageNameClass)).getText();

        Assert.assertEquals(actualPageName, budgetPageName);
        System.out.println("Sign in with an account native test done");
    }
}
