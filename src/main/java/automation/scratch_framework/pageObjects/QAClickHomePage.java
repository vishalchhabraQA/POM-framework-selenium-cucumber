package automation.scratch_framework.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class QAClickHomePage {

	/* Coding standards: Achieving Encapsulation: private locators/variables and public methods, so that child class cannot access it and it will be hidden */
	
	/* OOPs Encapsulation Implementation and Recommended: public methods and private variables */ 
	
	WebDriver driver;
	
	public QAClickHomePage(WebDriver driver) {
		this.driver=driver;
	}
	
	/* accessible only in this class only as private and it is recommended */
	private By loginLink = By.xpath("//a[contains(@href,'/users/sign_in')]");
	private By text = By.xpath("//div[@class='text-center']/h2");
	private By navBar = By.xpath("//ul[@class='nav navbar-nav navbar-right']");
	
	public QAClickLoginPage getLoginLink() {
		driver.findElement(loginLink).click();

		QAClickLoginPage lp = new QAClickLoginPage(driver); //because it is a special object which is oving to another page
		return lp; //to avoid another/multiple page object creation in the test
	}
	
	public WebElement getLabel() {
		return driver.findElement(text);
	}
	
	public WebElement getnavBar() {
		return driver.findElement(navBar);
	}
	
}
