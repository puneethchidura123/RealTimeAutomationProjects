package PuneethChidura.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ExtentSparkReporterConfig;

public class ExtentReporterNG {

	public  static ExtentReports getReportObject()
	{
		String PathForExtentReports = System.getProperty("User.dir")+"\\Reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(PathForExtentReports);
		ExtentSparkReporterConfig reporterconfigurator = reporter.config();
		reporterconfigurator.setDocumentTitle("Automation Test results");
		reporterconfigurator.setReportName("Test Automation results");
		
		ExtentReports extentreport = new ExtentReports();
		extentreport.attachReporter(reporter);
		extentreport.setSystemInfo("Tester", "Puneeth Chidura");
		
		return extentreport;
	}
}
