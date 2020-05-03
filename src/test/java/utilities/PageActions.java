package utilities;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
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
    }

    /**
     * Method is to launch the url
     */
    public void launchUrl(String url) {
        try {
            driver.get(url);
            logger.info("*****Launched url : " + url + "*****");
        } catch (TimeoutException e) {
            Assert.fail("Timed out !!! Application didn't load. Aborting !!!");
        }
    }

    /**
     * Method is to get the current url
     */
    public String getCurrentUrl() {
        logger.info("*****Current page URL : " + driver.getCurrentUrl() + "*****");
        return driver.getCurrentUrl();
    }

    /**
     * Method is to get the page title
     */
    public String getPageTitle() {
        logger.info("*****The Title of the page : " + driver.getTitle() + "*****");
        return driver.getTitle();
    }

    /**
     * Method is to click on the element
     */
    public void click(WebElement element) {
        try {
            element.click();
            logger.info("*****Element clicked : " + element.toString() + "*****");
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
            logger.info("*****Element clicked using javascript executor : " + element.toString() + "*****");
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
            logger.info("*****Element " + by.toString() + " clickable on the page*****");
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
            logger.info("*****Text: " + value + " entered in " + element.toString() + "*****");
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
            logger.info("*****Element " + by.toString() + " found on the page*****");
        } catch (Exception e) {
            logger.info("*****Element " + by.toString() + " not found on the page*****");
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
        logger.info("*****Page loaded successfully*****");
    }

    /**
     * Method is to capture the screenshot
     */
    public void captureScreen(WebDriver driver, String tname) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            File target = new File(System.getProperty("user.dir") + "\\test-output\\Screenshots\\" + tname + ".png");
            FileUtils.copyFile(source, target);
            logger.info("*****Screenshot captured successfully*****");
        } catch (Exception e) {
            Assert.fail("Test failed with exception ---> " + e.toString());
        }
    }

    /**
     * Method is to wait for specific seconds using thread.sleep
     */
    public void waitForSeconds(int d) {
        try {
            Thread.sleep((long) (d * 1000));
            logger.info("*****Hard wait for " + d + "seconds*****");
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
        logger.info("***** Key " + key + " hit from the keyboard*****");
    }

    /**
     * Method is to select the dropdown using value
     */
    public void selectValueFromDropDownByValue(By by, String value) {
        try {
            Select select = new Select(findObject(by));
            select.selectByValue(value);
            logger.info("***** Dropdown value " + value + " selected*****");
        }catch (Exception e){
            Assert.fail("Test failed with exception ---> " + e.toString());
        }
    }

    /**
     * Method is to select the dropdown using text
     */
    public void selectValueFromDropDownByText(By by, String value) {
        try {
            Select select = new Select(findObject(by));
            select.selectByVisibleText(value);
            logger.info("***** Dropdown value " + value + " selected*****");
        }catch (Exception e){
            Assert.fail("Test failed with exception ---> " + e.toString());
        }
    }
}
