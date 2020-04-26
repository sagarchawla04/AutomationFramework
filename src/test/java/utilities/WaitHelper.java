package utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitHelper {
	public WebDriver driver;

	public WaitHelper(WebDriver driver) {
		this.driver = driver;
	}

	public void WaitForElementToBeVisible(WebElement element, long timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void clickbyjavascript(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		WaitForElementToBeVisible(element, 40);
		 js.executeScript("arguments[0].click();", element);
	}

}
