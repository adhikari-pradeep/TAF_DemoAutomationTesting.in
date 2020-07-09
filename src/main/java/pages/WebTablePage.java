package pages;

import constants.PageUrls;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WebTablePage extends BasePage {

    private static WebTablePage webTablePage;
    private final WebDriver driver;

    @FindBy(xpath = ".//div[@class='ui-grid-contents-wrapper']")
    private WebElement tableRegistration;

    private WebTablePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public static WebTablePage getInstance(WebDriver driver) {
        if (webTablePage == null)
            webTablePage = new WebTablePage(driver);
        return webTablePage;
    }

    @Override
    void loadPage() {
        driver.navigate().to(PageUrls.WEB_TABLE_PAGE_URL);
        driver.manage().window().maximize();
    }

    @Override
    public void deleteInstance() {
        webTablePage = null;
    }

    public boolean isWebTablePageLoaded() {
        return isPageLoaded(tableRegistration);
    }
}
