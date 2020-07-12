package pages.widgets;

import constants.URLConstants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.BasePage;

public class AccordionPage extends BasePage {

    private static AccordionPage accordionPage;
    private final WebDriver driver;

    @FindBy(xpath = ".//a[@href='#collapse1']/b")
    private WebElement collapsibleGroup1;
    @FindBy(xpath = ".//div[@id='collapse1']/div[@class='panel-body']")
    private WebElement group1Text;
    @FindBy(xpath = ".//a[@href='#collapse2']/b")
    private WebElement collapsibleGroup2;
    @FindBy(xpath = ".//div[@id='collapse2']/div[@class='panel-body']")
    private WebElement group2Text;
    @FindBy(xpath = ".//a[@href='#collapse3']/b")
    private WebElement collapsibleGroup3;
    @FindBy(xpath = ".//div[@id='collapse3']/div[@class='panel-body']")
    private WebElement group3Text;
    @FindBy(xpath = ".//a[@href='#collapse4']/b")
    private WebElement collapsibleGroup4;
    @FindBy(xpath = ".//div[@id='collapse4']/div[@class='panel-body']")
    private WebElement group4Text;

    AccordionPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public static AccordionPage getInstance(WebDriver driver) {
        if (accordionPage == null)
            accordionPage = new AccordionPage(driver);
        return accordionPage;
    }

    public boolean isAccordionPageLoaded() {
        return isPageLoaded(collapsibleGroup1, collapsibleGroup2,
                collapsibleGroup3, collapsibleGroup4, group1Text);
    }

    @Override
    public void loadPage() {
        driver.navigate().to(URLConstants.ACCORDION_PAGE_URL);
        maximizeBrowserWindow();
    }

    @Override
    public void deleteInstance() {
        accordionPage = null;
    }

    public boolean isGroupExpanded(int groupNumber) {
        if (groupNumber == 1)
            return !group1Text.getText().equals("");
        else if (groupNumber == 2)
            return !group2Text.getText().equals("");
        else if (groupNumber == 3)
            return !group3Text.getText().equals("");
        else if (groupNumber == 4)
            return !group4Text.getText().equals("");
        else return false;
    }

    public void expandGroup(int groupNumber) {
        if (groupNumber == 1) {
            if (!isGroupExpanded(groupNumber))
                collapsibleGroup1.click();
        }
        else if (groupNumber == 2) {
            if (!isGroupExpanded(groupNumber))
                collapsibleGroup2.click();
        }
        else if (groupNumber == 3) {
            if (!isGroupExpanded(groupNumber))
                collapsibleGroup3.click();
        }
        else if (groupNumber == 4) {
            if (!isGroupExpanded(groupNumber))
                collapsibleGroup4.click();
        }
        refreshLocators(this);
    }

    public void collapseGroup(int groupNumber) {
        if (groupNumber == 1) {
            if (isGroupExpanded(groupNumber)) {
                collapsibleGroup1.click();
                waitUntilElementNotVisible(group1Text);
            }
        }
        else if (groupNumber == 2) {
            if (isGroupExpanded(groupNumber)) {
                collapsibleGroup2.click();
                waitUntilElementNotVisible(group2Text);
            }
        }
        else if (groupNumber == 3) {
            if (isGroupExpanded(groupNumber)) {
                collapsibleGroup3.click();
                waitUntilElementNotVisible(group3Text);
            }
        }
        else if (groupNumber == 4) {
            if (isGroupExpanded(groupNumber)) {
                collapsibleGroup4.click();
                waitUntilElementNotVisible(group4Text);
            }
        }
        refreshLocators(this);
    }

    public String getGroupText(int groupNumber) {
        if (groupNumber == 1)
            return group1Text.getText();
        else if (groupNumber == 2)
            return group2Text.getText();
        else if (groupNumber == 3)
            return group3Text.getText();
        else if (groupNumber == 4)
            return group4Text.getText();
        else return "";
    }
}
