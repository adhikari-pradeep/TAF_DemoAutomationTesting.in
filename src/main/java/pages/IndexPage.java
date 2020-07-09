package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import constants.PageUrls;

public class IndexPage extends BasePage {

    private static IndexPage indexPage;
    private final WebDriver driver;

    @FindBy(id = "btn2")
    private WebElement btnSkipSignIn;

    private IndexPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public static IndexPage getInstance(WebDriver driver) {
        if (indexPage == null)
            indexPage = new IndexPage(driver);
        return indexPage;
    }

    public void deleteInstance() {
        indexPage = null;
    }

    public boolean isIndexPageLoaded() {
        return isPageLoaded(btnSkipSignIn);
    }

    @Override
    public void loadPage() {
        driver.navigate().to(PageUrls.INDEX_PAGE_URL);
        maximizeBrowserWindow();
    }

    public RegisterPage skipSignIn() {
        btnSkipSignIn.click();
        return RegisterPage.getInstance(driver);
    }
}
