package utility;

import constants.WebDriverConstants;
import exception.UnsupportedBrowserException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.IOException;

public class BrowserFactory {

    public static WebDriver getWebDriver() throws IOException, UnsupportedBrowserException {
        String browserName = ConfigurationReader.getConfigurationData("browser");
        if (browserName.equalsIgnoreCase("CHROME")) {
            System.setProperty(WebDriverConstants.CHROME_DRIVER_KEY,
                    WebDriverConstants.BASE_DRIVER_PATH+"chromedriver.exe");
            return new ChromeDriver();
        }
        else if (browserName.equalsIgnoreCase("FIREFOX")) {
            System.setProperty(WebDriverConstants.FIREFOX_DRIVER_KEY,
                    WebDriverConstants.BASE_DRIVER_PATH+"geckodriver.exe");
            return new FirefoxDriver();
        }
        else if (browserName.equalsIgnoreCase("EDGE")) {
            System.setProperty(WebDriverConstants.EDGE_DRIVER_KEY,
                    WebDriverConstants.BASE_DRIVER_PATH+"msedgedriver.exe");
            return new EdgeDriver();
        }
        else throw new UnsupportedBrowserException(browserName);
    }
}
