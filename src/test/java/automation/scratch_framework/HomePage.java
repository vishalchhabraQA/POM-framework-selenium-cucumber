package automation.scratch_framework;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import automation.scratch_framework.pageObjects.QAClickHomePage;
import automation.scratch_framework.pageObjects.QAClickLoginPage;
import automation.scratch_framework.resources.Base;

public class HomePage extends Base { //Inheriting from base class

	public WebDriver driver; //to avoid overriding of driver from another class making local copy of base class driver
	
	//log4j : must be present for all test below line
	public static Logger log = LogManager.getLogger(Base.class.getName());
	
	
	
	@BeforeTest
	public void beforeTest() throws IOException {
		driver = initializeDriver(); //driver variable of base class
		log.info("driver is initialized!");
		maximizeWindow();
		log.info("window is maximized!");
		
	}
	
	
	@Test(dataProvider="getData")
	public void basePageNavigation(String uName, String pwd) throws IOException {
		
		driver.get(getURL()); //because we need to hit for 3 test data, so not put in beforetest
		log.info("navigated to "+ getURL());

		System.out.println( uName + " " + pwd );
		//	Assert.assertTrue(false); //to fail test forcefully to take screenshot onTestFailure
		QAClickHomePage hp = new QAClickHomePage(driver);

		Assert.assertEquals(hp.getLabel().getText(),"FEATURED COURSES");
		log.info("SuccessFully validated test message!");
		
		Assert.assertTrue(hp.getnavBar().isDisplayed());
		log.info("Navigation bar is present!");
		QAClickLoginPage lp = hp.getLoginLink();
		
		lp.getEmailInput().sendKeys(uName);
		lp.getPwdInput().sendKeys(pwd);
		lp.getLoginBtn().click();
		
	}

	@DataProvider
	public Object[][] getData() {
		
		Object[][] data = new Object[3][2];
		data[0][0]="ab";
		data[0][1]="1234567890";
		data[1][0]="abc";
		data[1][1]="123";
		data[2][0]="abcd";
		data[2][1]="890";
		
		return data;
		
	}
	
	@AfterTest
	public void afterTest() {
		closeDriver();
	}
}
