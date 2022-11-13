package PuneethChidura.SeleniumFrameworkDesign.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ExtentSparkReporterConfig;

import PuneethChidura.SeleniumFrameworkDesign.PageObjects.MyCartPage;
import PuneethChidura.SeleniumFrameworkDesign.PageObjects.ProductCatalogue;
import PuneethChidura.SeleniumFrameworkDesign.TestComponents.BaseTest;
import PuneethChidura.SeleniumFrameworkDesign.TestComponents.Retry;

public class ErrorValidationTest extends BaseTest {

	// ExtentReports extentreport;
	
	@Test(groups = {"ErrorHandling"},retryAnalyzer = Retry.class)
	public void loginErrorValidation() throws Throwable
	{
		
		
		landingpage.login("puneethchidura123@gmail.com", "iloveududE@17");
		String ErrorMessage =   landingpage.getLoginErrorMessage();
		Assert.assertEquals(ErrorMessage, "Incorrect email and password.");
	}
	
	@Test(groups = {"ErrorHandling"})
	public void productErrorValidation() throws Throwable
	{
		String productname = "ZARA COAT 3";
		ProductCatalogue productcatalogue = landingpage.login("puneethchidura123@gmail.com", "iloveududE@1");
		productcatalogue.addProductToCart(productname);
		MyCartPage mycartpage = productcatalogue.openCart();
		//checking if only one matching product is available
		Assert.assertEquals(2, mycartpage.getNoOfProductsInCart(productname));
	}
	
}
