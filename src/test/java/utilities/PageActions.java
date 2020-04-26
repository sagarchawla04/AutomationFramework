package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import stepDefinitions.BaseClass;

public class PageActions extends BaseClass {
	public WebDriver driver;

	public PageActions(WebDriver driver1) {
		this.driver = driver1;
	}

	/**
	 * Method is to load the properties of conig.properties
	 * 
	 */
	public void LoadProperties() throws IOException {
		configProp = new Properties();
		FileInputStream configPropFile = new FileInputStream("config.properties");
		configProp.load(configPropFile);
		// maximumTimeout= Long.parseLong(configProp.getProperty("maximumTimeout"));
	}

	/**
	 * Method is to set the logger configuration to enable the logs
	 * 
	 */
	public void SetLoggerConfiguration() {
		logger = Logger.getLogger(this.getClass().getName());
		PropertyConfigurator.configure("log4j.properties"); // Added Logger
		logger.setLevel(Level.DEBUG);
	}

	/**
	 * Method is to generate random string
	 * 
	 */
	public static String ramdomString() {
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return (generatedString);
	}

	/**
	 * Method is to launch the browser
	 * 
	 */
	public WebDriver launchBrowser() {
		String browser = configProp.getProperty("browser");
		// Launching browser
		if (browser.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", configProp.getProperty("firefoxpath"));
			driver = new FirefoxDriver();
		}

		else if (browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.silentOutput", "true");
			ChromeOptions options = new ChromeOptions();
			options.setPageLoadStrategy(PageLoadStrategy.NONE);
			System.setProperty("webdriver.chrome.driver", configProp.getProperty("chromepath"));
			driver = new ChromeDriver(options);
			System.setProperty("webdriver.chrome.silentOutput", "true");
		}

		else if (browser.equals("ie")) {
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
	 * 
	 */
	public void log(String message) {
		logger.info(message);
	}

	/**
	 * Method is to launch the url
	 * 
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
	 * 
	 */
	public String getCurrentUrl() {
		log("*****Current page URL : " + driver.getCurrentUrl() + "*****");
		return driver.getCurrentUrl();
	}

	/**
	 * Method is to get the page title
	 * 
	 */
	public String getPageTitle() {
		log("*****The Title of the page : " + driver.getTitle() + "*****");
		return driver.getTitle();
	}

	/**
	 * Method is to click on the element
	 * 
	 */
	public void click(WebElement element) {
		element.click();
		log("*****Element clicked : " + element.toString() + "*****");
	}

	public void click(By by) {
		this.click(findObject(by));
	}

	/**
	 * Method is to click on element using javascript executor
	 * 
	 */
	public void clickByJavaScript(By by) {
		this.clickByJavaScript(findObject(by));
	}

	public void clickByJavaScript(WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
		log("*****Element clicked using javascript executor : " + element.toString() + "*****");
	}

	/**
	 * Method is to wait for element to be visible on the page
	 * 
	 */
	public void WaitForElementToBeVisible(By by) {
		WebElement element = findObject(by);
		WebDriverWait wait = new WebDriverWait(driver, maximumTimeout);
		wait.until(ExpectedConditions.visibilityOf(element));
		log("*****Element " + by.toString() + " visible on the page*****");
	}

	/**
	 * Method is to wait for element to be clickable on the page
	 * 
	 */
	public WebElement WaitForElementToBeClickable(By by) {
		WebElement webelement = null;
		WebDriverWait wait = new WebDriverWait(driver, maximumTimeout);
		webelement = wait.until(ExpectedConditions.elementToBeClickable(by));
		log("*****Element " + by.toString() + " clickable on the page*****");
		return webelement;
	}

	/**
	 * Method is to set text in textbox field
	 *
	 */
	public void setText(WebElement element, String value) {
		System.out.println("vfdsfdsfg " + element.toString());
		element.clear();
		element.sendKeys(new CharSequence[] { value });
		log("*****Text: " + value + " entered in " + element.toString() + "*****");
	}

	public void setText(By by, String value) {
		this.setText(findObject(by), value);
	}

	/**
	 * Method is to find the object using by class and return webelement
	 *
	 */
	public WebElement findObject(By by) {
		WebElement element = null;
		WebDriverWait wait = new WebDriverWait(driver, maximumTimeout);
		element = (WebElement) wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		log("*****Element " + by.toString() + " found on the page*****");
		return element;
	}
	
	
	/**
	 * Method is to wait for the page to load
	 *
	 */
	public void waitForLoad(WebDriver driver) {
        ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
                    }
                };
        WebDriverWait wait = new WebDriverWait(driver, maximumTimeout);
        wait.until(pageLoadCondition);
    }
}
