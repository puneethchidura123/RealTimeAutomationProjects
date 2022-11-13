package PuneethChidura.ExtentReports;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ExtentSparkReporterConfig;

public class ExtentReportsDemo {

	
	ExtentReports extentreport;
	
	@BeforeTest
	public void config()
	{
		
		String PathForExtentReports = System.getProperty("User.dir")+"\\Reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(PathForExtentReports);
		ExtentSparkReporterConfig reporterconfigurator = reporter.config();
		reporterconfigurator.setDocumentTitle("Automation Test results");
		
		
		extentreport = new ExtentReports();
		extentreport.attachReporter(reporter);
		extentreport.setSystemInfo("Tester", "Puneeth Chidura");
		
		
		
	}
	
@Test
public void initialDemo()
{
    ExtentTest test = 	extentreport.createTest("First test");
	System.setProperty("webdriver.chrome.driver", "D:\\Real Time Automation Projects\\ExtentReports\\BrowserDrivers\\chromedriver.exe");
	ChromeDriver driver = new ChromeDriver();
	driver.get("https://rahulshettyacademy.com/");
	 String title =   driver.getTitle();
	 System.out.println(title);
	 test.fail("results doesn't match");
	 driver.close();
	 extentreport.flush();
}

}
