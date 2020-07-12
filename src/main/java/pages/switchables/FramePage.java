package pages.switchables;

import constants.URLConstants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.BasePage;

public class FramePage extends BasePage {

    private static FramePage framePage;
    private final WebDriver driver;

    @FindBy(xpath = ".//a[@href='#Single']")
    private WebElement btnSingleFrame;
    @FindBy(xpath = ".//a[@href='#Multiple']")
    private WebElement btnNestedFrame;
    @FindBy(xpath = ".//iframe[@id='singleframe']")
    private WebElement frameSingle;
    @FindBy(xpath = ".//div[@id='Multiple']/iframe")
    private WebElement multipleFrameParent;
    @FindBy(xpath = ".//div[@class='row']/iframe[@src='SingleFrame.html']")
    private WebElement multipleFrameChild;
    @FindBy(xpath = ".//div[@class='container']//input[@type='text']")
    private WebElement inputTextBox;

    FramePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public static FramePage getInstance(WebDriver driver) {
        if (framePage == null)
            framePage = new FramePage(driver);
        return framePage;
    }

    @Override
    public void loadPage() {
        driver.navigate().to(URLConstants.FRAME_PAGE_URL);
        maximizeBrowserWindow();
    }

    @Override
    public void deleteInstance() {
        framePage = null;
    }

    public boolean isFramePageLoaded() {
        return isPageLoaded(btnSingleFrame, btnNestedFrame, frameSingle);
    }

    public void switchToSingleFrame() {
        btnSingleFrame.click();
        driver.switchTo().frame(frameSingle);
        refreshLocators(this);
    }

    public void switchToNestedFrame() {
        btnNestedFrame.click();
        driver.switchTo().frame(multipleFrameParent);
        refreshLocators(this);
        driver.switchTo().frame(multipleFrameChild);
        refreshLocators(this);
    }

    public void switchBackToParentFrame() {
        driver.switchTo().parentFrame();
        refreshLocators(this);
    }

    public void enterTextToInput(String text) {
        inputTextBox.clear();
        inputTextBox.sendKeys(text);
    }

    public String getInputBoxText() {
        return inputTextBox.getAttribute("value");
    }
}
