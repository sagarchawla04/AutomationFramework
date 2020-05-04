package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.en.And;
import org.openqa.selenium.By;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;
import utilities.Encryption;
import utilities.JsonReader;
import utilities.PageActions;

public class Steps extends BaseClass {
    public PageActions pageActions = new PageActions();
    public String jsonPath = null;

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
        lp.setUserName(email);
        lp.setPassword(Encryption.decryptPassword(password));
    }

    @And("user enters the login credentials from {string} file")
    public void userEntersTheLoginCredentialsFromFile(String filename) {
        jsonPath = "./src/test/java/Resources/" + filename;
        lp.setUserName(JsonReader.getValueByJPath(jsonPath, "/validLoginUsername"));
        lp.setPassword(Encryption.decryptPassword(JsonReader.getValueByJPath(jsonPath, "/validLoginPassword")));
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
        jsonPath = "./src/test/java/Resources/test.json";
        addCust = new AddCustomerPage(driver);
        addCust.verifylogin();
        Assert.assertEquals(pageActions.getPageTitle(), JsonReader.getValueByJPath(jsonPath, "/expectedTitleAfterLogin"));
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
        jsonPath = "./src/test/java/Resources/test.json";
        Assert.assertEquals(pageActions.getPageTitle(), JsonReader.getValueByJPath(jsonPath, "/expectedTitleOfAddCustomerPage"));
    }

    @When("User enter customer info")
    public void user_enter_customer_info() throws InterruptedException {
        jsonPath = "./src/test/java/Resources/test.json";
        String email = pageActions.generateRamdomString() + "@gmail.com";
        addCust.setEmail(email);
        addCust.setPassword(JsonReader.getValueByJPath(jsonPath, "/addCustomer/customerInfo[0]/password"));
        addCust.setCustomerRoles(JsonReader.getValueByJPath(jsonPath, "/addCustomer/customerInfo[0]/role"));
        addCust.setManagerOfVendor(JsonReader.getValueByJPath(jsonPath, "/addCustomer/customerInfo[0]/vendorManager"));
        addCust.setGender(JsonReader.getValueByJPath(jsonPath, "/addCustomer/customerInfo[0]/gender"));
        addCust.setFirstName(JsonReader.getValueByJPath(jsonPath, "/addCustomer/customerInfo[0]/firstName"));
        addCust.setLastName(JsonReader.getValueByJPath(jsonPath, "/addCustomer/customerInfo[0]/lastName"));
        addCust.setDob(JsonReader.getValueByJPath(jsonPath, "/addCustomer/customerInfo[0]/dob")); // Format: D/MM/YYY
        addCust.setCompanyName(JsonReader.getValueByJPath(jsonPath, "/addCustomer/customerInfo[0]/companyName"));
        addCust.setAdminContent(JsonReader.getValueByJPath(jsonPath, "/addCustomer/customerInfo[0]/adminContent"));
    }

    @When("click on Save button")
    public void click_on_Save_button() {
        addCust.clickOnSave();
    }

    @Then("User can view confirmation message")
    public void user_can_view_confirmation_message() {
        jsonPath = "./src/test/java/Resources/test.json";
        addCust.verifySuccessMessage(JsonReader.getValueByJPath(jsonPath, "/addCustomerSuccessMessage"));
    }

    // Steps for searching a customer using email id

    @When("Enter customer EMail")
    public void enter_customer_EMail() {
        jsonPath = "./src/test/java/Resources/test.json";
        searchCust = new SearchCustomerPage(driver);
        searchCust.setEmail(JsonReader.getValueByJPath(jsonPath, "/searchCustomer[0]/email"));
    }

    @When("Click on search button")
    public void click_on_search_button() throws InterruptedException {
        searchCust.clickSearch();
    }

    @Then("User should found Email in the Search table")
    public void user_should_found_Email_in_the_Search_table() {
        jsonPath = "./src/test/java/Resources/test.json";
        boolean status = searchCust.searchCustomerByEmail(JsonReader.getValueByJPath(jsonPath, "/searchCustomer[0]/email"));
        Assert.assertTrue(status);
    }

    // Steps for searching a customer using name

    @When("Enter customer FirstName")
    public void enter_customer_FirstName() {
        jsonPath = "./src/test/java/Resources/test.json";
        searchCust = new SearchCustomerPage(driver);
        searchCust.setFirstName(JsonReader.getValueByJPath(jsonPath, "/searchCustomer[1]/firstName"));
    }

    @When("Enter customer LastName")
    public void enter_customer_LastName() {
        jsonPath = "./src/test/java/Resources/test.json";
        searchCust.setLastName(JsonReader.getValueByJPath(jsonPath, "/searchCustomer[1]/lastName"));
    }

    @Then("User should found Name in the Search table")
    public void user_should_found_Name_in_the_Search_table() {
        jsonPath = "./src/test/java/Resources/test.json";
        Assert.assertTrue(searchCust.searchCustomerByName(JsonReader.getValueByJPath(jsonPath, "/searchCustomer[1]/firstName") + " " + JsonReader.getValueByJPath(jsonPath, "/searchCustomer[1]/lastName")));
    }
}
