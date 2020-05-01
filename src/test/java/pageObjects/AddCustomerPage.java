package pageObjects;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import utilities.PageActions;

public class AddCustomerPage {

    public WebDriver ldriver;
    public PageActions pageActions;

    public AddCustomerPage(WebDriver rdriver) {
        ldriver = rdriver;
        PageFactory.initElements(ldriver, this);
        //pageActions = new PageActions(ldriver);
        pageActions = new PageActions();
    }

    By lnkCustomers_menu = By.xpath("//a[@href='#']//span[contains(text(),'Customers')]");
    By lnkCustomers_menuitem = By.xpath("//span[@class='menu-item-title'][contains(text(),'Customers')]");

    By btnAddnew = By.xpath("//a[@class='btn bg-blue']"); // Add new

    By txtEmail = By.xpath("//input[@id='Email']");
    By txtPassword = By.xpath("//input[@id='Password']");

    By txtcustomerRoles = By.xpath("//div[@class='k-multiselect-wrap k-floatwrap']");

    By lstitemAdministrators = By.xpath("//li[contains(text(),'Administrators')]");
    By lstitemRegistered = By.xpath("//li[contains(text(),'Registered')]");
    By lstitemGuests = By.xpath("//li[contains(text(),'Guests')]");
    By lstitemVendors = By.xpath("//li[contains(text(),'Vendors')]");

    By drpmgrOfVendor = By.xpath("//*[@id='VendorId']");
    By rdMaleGender = By.id("Gender_Male");
    By rdFeMaleGender = By.id("Gender_Female");

    By txtFirstName = By.xpath("//input[@id='FirstName']");
    By txtLastName = By.xpath("//input[@id='LastName']");

    By txtDob = By.xpath("//input[@id='DateOfBirth']");

    By txtCompanyName = By.xpath("//input[@id='Company']");

    By txtAdminContent = By.xpath("//textarea[@id='AdminComment']");

    By btnSave = By.xpath("//button[@name='save']");

    // Action Methods

    public String getPageTitle() {
        return ldriver.getTitle();
    }

    public void clickOnCustomersMenu() {
        pageActions.clickByJavaScript(lnkCustomers_menu);
        //ldriver.findElement(lnkCustomers_menu).click();
    }

    public void verifylogin() {
        pageActions.findObject(lnkCustomers_menu);
    }

    public void clickOnCustomersMenuItem() {
        pageActions.click(lnkCustomers_menuitem);
        //ldriver.findElement(lnkCustomers_menuitem).click();
    }

    public void clickOnAddnew() {
        pageActions.click(btnAddnew);
        //ldriver.findElement(btnAddnew).click();
    }

    public void setEmail(String email) {
        pageActions.setText(txtEmail, email);
        //ldriver.findElement(txtEmail).sendKeys(email);
    }

    public void setPassword(String password) {
        pageActions.setText(txtPassword, password);
        //ldriver.findElement(txtPassword).sendKeys(password);
    }

    public void setCustomerRoles(String role) throws InterruptedException {
        if (!role.equals("Vendors")) // If role is vendors should not delete Register as per req.
        {
            pageActions.click(By.xpath("//*[@id=\"SelectedCustomerRoleIds_taglist\"]/li/span[2]"));
            //ldriver.findElement(By.xpath("//*[@id=\"SelectedCustomerRoleIds_taglist\"]/li/span[2]")).click();
        }

        pageActions.click(txtcustomerRoles);
        //ldriver.findElement(txtcustomerRoles).click();

        WebElement listitem;

        Thread.sleep(3000);

        if (role.equals("Administrators")) {
            listitem = ldriver.findElement(lstitemAdministrators);
        } else if (role.equals("Guests")) {
            listitem = ldriver.findElement(lstitemGuests);
        } else if (role.equals("Registered")) {
            listitem = ldriver.findElement(lstitemRegistered);
        } else if (role.equals("Vendors")) {
            listitem = ldriver.findElement(lstitemVendors);
        } else {
            listitem = ldriver.findElement(lstitemGuests);
        }

        // listitem.click();
        // Thread.sleep(3000);

        JavascriptExecutor js = (JavascriptExecutor) ldriver;
        js.executeScript("arguments[0].click();", listitem);

    }

    public void setManagerOfVendor(String value) {
        Select drp = new Select(ldriver.findElement(drpmgrOfVendor));
        drp.selectByVisibleText(value);
    }

    public void setGender(String gender) {
        if (gender.equals("Male")) {
            pageActions.click(rdMaleGender);
            //ldriver.findElement(rdMaleGender).click();
        } else if (gender.equals("Female")) {
            pageActions.click(rdFeMaleGender);
            //ldriver.findElement(rdFeMaleGender).click();
        } else {
            pageActions.click(rdMaleGender);
            //ldriver.findElement(rdMaleGender).click();// Default
        }

    }

    public void setFirstName(String fname) {
        pageActions.setText(txtFirstName, fname);
        //	ldriver.findElement(txtFirstName).sendKeys(fname);
    }

    public void setLastName(String lname) {
        pageActions.setText(txtLastName, lname);
        //	ldriver.findElement(txtLastName).sendKeys(lname);
    }

    public void setDob(String dob) {
        pageActions.setText(txtDob, dob);
        //ldriver.findElement(txtDob).sendKeys(dob);
    }

    public void setCompanyName(String comname) {
        pageActions.setText(txtCompanyName, comname);
        //ldriver.findElement(txtCompanyName).sendKeys(comname);
    }

    public void setAdminContent(String content) {
        pageActions.setText(txtAdminContent, content);
        //ldriver.findElement(txtAdminContent).sendKeys(content);
    }

    public void clickOnSave() {
        pageActions.click(btnSave);
        //ldriver.findElement(btnSave).click();
    }

}
