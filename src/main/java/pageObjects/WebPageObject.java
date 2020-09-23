package pageObjects;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class WebPageObject {

    protected AppiumDriver appiumDriver;

    @FindBy(xpath = "//input[@name='q']")
    WebElement searchField;

    @FindBy(xpath = "//*[@id='rso']//div[@aria-level='3']")
    List<WebElement> searchResults;

    public WebPageObject(AppiumDriver appiumDriver) {
        this.appiumDriver = appiumDriver;
        PageFactory.initElements(appiumDriver, this);
    }

    public void openPage(String pageUrl) {
        appiumDriver.navigate().to(pageUrl);
        // Make sure that page has been loaded completely
        new WebDriverWait(appiumDriver, 10).until(
                wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete")
        );
    }

    public void performSearch(String searchString) {
        searchField.sendKeys(searchString);
        searchField.submit();
        // Make sure that page has been loaded completely
        new WebDriverWait(this.appiumDriver, 10).until(
                wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete")
        );
    }

    public List<WebElement> getSearchResults() {
        return searchResults;
    }
}
