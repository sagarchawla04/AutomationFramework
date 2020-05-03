package stepDefinitions;

import java.io.IOException;
import org.openqa.selenium.By;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;
import utilities.JsonReader;
import utilities.PageActions;

public class Steps extends BaseClass {
    public PageActions pageActions = new PageActions();

    @Given("User Launch Chrome browser")
    public void user_Launch_Chrome_browser() throws IOException {
        //lp = new LoginPage(BaseClass.driver);
        lp = new LoginPage(driver);
    }

    @When("User opens URL {string}")
    public void user_opens_URL(String url) {
        lp.launchURL(url);
    }

    @When("User enters Email as {string} and Password as {string}")
    public void user_enters_Email_as_and_Password_as(String email, String password) {
        //String email1 = JsonReader.getValueByJPath("./src/test/java/Resources/test.json","/batters/batter[1]/email");
        //String password1 = JsonReader.getValueByJPath("./src/test/java/Resources/test.json","/batters/batter[1]/password");
        lp.setUserName(email);
        lp.setPassword(password);
    }

    @When("Click on Login")
    public void click_on_Login() {
        lp.clickLogin();
    }

    @Then("Page Title should be {string}")
    public void page_Title_should_be(String pagetitle) {
        lp.verifyPageTitle(pagetitle);
    }

    @When("User click on Log out link")
    public void user_click_on_Log_out_link() throws InterruptedException {
        lp.clickLogout();
        Thread.sleep(3000);
    }

    @Then("close browser")
    public void close_browser() {
        driver.quit();
    }

    // Customer feature step definitions

    @Then("User can view Dashboad")
    public void user_can_view_Dashboad() {
        addCust = new AddCustomerPage(driver);
        addCust.verifylogin();
        Assert.assertEquals( pageActions.getPageTitle(),"Dashboard / nopCommerce administration");
    }

    @When("User click on customers Menu")
    public void user_click_on_customers_Menu() {
        addCust.clickOnCustomersMenu();
    }

    @When("click on customers Menu Item")
    public void click_on_customers_Menu_Item() {
        addCust.clickOnCustomersMenuItem();
    }

    @When("click on Add new button")
    public void click_on_Add_new_button() {
        addCust.clickOnAddnew();
    }

    @Then("User can view Add new customer page")
    public void user_can_view_Add_new_customer_page() {
        Assert.assertEquals(pageActions.getPageTitle(),"Add a new customer / nopCommerce administration");
    }

    @When("User enter customer info")
    public void user_enter_customer_info() throws InterruptedException {
        String email = pageActions.generateRamdomString() + "@gmail.com";
        addCust.setEmail(email);
        addCust.setPassword("test123");
        // Registered - default
        // The customer cannot be in both 'Guests' and 'Registered' customer roles
        // Add the customer to 'Guests' or 'Registered' customer role
        addCust.setCustomerRoles("Guest");
        addCust.setManagerOfVendor("Vendor 2");
        addCust.setGender("Male");
        addCust.setFirstName("Pavan");
        addCust.setLastName("Kumar");
        addCust.setDob("7/05/1985"); // Format: D/MM/YYY
        addCust.setCompanyName("busyQA");
        addCust.setAdminContent("This is for testing.........");
    }

    @When("click on Save button")
    public void click_on_Save_button() {
        addCust.clickOnSave();
    }

    @Then("User can view confirmation message {string}")
    public void user_can_view_confirmation_message(String successmessage) {
        addCust.verifySuccessMessage(successmessage);
    }

    // Steps for searching a customer using email id

    @When("Enter customer EMail")
    public void enter_customer_EMail() {
        searchCust = new SearchCustomerPage(driver);
        searchCust.setEmail("victoria_victoria@nopCommerce.com");
    }

    @When("Click on search button")
    public void click_on_search_button() throws InterruptedException {
        searchCust.clickSearch();
    }

    @Then("User should found Email in the Search table")
    public void user_should_found_Email_in_the_Search_table() {
        boolean status = searchCust.searchCustomerByEmail("victoria_victoria@nopCommerce.com");
        Assert.assertTrue(status);
    }

    // Steps for searching a customer using name

    @When("Enter customer FirstName")
    public void enter_customer_FirstName() {
        searchCust = new SearchCustomerPage(driver);
        searchCust.setFirstName("Victoria");
    }

    @When("Enter customer LastName")
    public void enter_customer_LastName() {
        searchCust.setLastName("Terces");
    }

    @Then("User should found Name in the Search table")
    public void user_should_found_Name_in_the_Search_table() {
        Assert.assertTrue(searchCust.searchCustomerByName("Victoria Terces"));
    }

}
