package TestNGTests.positive;

import exception.UnsupportedBrowserException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.IndexPage;
import pages.RegisterPage;
import utility.BrowserFactory;

import java.io.IOException;

public class TestIndexPage {

    private WebDriver driver;
    private IndexPage indexPage;
    private RegisterPage registerPage;

    @BeforeClass
    public void setup() throws IOException, UnsupportedBrowserException {
        driver = BrowserFactory.getWebDriver();
        indexPage = IndexPage.getInstance(driver);
    }

    @Test
    public void testLoadIndexPage() {
        indexPage.loadPage();
        Assert.assertTrue(indexPage.isIndexPageLoaded());
    }

    @Test(dependsOnMethods = "testLoadIndexPage")
    public void testSkipSignIn() {
        registerPage = indexPage.skipSignIn();
        registerPage.isPageLoaded();
    }

    @AfterClass
    public void cleanup() {
        if (indexPage != null)
            indexPage.deleteInstance();
        if (registerPage != null)
            registerPage.deleteInstance();
        driver.manage().deleteAllCookies();
        driver.close();
    }
}
