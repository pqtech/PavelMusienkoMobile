package scenarios;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObjects.WebPageObject;
import setup.BaseTest;

import java.util.List;

public class webMobileTests extends BaseTest {

    protected WebPageObject googlePage;

    @Parameters("ianaUrl")
    @Test(enabled = false, groups = {"web"}, description = "Make sure that we've opened IANA homepage")
    public void simpleWebTest(String ianaUrl) throws InterruptedException {
        getDriver().get(ianaUrl); // open IANA homepage

        // Make sure that page has been loaded completely
        new WebDriverWait(getDriver(), 10).until(
                wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete")
        );

        // Check IANA homepage title
        assert ((WebDriver) getDriver()).getTitle().equals("Internet Assigned Numbers Authority") : "This is not IANA homepage";

        // Log that test finished
        System.out.println("Site opening done");
    }

    @Parameters({"googleUrl", "searchQuery"})
    @Test(groups = {"web"}, description = "Google search test")
    public void googleSearchWebTest(String googleUrl, String searchQuery) {

        // Perform search
        googlePage = new WebPageObject(getDriver());
        googlePage.openPage(googleUrl);
        List<WebElement> resultList = googlePage.getSearchResults(searchQuery);

        // SoftAssert for each search result
        SoftAssert softAssert = new SoftAssert();
        for (WebElement element : resultList) {
            softAssert.assertTrue(element.getText().contains(searchQuery));
        }

        // Assert search results
        softAssert.assertAll();
        System.out.println("Opening a website and getting results done");
    }
}
