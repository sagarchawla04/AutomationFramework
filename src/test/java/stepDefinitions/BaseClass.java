package stepDefinitions;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;

import cucumber.api.Scenario;
import io.cucumber.java.Before;
import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;
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
	public static Scenario scenario;
	public static String screenshotname;
	

	// Created for generating random string for unique email
	public static String ramdomString() {
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return (generatedString);
	}
	
	
}
