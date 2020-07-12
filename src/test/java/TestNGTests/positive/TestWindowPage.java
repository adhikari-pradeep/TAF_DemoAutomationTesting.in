package TestNGTests.positive;

import constants.URLConstants;
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

    @Test(dependsOnMethods = "testLoadWindowPage", priority = 1)
    public void testNewTabbedWindow() {
        windowPage.getNewTabbedWindow();
        Assert.assertEquals(windowPage.getWindowCount(), 2);
        windowPage.switchToWindow(1);
        windowPage.gotoMainWindow();
        Assert.assertEquals(windowPage.getCurrentURL(), URLConstants.WINDOW_PAGE_URL);
        windowPage.closeChildWindows();
        windowPage.refreshWindowList();
        Assert.assertEquals(windowPage.getWindowCount(), 1);
        windowPage.gotoMainWindow();
        windowPage.refreshPage(driver);
    }

    @Test(dependsOnMethods = "testLoadWindowPage", priority = 2)
    public void testNewSeperateWindow() {
        windowPage.getNewSeperateWindow();
        Assert.assertEquals(windowPage.getWindowCount(), 2);
        windowPage.switchToWindow(1);
        windowPage.gotoMainWindow();
        Assert.assertEquals(windowPage.getCurrentURL(), URLConstants.WINDOW_PAGE_URL);
        windowPage.closeChildWindows();
        windowPage.refreshWindowList();
        Assert.assertEquals(windowPage.getWindowCount(), 1);
        windowPage.gotoMainWindow();
        windowPage.refreshPage(driver);
    }

    @Test(dependsOnMethods = "testLoadWindowPage", priority = 3)
    public void testNewMultipleSeperateWindow() {
        windowPage.getNewMultipleSeperateWindow();
        Assert.assertEquals(windowPage.getWindowCount(), 3);
        windowPage.switchToWindow(1);
        windowPage.switchToWindow(2);
        windowPage.gotoMainWindow();
        Assert.assertEquals(windowPage.getCurrentURL(), URLConstants.WINDOW_PAGE_URL);
        windowPage.closeChildWindows();
        windowPage.refreshWindowList();
        Assert.assertEquals(windowPage.getWindowCount(), 1);
        windowPage.gotoMainWindow();
        windowPage.refreshPage(driver);
    }

    @AfterClass
    public void cleanup() {
        if (windowPage != null)
            windowPage.deleteInstance();
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}
