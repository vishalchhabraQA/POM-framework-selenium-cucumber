package automation.scratch_framework.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class QAClickLoginPage {

	
	WebDriver driver;
	
	public QAClickLoginPage(WebDriver driver) {
		this.driver=driver;
	}
	
	private By emailInput = By.id("user_email");
	private By pwdInput = By.id("user_password");
	private By loginBtn = By.name("commit");
	
	public WebElement getEmailInput() {
		return driver.findElement(emailInput);
	}
	
	public WebElement getPwdInput() {
		return driver.findElement(pwdInput);
	}
	
	public WebElement getLoginBtn() {
		return driver.findElement(loginBtn);
	}
}



