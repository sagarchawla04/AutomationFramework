package stepDefinitions;

import java.util.Properties;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import cucumber.api.Scenario;
import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;
import utilities.DriverManager;
import utilities.PageActions;

public class BaseClass {

    public static WebDriver driver;
    public LoginPage lp;
    public AddCustomerPage addCust;
    public SearchCustomerPage searchCust;
    public static Logger logger;
    public Properties configProp;
    public long maximumTimeout = 40;
    public PageActions pageActions;
    public DriverManager driverManager;
    public static Scenario scenario;
    public static String screenshotname;

}
