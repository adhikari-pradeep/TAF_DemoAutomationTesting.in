package pages.widgets;

import constants.URLConstants;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.BasePage;

import java.util.List;

public class AutoCompletePage extends BasePage {

    private static AutoCompletePage autoCompletePage;
    private final WebDriver driver;

    private static final String SUGGESTIONS_LIST_XPATH = ".//ul[@id='ui-id-1']/li";

    @FindBy(xpath = ".//input[@id='searchbox']")
    private WebElement inputSearchBox;
    @FindBy(xpath = SUGGESTIONS_LIST_XPATH)
    private List<WebElement> suggestionList;
    @FindBy(xpath = ".//div[@class='ui-autocomplete-multiselect-item']")
    private WebElement selectedSuggestion;

    AutoCompletePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public static AutoCompletePage getInstance(WebDriver driver) {
        if (autoCompletePage == null)
            autoCompletePage = new AutoCompletePage(driver);
        return autoCompletePage;
    }

    @Override
    public void loadPage() {
        driver.navigate().to(URLConstants.AUTOCOMPLETE_PAGE_URL);
        maximizeBrowserWindow();
    }

    @Override
    public void deleteInstance() {
        autoCompletePage = null;
    }

    public boolean isAutoCompletePageLoaded() {
        return isPageLoaded(inputSearchBox);
    }

    public void enterTextToSearch(String text) {
        inputSearchBox.clear();
        inputSearchBox.sendKeys(text);
        waitUntilAllElementVisible(SUGGESTIONS_LIST_XPATH);
        refreshLocators(this);
    }

    public String getTextFromSearchField() {
        return selectedSuggestion.getText();
    }

    public void selectAutoCompleteData(int number) {
        if (suggestionList.size() < number) {
            throw new InvalidArgumentException("Only "+suggestionList.size()+" number of data available");
        }
        else suggestionList.get(number-1).click();
        refreshLocators(this);
    }
}
