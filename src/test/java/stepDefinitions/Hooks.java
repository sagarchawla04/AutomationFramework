package stepDefinitions;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import cucumber.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import utilities.PageActions;

public class Hooks extends BaseClass {
	
	// Hook- Will be executed once before every TC
			@Before
			public WebDriver setup(Scenario scenario) throws IOException {
				pageActions = new PageActions();
				pageActions.LoadProperties();
				pageActions.SetLoggerConfiguration();
				driver = pageActions.launchBrowser();
				BaseClass.scenario = scenario;
				return driver;
			}
			
			@After
			public void tearDown() {
				driver.quit();
			}

}
