package scenarios;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.WebPageObject;
import setup.BaseTest;

import java.util.List;

public class webMobileTests extends BaseTest {

    protected WebPageObject googlePage;

    @Test(enabled = false, groups = {"web"}, description = "Make sure that we've opened IANA homepage")
    public void simpleWebTest() throws InterruptedException {
        getDriver().get("http://iana.org"); // open IANA homepage

        // Make sure that page has been loaded completely
        new WebDriverWait(getDriver(), 10).until(
                wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete")
        );

        // Check IANA homepage title
        assert ((WebDriver) getDriver()).getTitle().equals("Internet Assigned Numbers Authority") : "This is not IANA homepage";

        // Log that test finished
        System.out.println("Site opening done");
    }

    @Test(groups = {"web"}, description = "Google search test")
    public void googleSearchWebTest() {

        // Perform search
        googlePage = new WebPageObject(getDriver());
        googlePage.openPage("http://google.com");
        googlePage.performSearch("EPAM");

        int numberOfRelatedResults = 0;
        List<WebElement> resultList = googlePage.getSearchResults();

        // Count how many results we have got with a specified text
        for (WebElement element : resultList) {
            if (element.getText().contains("EPAM")) {
                numberOfRelatedResults++;
            }
        }

        // Assert if we have got enough related results
        Assert.assertTrue(numberOfRelatedResults > 8);
        System.out.println("Opening a website and getting results done");
    }

}
