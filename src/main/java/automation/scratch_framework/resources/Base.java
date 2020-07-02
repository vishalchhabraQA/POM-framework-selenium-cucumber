package automation.scratch_framework.resources;

import java.io.File;
import org.apache.commons.io.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Base {

	//headless browser: browser will not open but still all tests will run with chrome binary, is is fast. 
	
	public WebDriver driver; //so that driver access is everywhere, defined at global level
	Properties p;
	String projectPath = System.getProperty("user.dir");
	
	public WebDriver initializeDriver() throws IOException {
		//it will be used by all the test cases
		//take argument as browser type and specific browser will be opened
	
		p = new Properties();
		
		FileInputStream fis = new FileInputStream(projectPath + "/src/main/java/automation/scratch_framework/resources/data.properties");
		p.load(fis);
		
//		String browserName = System.getProperty("browserName"); //get property from maven command
		String browserName = p.getProperty("browserName"); //get property from .properties file
		
		if(browserName.contains("chrome")) { //always use equalsIgnoreCase when comparing .properties file value
			DesiredCapabilities ch=DesiredCapabilities.chrome();
			ch.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
			ch.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

			
			ChromeOptions co = new ChromeOptions();
			co.merge(ch);
			if(browserName.contains("headless")) {
				co.addArguments("--headless");
			}
			System.setProperty("webdriver.chrome.driver", projectPath + "/src/main/java/automation/scratch_framework/resources/browser-drivers/chromedriver");
			
			driver = new ChromeDriver(co);
		}
		else if(browserName.contains("firefox")){
			FirefoxOptions fo = new FirefoxOptions();
			if(browserName.contains("headless")) {
				fo.addArguments("--headless");
			}
			System.setProperty("webdriver.gecko.driver", projectPath + "/src/main/java/automation/scratch_framework/resources/browser-drivers/geckodriver");
			driver = new FirefoxDriver(fo);	
		}
	
		driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS); //applicable to all the test cases
		return driver;
	}
	
	public void closeDriver() {
		driver.close();
	}
	
	public String getURL() throws IOException {
		
		String url = p.getProperty("url");
		
		return url;
	}
	
	public void maximizeWindow() {
		driver.manage().window().maximize();
	}
	
	public String getScreenshot(String testName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		String path = projectPath + "/reports/screenshots/"+testName+".png";
		FileUtils.copyFile(src, new File(path));
		return path;
	}
}
