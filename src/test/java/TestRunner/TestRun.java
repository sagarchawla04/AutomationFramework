package TestRunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		//features = ".//Features/Login.feature",
		//features = {".//Features/Login.feature",".//Features/Customers.feature"}, 
		features=".//Features/", // This will run all feature files
		glue = "stepDefinitions", //the path of the step definition files
		dryRun = false, 
		monochrome = true,  //display the console output in a proper readable format
		plugin = { "pretty", "html:test-output" },
		tags= {"@sanity"}
		//tags= {"@sanity","@regression"} This represents AND operation between both tags. All test cases having both tags at same time will be executed
		//tags= {"@sanity, @regression"} This represents OR operation between both tags. All test cases with either of tags will be executed
		)
public class TestRun {

}