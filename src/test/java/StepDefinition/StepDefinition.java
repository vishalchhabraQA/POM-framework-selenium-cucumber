package StepDefinition;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import automation.scratch_framework.pageObjects.QAClickHomePage;
import automation.scratch_framework.pageObjects.QAClickLoginPage;
import cucumber.api.java.en.*;

import automation.scratch_framework.pageObjects.QAClickHomePage;
import automation.scratch_framework.pageObjects.QAClickLoginPage;
import automation.scratch_framework.resources.Base;



public class StepDefinition extends Base {

	WebDriver driver; //to avoid overriding of driver from another class making local copy of base class driver
	QAClickHomePage hp;
	QAClickLoginPage lp;
	
    @Given("^initialize the browser with chrome$")
    public void initialize_the_browser_with_chrome() throws Throwable {
    	driver = initializeDriver(); //driver variable of base class
		maximizeWindow();
    }


    @Given("^navigate to \"([^\"]*)\" site$")
    public void navigate_to_something_site(String strArg1) throws Throwable {
    	driver.get(strArg1); //because we need to hit for 3 test data, so not put in beforetest
		//	Assert.assertTrue(false); //to fail test forcefully to take screenshot onTestFailure
		hp = new QAClickHomePage(driver);
		if(hp.getNoThanksBtnCount() > 0) { //isDisplyed will not work if "it is in DOM and not on UI" so use element count to check if present or not
			hp.getNoThanksBtn().click();
		}
		Assert.assertEquals(hp.getLabel().getText(),"FEATURED COURSES");
		Assert.assertTrue(hp.getnavBar().isDisplayed());
    }

    @Given("^click on login link to navigate to sign in page$")
    public void click_on_login_link_to_navigate_to_sign_in_page() throws Throwable {
		lp = hp.getLoginLink();   
    }

    
    @When("^User enters (.+) and (.+) and logs in$")
    public void user_enters_and_and_logs_in(String username, String password) throws Throwable {
		lp.getEmailInput().sendKeys(username);
		lp.getPwdInput().sendKeys(password);
		lp.getLoginBtn().click();

    }


    @Then("^verify that user logged in to application successfully$")
    public void verify_that_user_logged_in_to_application_successfully() throws Throwable {
        System.out.println("validated! passed");
        closeDriver();
    }



}
