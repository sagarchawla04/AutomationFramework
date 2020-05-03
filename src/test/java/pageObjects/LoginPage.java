package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import utilities.PageActions;

public class LoginPage {

    private WebDriver ldriver;
    PageActions pageActions;

    public LoginPage(WebDriver rdriver) {
        ldriver = rdriver;
        // PageFactory.initElements(ldriver, this);
        // pageActions = new PageActions(ldriver);
        pageActions = new PageActions();
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

    public void clickLogin() {
        pageActions.click(btnLogin1);
        pageActions.waitForPageLoad(ldriver);
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
