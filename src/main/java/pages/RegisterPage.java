package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import constants.URLConstants;
import org.openqa.selenium.support.ui.Select;

public class RegisterPage extends BasePage {

    private static RegisterPage registerPage;
    private final WebDriver driver;

    @FindBy(xpath = ".//input[@placeholder='First Name']")
    private WebElement inputFirstName;
    @FindBy(xpath = ".//input[@placeholder='Last Name']")
    private WebElement inputLastName;
    @FindBy(xpath = ".//input[@type='email']")
    private WebElement inputEmail;
    @FindBy(xpath = ".//input[@type='tel']")
    private WebElement inputPhone;
    @FindBy(xpath = ".//input[@value='Male']")
    private WebElement radioGenderM;
    @FindBy(xpath = ".//input[@value='FeMale']")
    private WebElement radioGenderF;
    @FindBy(id = "firstpassword")
    private WebElement inputPassword;
    @FindBy(id = "secondpassword")
    private WebElement inputConfirmPassword;
    @FindBy(id = "submitbtn")
    private WebElement btnSubmit;

    private Select selectCountry;
    private Select selectYearDOB;
    private Select selectMonthDOB;
    private Select selectDayDOB;


    private RegisterPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private void initializeSelects(WebDriver driver) {
        selectCountry = new Select(driver.findElement(By.xpath(".//select[@id='countries']")));
        selectYearDOB = new Select(driver.findElement(By.xpath(".//select[@id='yearbox']")));
        selectMonthDOB = new Select(driver.findElement(By.xpath(".//select[@placeholder='Month']")));
        selectDayDOB = new Select(driver.findElement(By.xpath(".//select[@id='daybox']")));
    }

    public static RegisterPage getInstance(WebDriver driver) {
        if (registerPage == null)
            registerPage = new RegisterPage(driver);
        return registerPage;
    }

    @Override
    public void loadPage() {
        driver.navigate().to(URLConstants.REGISTER_PAGE_URL);
        maximizeBrowserWindow();
        initializeSelects(driver);
    }

    @Override
    public void deleteInstance() {
        registerPage = null;
    }

    public boolean isRegisterPageLoaded() {
        return isPageLoaded(inputFirstName, inputLastName,
                inputEmail, inputPhone,
                radioGenderM, radioGenderF,
                inputPassword, inputConfirmPassword, btnSubmit);
    }

    public void enterFirstName(String firstName) {
        inputFirstName.clear();
        inputFirstName.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        inputLastName.clear();
        inputLastName.sendKeys(lastName);
    }

    public void enterEmail(String email) {
        inputEmail.clear();
        inputEmail.sendKeys(email);
    }

    public void enterPhone(String phone) {
        inputPhone.clear();
        inputPhone.sendKeys(phone);
    }

    public void selectGender(String gender) {
        if (gender.equalsIgnoreCase("Male")
                || gender.equalsIgnoreCase("M"))
            radioGenderM.click();
        else if (gender.equalsIgnoreCase("FeMale")
                || gender.equalsIgnoreCase("F"))
            radioGenderF.click();
    }

    public void selectCountry(String country) {
        scrollToElement(selectCountry);
        country = country.substring(0,1).toUpperCase()+country.substring(1).toLowerCase();
        selectCountry.selectByValue(country);
    }

    public void selectYearDob(String year) {
        selectYearDOB.selectByValue(year);
    }

    public void selectMonthDOB(int month) {
        selectMonthDOB.selectByIndex(month);
    }

    public void selectDayDob(String day) {
        selectDayDOB.selectByValue(day);
    }

    public void enterPassword(String password) {
        inputPassword.clear();
        inputPassword.sendKeys(password);
    }

    public void enterConfirmPassword(String password) {
        inputConfirmPassword.clear();
        inputConfirmPassword.sendKeys(password);
    }

    public WebTablePage register() {
        btnSubmit.click();
        waitUntilPageWithUrlLoads(URLConstants.WEB_TABLE_PAGE_URL);
        waitUntilPageWithTitleLoads(WebTablePage.PAGE_TITLE);
        return WebTablePage.getInstance(driver);
    }
}
