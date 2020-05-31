package TestRunner;

//import org.junit.AfterClass;
//import org.junit.runner.RunWith;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import com.cucumber.listener.Reporter;
import com.cucumber.listener.ExtentCucumberFormatter;
//import cucumber.api.CucumberOptions;
//import cucumber.api.junit.Cucumber;

//import io.cucumber.junit.CucumberOptions;
//import io.cucumber.junit.Cucumber;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;


//@RunWith(Cucumber.class)
@CucumberOptions(
        //features = ".//Features/Login.feature",
        //features = {".//Features/Login.feature",".//Features/Customers.feature"},
        features = ".//Features/", // This will run all feature files
        glue = "stepDefinitions", //the path of the step definition files
        dryRun = false,
        monochrome = true,//display the console output in a proper readable format
        //plugin = { "com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html"},
        plugin = {"pretty", "html:test-output"},
        tags = {"@sanity"}
        //tags= {"@sanity","@regression"} This represents AND operation between both tags. All test cases having both tags at same time will be executed
        //tags= {"@sanity, @regression"} This represents OR operation between both tags. All test cases with either of tags will be executed
)
public class TestRun extends AbstractTestNGCucumberTests {

    /* // For parallel execution of test cases
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
     */
}