package stepDefinitions;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import cucumber.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import utilities.DriverManager;
import utilities.PageActions;

public class Hooks extends BaseClass {

    // Hook- Will be executed once before every TC
    @Before
    public WebDriver setup(Scenario scenario) throws IOException {
        pageActions = new PageActions();
        driverManager = new DriverManager();
        driverManager.LoadProperties();
        driverManager.SetLoggerConfiguration();
        driver = driverManager.launchBrowser();
        BaseClass.scenario = scenario;
        return driver;
    }

    @After
    public void tearDown(Scenario scenario) {
        if(scenario.isFailed()) {
            logger.info("Scenario is failed : "+scenario.getName());
            pageActions.captureScreen(driver, pageActions.Screenshotname());
        }
        driver.quit();
    }

}
