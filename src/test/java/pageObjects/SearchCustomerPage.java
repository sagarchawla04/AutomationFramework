package pageObjects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import stepDefinitions.BaseClass;
import utilities.PageActions;

public class SearchCustomerPage {
    public WebDriver ldriver;
    PageActions pageActions;

    public SearchCustomerPage(WebDriver rdriver) {
        ldriver = rdriver;
        //PageFactory.initElements(ldriver, this);
        pageActions = new PageActions();
    }

    By txtEmail = By.id("SearchEmail");
    By txtFirstName = By.id("SearchFirstName");
    By txtLastName = By.id("SearchLastName");
    By btnSearch = By.id("search-customers");
    By table = By.xpath("//table[@id='customers-grid']");
    By tableRows = By.xpath("//table[@id='customers-grid']//tbody/tr");
    By tableColumns = By.xpath("//table[@id='customers-grid']//tbody/tr/td");

//	@FindBy(how = How.ID, using = "SearchEmail")
//	@CacheLookup
//	WebElement txtEmail;

    public void setEmail(String email) {
        pageActions.setText(txtEmail, email);
    }

    public void clickSearch() throws InterruptedException {
        pageActions.click(btnSearch);
        pageActions.waitForPageLoad(ldriver);
        pageActions.waitForSeconds(2);
    }

    public void setFirstName(String fname) {
        pageActions.setText(txtFirstName, fname);
    }

    public void setLastName(String lname) {
        pageActions.setText(txtLastName, lname);
    }

    public int getNoOfRows() {
        return ldriver.findElements(tableRows).size();
    }

    public int getNoOfColumns() {
        return ldriver.findElements(tableColumns).size();
    }

    public boolean searchCustomerByEmail(String email) {
        pageActions.findObject(table);
        boolean flag = false;
        for (int i = 1; i <= getNoOfRows(); i++) {
            String emailid = ldriver.findElement(table).findElement(By.xpath("//table[@id='customers-grid']/tbody/tr[" + i + "]/td[2]")).getText();
            if (emailid.equals(email)) {
                flag = true;
                BaseClass.logger.info("***** Email id: " + email + "came in search results");
                break;
            }
        }
        return flag;
    }

    public boolean searchCustomerByName(String Name) {
        pageActions.findObject(table);
        boolean flag = false;
        for (int i = 1; i <= getNoOfRows(); i++) {
            String name = ldriver.findElement(table).findElement(By.xpath("//table[@id='customers-grid']/tbody/tr[" + i + "]/td[3]"))
                    .getText();
            String names[] = name.split(" ");// Separating fname and lname
            String expectednames[] = Name.split(" ");
            if (names[0].equals(expectednames[0]) && names[1].equals(expectednames[1])) {
                flag = true;
                BaseClass.logger.info("***** Name : " + Name + "came in search results");
                break;
            }
        }
        return flag;
    }
}
