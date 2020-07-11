package TestNGTests.positive;

import exception.UnsupportedBrowserException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.FramePage;
import utility.BrowserFactory;
import utility.UserProvider;

import java.io.IOException;

public class TestFramePage {

    private WebDriver driver;
    private FramePage framePage;

    @BeforeClass
    public void setup() throws IOException, UnsupportedBrowserException {
        driver = BrowserFactory.getWebDriver();
        framePage = FramePage.getInstance(driver);
    }

    @Test
    public void testLoadFramePage() {
        framePage.loadPage();
        Assert.assertTrue(framePage.isFramePageLoaded());
    }

    @Test(dependsOnMethods = "testLoadFramePage", priority = 1)
    public void testSingleFrame() {
        String text = UserProvider.getName();
        String number = UserProvider.getPhone();
        framePage.switchToSingleFrame();
        framePage.enterTextToInput(text);
        Assert.assertEquals(framePage.getInputBoxText(), text);
        framePage.enterTextToInput(number);
        Assert.assertEquals(framePage.getInputBoxText(), number);
        framePage.switchBackToParentFrame();
    }

    @Test(dependsOnMethods = "testLoadFramePage", priority = 2)
    public void testMultipleNestedFrame() {
        String text = UserProvider.getName();
        String number = UserProvider.getPhone();
        framePage.switchToNestedFrame();
        framePage.enterTextToInput(text);
        Assert.assertEquals(framePage.getInputBoxText(), text);
        framePage.enterTextToInput(number);
        Assert.assertEquals(framePage.getInputBoxText(), number);
        framePage.switchBackToParentFrame();
    }

    @AfterClass
    public void cleanup() {
        framePage.deleteInstance();
        driver.manage().deleteAllCookies();
        driver.close();
    }
}
