package TestNGTests.positive;

import exception.UnsupportedBrowserException;
import model.User;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.RegisterPage;
import pages.WebTablePage;
import utility.BrowserFactory;
import utility.UserProvider;

import java.io.IOException;

public class TestRegisterPage {

    private WebDriver driver;
    private RegisterPage registerPage;
    private WebTablePage webTablePage;

    @BeforeClass
    public void setup() throws IOException, UnsupportedBrowserException {
        driver = BrowserFactory.getWebDriver();
        registerPage = RegisterPage.getInstance(driver);
    }

    @Test
    public void testLoadRegisterPage() {
        registerPage.loadPage();
        Assert.assertTrue(registerPage.isPageLoaded());
    }

    @Test
    public void testRegistrationFunction() throws IOException {
        User user = UserProvider.getUser();
        registerPage.enterFirstName(user.getFirstName());
        registerPage.enterLastName(user.getLastName());
        registerPage.enterEmail(user.getEmail());
        registerPage.enterPhone(user.getPhone());
        registerPage.selectGender(user.getGender());
        registerPage.selectCountry(user.getCountry());
        registerPage.selectYearDob(user.getYear());
        registerPage.selectMonthDOB(user.getMonth());
        registerPage.selectDayDob(user.getDate());
        registerPage.enterPassword(user.getPassword());
        registerPage.enterConfirmPassword(user.getPassword());
        webTablePage = registerPage.register();
        Assert.assertTrue(webTablePage.isWebTablePageLoaded());
    }

    @AfterClass
    public void cleanup() {
        registerPage.deleteInstance();
        webTablePage.deleteInstance();
        driver.manage().deleteAllCookies();
        driver.close();
    }
}
