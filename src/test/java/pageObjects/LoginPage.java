package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import junit.framework.Assert;
import stepDefinitions.BaseClass;
import utilities.PageActions;
import utilities.WaitHelper;

public class LoginPage {

	private WebDriver ldriver;
	WaitHelper waitHelper;
	PageActions pageActions;

	public LoginPage(WebDriver rdriver) {
		ldriver = rdriver;
		// PageFactory.initElements(ldriver, this);
		waitHelper = new WaitHelper(ldriver);
		pageActions = new PageActions(ldriver);
	}

	By txtEmail1 = By.xpath("//input[@class='email']");
	By txtPassword1 = By.id("Password");
	By btnLogin1 = By.xpath("//input[@value='Log in']");
	By lnkLogout1 = By.cssSelector("a[href*='logout']");

	// @FindBy(xpath = "//input[@class='email']")
	// @CacheLookup
	// public WebElement txtEmail;

	public void setUserName(String uname) {
		pageActions.setText(txtEmail1, uname);
	}

	public void setPassword(String pwd) {
		pageActions.setText(txtPassword1, pwd);
	}

	public void clickLogin() throws InterruptedException {
		pageActions.click(btnLogin1);
		pageActions.waitForLoad(ldriver);
	}

	public void clickLogout() {
		pageActions.clickByJavaScript(lnkLogout1);
	}

	public void launchURL(String url) {
		pageActions.launchUrl(url);
	}
	
	public void verifyPageTitle(String expectedtitle) {
		Assert.assertEquals(expectedtitle, pageActions.getPageTitle());
	}

}
