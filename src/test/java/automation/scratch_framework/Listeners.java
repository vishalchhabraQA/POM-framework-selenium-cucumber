package automation.scratch_framework;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import automation.scratch_framework.resources.Base;
import automation.scratch_framework.resources.ExtentReporterNg;


public class Listeners extends Base implements ITestListener { //implementing an interface

/* To make tests thread safe and cannot be overridden by another tests we use ThreadLocal class, so that we can run over test in parallel mode as well and avoid overriding of test vaiables with another test when running parralllely to make it thread safe execution in parrallel */	

	ExtentReports er = ExtentReporterNg.getReportObject();
	ExtentTest test;
	
	//Thread safe concept to run tests in parallel
	ThreadLocal<ExtentTest> tl = new ThreadLocal<ExtentTest>();
	
	@Override
	public void onTestStart(ITestResult result) {

		test = er.createTest(result.getMethod().getMethodName()); //now extent report will continuously monitor this test and generates data.
		
		tl.set(test); //to create thread local pool and avoid overriden of tests and point exact test name in each listener in parralel mode
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		tl.get().log(Status.PASS, "test passed!!!!!!!!!");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		String testName = result.getMethod().getMethodName();
		/* get the driver variable from test, to give the driver life */

		WebDriver driver = null;

		tl.get().fail(result.getThrowable());
		
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			String sspath = (String) getScreenshot(testName, driver);
			tl.get().addScreenCaptureFromPath(sspath, testName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
	}

	@Override
	public void onStart(ITestContext context) {
	}

	@Override
	public void onFinish(ITestContext context) {
		er.flush();
	}

}
