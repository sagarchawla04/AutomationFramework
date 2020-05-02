package utilities;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import stepDefinitions.BaseClass;

public class ExtentReportManager implements ITestListener {

	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	public void onStart(ITestContext testContext)
	{
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time stamp
		String repName="Test-Report-"+timeStamp+".html";
				
		htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir")+ "/test-output/ExtentReports/"+repName);//specify location of the report
				
		htmlReporter.config().setDocumentTitle("My Test Automation Framework Report"); // Tile of report
		htmlReporter.config().setReportName("Automation Run Report"); // name of the report
		htmlReporter.config().setTheme(Theme.DARK);
		htmlReporter.setAppendExisting(true);
		
		extent=new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host name","localhost");
		extent.setSystemInfo("OS","Windows");
		extent.setSystemInfo("Browser","Chrome");
		extent.setSystemInfo("Environment","QA");
		extent.setSystemInfo("user","Sagar");
			
	}
	
	public void onTestSuccess(ITestResult result)
	{
		//test=extent.createTest(result.getName()); // create new entry in the report
		test=extent.createTest(BaseClass.scenario.getName()); // create new entry in the report
		test.log(Status.PASS, "Test Case PASSED IS " + BaseClass.scenario.getName());
		//test.log(Status.PASS, "Test Case PASSED IS " + result.getName());
	}
	
	public void onTestFailure(ITestResult result)
	{
//		test=extent.createTest(result.getName()); // create new entry in the report
//		test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getName()); // to add name in extent report
		
		test=extent.createTest(BaseClass.scenario.getName()); // create new entry in the report
		test.log(Status.FAIL, "TEST CASE FAILED IS " + BaseClass.scenario.getName()); // to add name in extent report
		
		test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getThrowable()); // to add error/exception in extent report

		//String screenshotPath=System.getProperty("user.dir")+"\\Screenshots\\"+result.getName()+".png";
		String screenshotPath=System.getProperty("user.dir")+"\\test-output\\Screenshots\\"+BaseClass.screenshotname + ".png";
		try {
			test.addScreenCaptureFromPath(screenshotPath);// adding screen shot
		} catch (IOException e) {
				e.printStackTrace();
		} 
	}
	
	public void onTestSkipped(ITestResult result)
	{
		test=extent.createTest(result.getName()); // create new entry in the report
		test.log(Status.SKIP, "Test Case SKIPPED IS " + result.getName());
	}
	
	public void onFinish(ITestContext testContext)
	{
		extent.flush();
	}

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}
}
