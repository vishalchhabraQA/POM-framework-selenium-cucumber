package automation.scratch_framework.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNg {

	

	static ExtentReports er; //defined globally, so that other methods can use its methods and properties
	
	
	public  static ExtentReports getReportObject() {
		
		//gives project path, no matter on which system u are working
				String path = System.getProperty("user.dir")+"/reports/extent-reports/index.html"; //so index.html is the file which will have extent reports
				
				ExtentSparkReporter esr = new ExtentSparkReporter(path); //responsible for creating report
				//esr object is responsible for doing all extent reports configuration
				esr.config().setReportName("extent-report-web-automation-report");
				esr.config().setDocumentTitle("extent-report-title");
				
				er = new ExtentReports(); //responsible for driving all your reporting execution
				er.attachReporter(esr); 
				er.setSystemInfo("Tester", "Vishal Chhabra");
				return er;
	}
	
	
}
