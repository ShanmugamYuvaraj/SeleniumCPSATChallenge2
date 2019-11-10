package commonutils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseClass {

    public static WebDriver driver;

    /**
     * Used to launch the browsers based on the user input
     *
     * @param browser
     */
    public static void launchBrowser(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--disable-notifications");

            System.setProperty("webdriver.chrome.driver", Constants.chromeDriverPath);
            driver = new ChromeDriver(chromeOptions);

        } else if (browser.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", Constants.geckoDriverPath);
            driver = new FirefoxDriver();
        }
    }
}
