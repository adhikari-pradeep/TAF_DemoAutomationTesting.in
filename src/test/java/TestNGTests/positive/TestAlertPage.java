package TestNGTests.positive;

import exception.UnsupportedBrowserException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.switchables.AlertPage;
import utility.BrowserFactory;
import utility.DataProvider;

import java.io.IOException;

public class TestAlertPage {

    private WebDriver driver;
    private AlertPage alertPage;

    @BeforeClass
    public void setup() throws IOException, UnsupportedBrowserException {
        driver = BrowserFactory.getWebDriver();
        alertPage = AlertPage.getInstance(driver);
    }

    @Test
    public void testLoadAlertPage() {
        alertPage.loadPage();
        Assert.assertTrue(alertPage.isAlertPageLoaded());
    }

    @Test(dependsOnMethods = "testLoadAlertPage")
    public void testAlertWithOk() {
        alertPage.acceptAlertWithOk();
        alertPage = alertPage.refreshPage(driver);
        Assert.assertTrue(alertPage.isAlertPageLoaded());
    }

    @Test(dependsOnMethods = "testLoadAlertPage")
    public void testAlertWithOkAndCancel() {
        Assert.assertEquals(alertPage.acceptAlertWithOkAndCancel(),
                "You pressed Ok");
        Assert.assertEquals(alertPage.cancelAlertWithOkAndCancel(),
                "You Pressed Cancel");
        alertPage = alertPage.refreshPage(driver);
        Assert.assertTrue(alertPage.isAlertPageLoaded());
    }

    @Test(dependsOnMethods = "testLoadAlertPage")
    public void testAlertWithTextBox() {
        String name = DataProvider.getName();
        Assert.assertEquals(alertPage.acceptAlertWithTextBox(name),
                "Hello "+name+" How are you today");
        Assert.assertFalse(alertPage.cancelAlertWithTextBox(name));
        Assert.assertFalse(alertPage.cancelAlertWithTextBox());
        alertPage = alertPage.refreshPage(driver);
        Assert.assertTrue(alertPage.isAlertPageLoaded());
    }

    @AfterClass
    public void cleanup() {
        if (alertPage != null)
            alertPage.deleteInstance();
        driver.manage().deleteAllCookies();
        driver.close();
    }
}
