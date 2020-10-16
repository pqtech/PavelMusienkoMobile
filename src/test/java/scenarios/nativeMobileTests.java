package scenarios;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import setup.BaseTest;

public class nativeMobileTests extends BaseTest {

    @Parameters({"userEmail", "userPassword", "budgetPageName"})
    @Test(groups = {"native"}, description = "Sign in with a registered account")
    public void SignInAccountNativeTest(String userEmail, String userPassword, String budgetPageName)
            throws IllegalAccessException, NoSuchFieldException, InstantiationException {

        // Perform signing in actions
        getPo().getWelement("signInEmailField").sendKeys(userEmail);
        getPo().getWelement("signInPasswordField").sendKeys(userPassword);
        getPo().getWelement("signInBtn").click();

        // Get Budget page's name
        String actualPageName = getPo().getWelement("actionBarText").getText();
        Assert.assertEquals(actualPageName, budgetPageName);
        System.out.println("Sign in with an account native test done");
    }
}
