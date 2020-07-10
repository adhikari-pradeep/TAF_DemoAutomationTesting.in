package TestNGTests.positive;

import constants.PageUrls;
import exception.UnsupportedBrowserException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.WindowPage;
import utility.BrowserFactory;

import java.io.IOException;

public class TestWindowPage {

    private WebDriver driver;
    private WindowPage windowPage;

    @BeforeClass
    public void setup() throws IOException, UnsupportedBrowserException {
        driver = BrowserFactory.getWebDriver();
        windowPage = WindowPage.getInstance(driver);
    }

    @Test
    public void testLoadWindowPage() {
        windowPage.loadPage();
        Assert.assertTrue(windowPage.isWindowPageLoaded());
    }

    @Test(dependsOnMethods = "testLoadWindowPage")
    public void testNewTabbedWindow() {
        windowPage.getNewTabbedWindow();
        Assert.assertEquals(windowPage.getWindowCount(), 2);
        windowPage.switchToWindow(1);
        Assert.assertEquals(windowPage.getWindowTitle(), "Sakinalium | Home");
        Assert.assertEquals(windowPage.getCurrentURL(), "http://www.sakinalium.in/");
        windowPage.gotoMainWindow();
        Assert.assertEquals(windowPage.getCurrentURL(), PageUrls.WINDOW_PAGE_URL);
        windowPage.closeChildWindows();
        windowPage.refreshWindowList();
        Assert.assertEquals(windowPage.getWindowCount(), 1);
        windowPage.gotoMainWindow();
    }

    @AfterClass
    public void cleanup() {
        if (windowPage != null)
            windowPage.deleteInstance();
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}
