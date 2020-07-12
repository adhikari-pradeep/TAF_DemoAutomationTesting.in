package pages.switchables;

import constants.URLConstants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.BasePage;

public class AlertPage extends BasePage {

    private static AlertPage alertPage;
    private final WebDriver driver;

    @FindBy(xpath = ".//a[@href='#OKTab']")
    private WebElement tabAlertWithOk;
    @FindBy(xpath = ".//button[@onclick='alertbox()']")
    private WebElement btnAlertWithOk;
    @FindBy(xpath = ".//a[@href='#CancelTab']")
    private WebElement tabAlertWithOkAndCancel;
    @FindBy(xpath = ".//button[@onclick='confirmbox()']")
    private WebElement btnAlertWithOkAndCancel;
    @FindBy(id = "demo")
    private WebElement alertConfirmResponseText;
    @FindBy(xpath = ".//a[@href='#Textbox']")
    private WebElement tabAlertWithTextBox;
    @FindBy(xpath = ".//button[@onclick='promptbox()']")
    private WebElement btnAlertWithTextBox;
    @FindBy(id = "demo1")
    private WebElement alertTextResponseText;

    private AlertPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public static AlertPage getInstance(WebDriver driver) {
        if (alertPage == null)
            alertPage = new AlertPage(driver);
        return alertPage;
    }

    @Override
    public void loadPage() {
        driver.navigate().to(URLConstants.ALERT_PAGE_URL);
        maximizeBrowserWindow();
    }

    @Override
    public void deleteInstance() {
        alertPage = null;
    }

    public AlertPage refreshPage(WebDriver driver) {
        super.refreshPage();
        deleteInstance();
        return getInstance(driver);
    }

    public boolean isAlertPageLoaded() {
        return isPageLoaded(tabAlertWithOk, tabAlertWithOkAndCancel,
                tabAlertWithTextBox, btnAlertWithOk);
    }

    public void acceptAlertWithOk() {
        btnAlertWithOk.click();
        driver.switchTo().alert().accept();
    }

    public String acceptAlertWithOkAndCancel() {
        tabAlertWithOkAndCancel.click();
        refreshLocators(this);
        btnAlertWithOkAndCancel.click();
        driver.switchTo().alert().accept();
        refreshLocators(this);
        return alertConfirmResponseText.getText();
    }

    public String cancelAlertWithOkAndCancel() {
        tabAlertWithOkAndCancel.click();
        refreshLocators(this);
        btnAlertWithOkAndCancel.click();
        driver.switchTo().alert().dismiss();
        refreshLocators(this);
        return alertConfirmResponseText.getText();
    }

    public String acceptAlertWithTextBox(String text) {
        tabAlertWithTextBox.click();
        refreshLocators(this);
        btnAlertWithTextBox.click();
        driver.switchTo().alert().sendKeys(text);
        driver.switchTo().alert().accept();
        refreshLocators(this);
        return alertTextResponseText.getText();
    }

    public boolean cancelAlertWithTextBox(String text) {
        tabAlertWithTextBox.click();
        refreshLocators(this);
        btnAlertWithTextBox.click();
        driver.switchTo().alert().sendKeys(text);
        driver.switchTo().alert().dismiss();
        refreshLocators(this);
        return alertTextResponseText.isDisplayed();
    }

    public boolean cancelAlertWithTextBox() {
        tabAlertWithTextBox.click();
        refreshLocators(this);
        btnAlertWithTextBox.click();
        driver.switchTo().alert().dismiss();
        refreshLocators(this);
        return alertTextResponseText.isDisplayed();
    }
}
