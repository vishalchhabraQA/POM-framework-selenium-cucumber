package TestRunnerPackage;


import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;


//@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/java/Features", //run all feature files
		glue = "StepDefinition" //define step definition file
		)
public class TestRunner extends AbstractTestNGCucumberTests {

}
