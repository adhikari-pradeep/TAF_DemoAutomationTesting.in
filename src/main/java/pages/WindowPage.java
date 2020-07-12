package pages;

import constants.URLConstants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class WindowPage extends BasePage {

    private static WindowPage windowPage;
    private final WebDriver driver;
    private String mainWindow;
    private List<String> windows;

    @FindBy(xpath = ".//a[@href='#Tabbed']")
    private WebElement tabNewTabbedWindow;
    @FindBy(xpath = ".//div[@id='Tabbed']/a/button")
    private WebElement btnNewTabbedWindow;
    @FindBy(xpath = ".//a[@href='#Seperate']")
    private WebElement tabNewSeperateWindow;
    @FindBy(xpath = ".//button[@onclick='newwindow()']")
    private WebElement btnNewSeperateWindow;
    @FindBy(xpath = ".//a[@href='#Multiple']")
    private WebElement tabNewSeperateMultipleWindow;
    @FindBy(xpath = ".//button[@onclick='multiwindow()']")
    private WebElement btnNewSeperateMultipleWindow;


    private WindowPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public static WindowPage getInstance(WebDriver driver) {
        if (windowPage == null)
            windowPage = new WindowPage(driver);
        return windowPage;
    }

    @Override
    public void loadPage() {
        driver.navigate().to(URLConstants.WINDOW_PAGE_URL);
        maximizeBrowserWindow();
    }

    @Override
    public void deleteInstance() {
        windowPage = null;
    }

    public boolean isWindowPageLoaded() {
        return isPageLoaded(tabNewTabbedWindow, tabNewSeperateWindow,
                tabNewSeperateMultipleWindow, btnNewTabbedWindow);
    }

    public void getNewTabbedWindow() {
        mainWindow = driver.getWindowHandle();
        btnNewTabbedWindow.click();
        refreshWindowList();
    }

    public void getNewSeperateWindow() {
        mainWindow = driver.getWindowHandle();
        tabNewSeperateWindow.click();
        refreshLocators(this);
        btnNewSeperateWindow.click();
        refreshWindowList();
    }

    public void getNewMultipleSeperateWindow() {
        mainWindow = driver.getWindowHandle();
        tabNewSeperateMultipleWindow.click();
        refreshLocators(this);
        btnNewSeperateMultipleWindow.click();
        refreshWindowList();
    }

    public int getWindowCount() {
        return windows.size();
    }

    public void refreshWindowList() {
        windows = new ArrayList<>(driver.getWindowHandles());
    }

    public void switchToWindow(int index) {
        driver.switchTo().window(windows.get(index));
    }

    public void closeChildWindows() {
        for (int i=1; i<windows.size(); i++) {
            driver.switchTo().window(windows.get(i));
            driver.close();
        }
    }

    public void gotoMainWindow() {
        driver.switchTo().window(mainWindow);
    }

    public WindowPage refreshPage(WebDriver driver) {
        super.refreshPage();
        deleteInstance();
        return getInstance(driver);
    }
}
