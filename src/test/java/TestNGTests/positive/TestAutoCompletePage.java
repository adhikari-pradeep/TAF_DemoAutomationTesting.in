package TestNGTests.positive;

import exception.UnsupportedBrowserException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.AutoCompletePage;
import utility.BrowserFactory;
import utility.DataProvider;

import java.io.IOException;

public class TestAutoCompletePage {

    private WebDriver driver;
    private AutoCompletePage autoCompletePage;

    @BeforeClass
    public void setup() throws IOException, UnsupportedBrowserException {
        driver = BrowserFactory.getWebDriver();
        autoCompletePage = AutoCompletePage.getInstance(driver);
    }

    @Test
    public void testLoadAutoCompletePage() {
        autoCompletePage.loadPage();
        Assert.assertTrue(autoCompletePage.isAutoCompletePageLoaded());
    }

    @Test(dependsOnMethods = "testLoadAutoCompletePage")
    public void testAutoComplete() {
        String text = DataProvider.getText(1);
        autoCompletePage.enterTextToSearch(text);
        autoCompletePage.selectAutoCompleteData(1);
        Assert.assertTrue(autoCompletePage.getTextFromSearchField().contains(text));
    }

    @AfterClass
    public void cleanup() {
        autoCompletePage.deleteInstance();
        driver.manage().deleteAllCookies();
        driver.close();
    }
}
