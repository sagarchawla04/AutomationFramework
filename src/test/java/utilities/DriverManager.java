package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.DesiredCapabilities;
import stepDefinitions.BaseClass;

import java.io.FileInputStream;
import java.util.Properties;

public class DriverManager extends BaseClass {
    /*
    public static WebDriver driver;
    public DriverManager() {
        this.driver=getDriver();
    }

    public static WebDriver getDriver()
    {
        return threadLocal.get();
    }

    /**
     * Method is to load the properties of conig.properties
     */
    public void LoadProperties() {
        try {
            configProp = new Properties();
            FileInputStream configPropFile = new FileInputStream("config.properties");
            configProp.load(configPropFile);
            // maximumTimeout= Long.parseLong(configProp.getProperty("maximumTimeout"));
        } catch (Exception e) {
            Assert.fail("Timed out !!! Application didn't load. Aborting !!!");
        }
    }

    /**
     * Method is to set the logger configuration to enable the logs
     */
    public void SetLoggerConfiguration() {
        logger = Logger.getLogger(this.getClass().getName());
        PropertyConfigurator.configure("log4j.properties"); // Added Logger
        logger.setLevel(Level.DEBUG);
    }

    /**
     * Method is to launch the browser
     */
    public WebDriver launchBrowser() {
        String browser = configProp.getProperty("browser");
        // Launching browser
        if (browser.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", configProp.getProperty("firefoxpath"));
            driver = new FirefoxDriver();
           // threadLocal.set(new FirefoxDriver());
        } else if (browser.equals("chrome")) {
            System.setProperty("webdriver.chrome.silentOutput", "true");
            DesiredCapabilities capabilities = getChromeDesiredCapabilities();
           //System.setProperty("webdriver.chrome.driver", configProp.getProperty("chromepath"));
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(capabilities);
          //  threadLocal.set(new ChromeDriver(capabilities));
        } else if (browser.equals("ie")) {
            System.setProperty("webdriver.ie.driver", configProp.getProperty("iepath"));
            driver = new InternetExplorerDriver();
            //threadLocal.set(new InternetExplorerDriver());
        }
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        //getDriver().manage().window().maximize();
        //getDriver().manage().deleteAllCookies();
        logger.info("*****Browser launched : " + browser + "*****");
        return driver;

        /*
         * Used for Webdriver manager and suppress logging org.apache.log4j.Logger
         * logger4j = org.apache.log4j.Logger.getRootLogger();
         * logger4j.setLevel(org.apache.log4j.Level.toLevel("INFO"));
         *
         * System.setProperty("webdriver.chrome.silentOutput", "true");
         *
         * WebDriverManager.chromedriver().setup(); driver = new ChromeDriver();
         */
    }

    /**
     * Method is to set the required configuration for chrome browser
     */
    public DesiredCapabilities getChromeDesiredCapabilities() {
        LoggingPreferences logs = new LoggingPreferences();
        logs.enable("browser", java.util.logging.Level.ALL);
        logs.enable("client", java.util.logging.Level.ALL);
        logs.enable("driver", java.util.logging.Level.ALL);
        logs.enable("server", java.util.logging.Level.ALL);
        logs.enable("performance", java.util.logging.Level.ALL);
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("ensureCleanSession", true);
        capabilities.setCapability("loggingPrefs", logs);
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments(new String[]{"--disable-gpu"});
        chromeOptions.addArguments(new String[]{"start-maximized"});
        chromeOptions.addArguments(new String[]{"--disable-web-security"});
        chromeOptions.addArguments(new String[]{"--test-type"});
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NONE);
        chromeOptions.addArguments(new String[]{"--log-level=3"});
        capabilities.setCapability("chrome.verbose", false);
        capabilities.setCapability("goog:chromeOptions", chromeOptions);
        return capabilities;
    }

}
