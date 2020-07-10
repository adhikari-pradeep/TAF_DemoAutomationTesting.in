package pages;

import constants.PageUrls;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {

    private static final int TIMEOUT_IN_SECONDS = 10;

    private final WebDriver driver;
    private final WebDriverWait wait;

    BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, TIMEOUT_IN_SECONDS);
    }

    public abstract void loadPage();

    public abstract void deleteInstance();

    protected void refreshPage() {
        driver.navigate().refresh();
    }

    public void refreshLocators(BasePage page) {
        PageFactory.initElements(driver, page);
    }

    public void maximizeBrowserWindow() {
        driver.manage().window().maximize();
    }

    public String getWindowTitle() {
        return driver.getTitle();
    }

    public String getCurrentURL() {
        return driver.getCurrentUrl();
    }

    public boolean isPageLoaded(WebElement... elements) {
        boolean loaded = true;
        for (WebElement element : elements) {
            if (!isElementDisplayed(element)) {
                loaded = false;
                break;
            }
        }
        return loaded;
    }

    public boolean isElementDisplayed(WebElement element) {
        if (element.isDisplayed()) {
            return true;
        }
        else {
            waitUntilElementVisible(element);
            return element.isDisplayed();
        }
    }

    void waitUntilPageWithUrlLoads(String url) {
        wait.until(ExpectedConditions.urlToBe(url));
    }

    void waitUntilPageWithTitleLoads(String title) {
        wait.until(ExpectedConditions.titleIs(title));
    }

    void waitUntilElementVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    void waitUntilElementClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}
