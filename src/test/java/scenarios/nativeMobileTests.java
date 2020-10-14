package scenarios;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.NativePageObject;
import setup.BaseTest;

public class nativeMobileTests extends BaseTest {

    @Parameters({"userEmail", "userPassword", "budgetPageName"})
    @Test(groups = {"native"}, description = "Sign in with a registered account")
    public void SignInAccountNativeTest(String userEmail, String userPassword,
                                        String budgetPageName)
            throws IllegalAccessException, NoSuchFieldException, InstantiationException {

        // Perform signing in actions
        getPo().getWelement("signInEmailField").sendKeys(userEmail);
        getPo().getWelement("signInPasswordField").sendKeys(userPassword);
        getPo().getWelement("signInBtn").click();

        // Making sure that the Budged page has loaded
        new WebDriverWait(getDriver(), 1)
                .until(ExpectedConditions.presenceOfElementLocated(By
                        .xpath(NativePageObject.getActionBarTextLocator())));

        // Get Budget page's name
        String actualPageName = getPo().getWelement("actionBarText").getText();

        Assert.assertEquals(actualPageName, budgetPageName);
        System.out.println("Sign in with an account native test done");
    }
}
