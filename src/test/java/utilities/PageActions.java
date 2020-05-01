package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import stepDefinitions.BaseClass;

public class PageActions extends BaseClass {
//    public WebDriver driver;
//
//    public PageActions(WebDriver driver1) {
//        this.driver = driver1;
//    }

    /**
     * Method is to return the driver
     */
    public static WebDriver getWebdriver() {
        return driver;
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
     * Method is to generate random string
     */
    public String generateRamdomString() {
        String generatedString = RandomStringUtils.randomAlphabetic(5);
        return (generatedString);
    }

    /**
     * Method is to generate random numeric digits
     */
    public String generateRamdomNumerals() {
        String generatedString = RandomStringUtils.randomNumeric(5);
        return (generatedString);
    }

    /**
     * Method is to get the name of screenshot
     */
    public String Screenshotname() {
        return screenshotname = scenario.getName() + "_Test_" + generateRamdomNumerals();
        // return screenshotname = "Failed_"+generateRamdomString();
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
        } else if (browser.equals("chrome")) {
            System.setProperty("webdriver.chrome.silentOutput", "true");
            DesiredCapabilities capabilities = getChromeDesiredCapabilities();
            System.setProperty("webdriver.chrome.driver", configProp.getProperty("chromepath"));
            driver = new ChromeDriver(capabilities);
        } else if (browser.equals("ie")) {
            System.setProperty("webdriver.ie.driver", configProp.getProperty("iepath"));
            driver = new InternetExplorerDriver();
        }

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        log("*****Browser launched : " + browser + "*****");
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
     * Method is to print the message in console
     */
    public void log(String message) {
        logger.info(message);
    }

    /**
     * Method is to launch the url
     */
    public void launchUrl(String url) {
        try {
            driver.get(url);
            log("*****Launched url : " + url + "*****");
        } catch (TimeoutException e) {
            Assert.fail("Timed out !!! Application didn't load. Aborting !!!");
        }
    }

    /**
     * Method is to get the current url
     */
    public String getCurrentUrl() {
        log("*****Current page URL : " + driver.getCurrentUrl() + "*****");
        return driver.getCurrentUrl();
    }

    /**
     * Method is to get the page title
     */
    public String getPageTitle() {
        log("*****The Title of the page : " + driver.getTitle() + "*****");
        return driver.getTitle();
    }

    /**
     * Method is to click on the element
     */
    public void click(WebElement element) {
        try {
            element.click();
            log("*****Element clicked : " + element.toString() + "*****");
        } catch (Exception e) {
            Assert.fail("Test failed with exception ---> " + e.toString());
        }
    }

    public void click(By by) {
        this.click(WaitForElementToBeClickable(by));
    }

    /**
     * Method is to click on element using javascript executor
     */
    public void clickByJavaScript(By by) {
        this.clickByJavaScript(WaitForElementToBeClickable(by));
    }

    public void clickByJavaScript(WebElement element) {
        try {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", element);
            log("*****Element clicked using javascript executor : " + element.toString() + "*****");
        } catch (Exception e) {
            Assert.fail("Test failed with exception ---> " + e.toString());
        }
    }

    /**
     * Method is to wait for element to be clickable on the page
     */
    public WebElement WaitForElementToBeClickable(By by) {
        WebElement webelement = null;
        try {
            WebDriverWait wait = new WebDriverWait(driver, maximumTimeout);
            webelement = wait.until(ExpectedConditions.elementToBeClickable(by));
            log("*****Element " + by.toString() + " clickable on the page*****");
        } catch (Exception e) {
            Assert.fail("Test failed with exception ---> " + e.toString());
        }
        return webelement;
    }

    /**
     * Method is to set text in textbox field
     */
    public void setText(WebElement element, String value) {
        try {
            element.clear();
            element.sendKeys(new CharSequence[]{value});
            log("*****Text: " + value + " entered in " + element.toString() + "*****");
        } catch (Exception e) {
            Assert.fail("Test failed with exception ---> " + e.toString());
        }
    }

    public void setText(By by, String value) {
        this.setText(findObject(by), value);
    }

    /**
     * Method is to find the object using by class and return webelement
     */
    public WebElement findObject(By by) {
        WebElement element = null;
        try {
            WebDriverWait wait = new WebDriverWait(driver, maximumTimeout);
            element = (WebElement) wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            log("*****Element " + by.toString() + " found on the page*****");
        } catch (Exception e) {
            log("*****Element " + by.toString() + " not found on the page*****");
            captureScreen(driver, Screenshotname());
            Assert.fail("Test failed with exception ---> " + e.toString());
        }
        return element;
    }

    /**
     * Method is to wait for the page to load
     */
    public void waitForPageLoad(WebDriver driver) {
        ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };
        WebDriverWait wait = new WebDriverWait(driver, maximumTimeout);
        wait.until(pageLoadCondition);
        log("*****Page loaded successfully*****");
    }

    /**
     * Method is to capture the screenshot
     */
    public void captureScreen(WebDriver driver, String tname) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            File target = new File(System.getProperty("user.dir") + "\\Screenshots\\" + tname + ".png");
            FileUtils.copyFile(source, target);
            log("*****Screenshot captured successfully*****");
        } catch (Exception e) {
            Assert.fail("Test failed with exception ---> " + e.toString());
        }
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

    /**
     * Method is to wait for specific seconds using thread.sleep
     */
    public void waitForSeconds(int d) {
        try {
            Thread.sleep((long) (d * 1000));
        } catch (Exception e) {
            Assert.fail("Test failed with exception ---> " + e.toString());
        }
    }

    /**
     * Method is to scroll to page end
     */
    public void scrollToPageEnd() {
        Actions builder = new Actions(driver);
        builder.sendKeys(new CharSequence[]{Keys.END}).perform();
    }

    /**
     * Method is to hit the keys with keyboard mimic
     */
    public void hitKeys(String key) {
        Actions builder = new Actions(driver);
        if (key.equals("tab")) {
            builder.sendKeys(new CharSequence[]{Keys.TAB}).perform();
        } else if (key.equals("enter")) {
            builder.sendKeys(new CharSequence[]{Keys.ENTER}).perform();
        } else if (key.equals("down")) {
            builder.sendKeys(new CharSequence[]{Keys.DOWN}).perform();
        } else if (key.equals("up")) {
            builder.sendKeys(new CharSequence[]{Keys.UP}).perform();
        } else if (key.equals("left")) {
            builder.sendKeys(new CharSequence[]{Keys.LEFT}).perform();
        } else if (key.equals("right")) {
            builder.sendKeys(new CharSequence[]{Keys.RIGHT}).perform();
        } else if (key.equals("pagedown")) {
            builder.sendKeys(new CharSequence[]{Keys.PAGE_DOWN}).perform();
        }
    }
}
