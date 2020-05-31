package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import utilities.PageActions;

public class AddCustomerPage {

    public WebDriver ldriver;
    public PageActions pageActions;

    public AddCustomerPage(WebDriver rdriver) {
        ldriver = rdriver;
        pageActions = new PageActions();
    }

    By lnkCustomers_menu = By.xpath("//a[@href='#']//span[contains(text(),'Customers')]");
    By lnkCustomers_menuitem = By.xpath("//span[@class='menu-item-title'][contains(text(),'Customers')]");
    By btnAddnew = By.xpath("//a[@class='btn bg-blue']"); // Add new
    By txtEmail = By.xpath("//input[@id='Email']");
    By txtPassword = By.xpath("//input[@id='Password']");
    By txtcustomerRoles = By.xpath("//label[text()='Customer roles']//following::div[@class='k-multiselect-wrap k-floatwrap']");
    By drpmgrOfVendor = By.xpath("//*[@id='VendorId']");
    By rdMaleGender = By.id("Gender_Male");
    By rdFeMaleGender = By.id("Gender_Female");
    By txtFirstName = By.xpath("//input[@id='FirstName']");
    By txtLastName = By.xpath("//input[@id='LastName']");
    By txtDob = By.xpath("//input[@id='DateOfBirth']");
    By txtCompanyName = By.xpath("//input[@id='Company']");
    By txtAdminContent = By.xpath("//textarea[@id='AdminComment']");
    By btnSave = By.xpath("//button[@name='save']");
    By removeRole = By.xpath("//*[@id=\"SelectedCustomerRoleIds_taglist\"]/li/span[2]");
    By message = By.tagName("body");

    // Action Methods

    public void clickOnCustomersMenu() {
        pageActions.clickByJavaScript(lnkCustomers_menu);
    }

    public void verifylogin() {
        pageActions.findObject(lnkCustomers_menu);
    }

    public void clickOnCustomersMenuItem() {
        pageActions.click(lnkCustomers_menuitem);
    }

    public void clickOnAddnew() {
        pageActions.click(btnAddnew);
        pageActions.waitForPageLoad(ldriver);
    }

    public void setEmail(String email) {
        pageActions.setText(txtEmail, email);
    }

    public void setPassword(String password) {
        pageActions.setText(txtPassword, password);
    }

    public void setCustomerRoles(String role) throws InterruptedException {
        if (!role.equals("Vendors")) // If role is vendors should not delete Register as per req.
            pageActions.click(removeRole);
        pageActions.click(txtcustomerRoles);
        By specificRole = By.xpath("//li[contains(text(),'" + role + "')]");
        pageActions.clickByJavaScript(specificRole);
    }

    public void setManagerOfVendor(String value) {
        pageActions.selectValueFromDropDownByText(drpmgrOfVendor, value);
    }

    public void setGender(String gender) {
        if (gender.equals("Female")) {
            pageActions.click(rdFeMaleGender);
        } else {
            pageActions.click(rdMaleGender);
        }
    }

    public void setFirstName(String fname) {
        pageActions.setText(txtFirstName, fname);
    }

    public void setLastName(String lname) {
        pageActions.setText(txtLastName, lname);
    }

    public void setDob(String dob) {
        pageActions.setText(txtDob, dob);
    }

    public void setCompanyName(String comname) {
        pageActions.setText(txtCompanyName, comname);
    }

    public void setAdminContent(String content) {
        pageActions.setText(txtAdminContent, content);
    }

    public void clickOnSave() {
        pageActions.click(btnSave);
        pageActions.waitForPageLoad(ldriver);
    }

    public void verifySuccessMessage(String successMessage) {
        Assert.assertTrue(ldriver.findElement(message).getText().contains(successMessage));
    }
}
