package PuneethChidura.SeleniumFrameworkDesign.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PuneethChidura.SeleniumFrameworkDesign.PageObjects.LandingPage;
import PuneethChidura.SeleniumFrameworkDesign.PageObjects.MyCartPage;
import PuneethChidura.SeleniumFrameworkDesign.PageObjects.OrderCofirmationPage;
import PuneethChidura.SeleniumFrameworkDesign.PageObjects.OrdersPage;
import PuneethChidura.SeleniumFrameworkDesign.PageObjects.PaymentPage;
import PuneethChidura.SeleniumFrameworkDesign.PageObjects.ProductCatalogue;
import PuneethChidura.SeleniumFrameworkDesign.TestComponents.BaseTest;
import PuneethChidura.data.DataReader;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest extends BaseTest{

	private static final WebDriver TakesScreenshot = null;
	String productname = "ZARA COAT 3";
	
	@Test(dataProvider = "getData",groups = {"Purchase"})
public void submitOrder(HashMap<Object, Object> input) throws Throwable
{
		System.out.println("submitOrder method eecution started");
		System.out.println("current email :: "+input.get("email"));
	ProductCatalogue productcatalogue = landingpage.login((String)input.get("email"), (String)input.get("password"));
	System.out.println("current product name :: "+input.get("product"));
	productcatalogue.addProductToCart((String)input.get("product"));
	MyCartPage mycartpage = productcatalogue.openCart();
	//checking if only one matching product is available
	Assert.assertEquals(1, mycartpage.getNoOfProductsInCart(productname));
	PaymentPage paymentpage = mycartpage.checkOutCart();
	String CountryName = "India";
	OrderCofirmationPage orderconfirmationpage = paymentpage.enterCountryNameAndPlaceOrder(CountryName);
	String RequiredConfirmationMessage = "THANKYOU FOR THE ORDER.";
	String ConfirmationMessage = orderconfirmationpage.getConfirmationPageHeader();
	Assert.assertTrue(ConfirmationMessage.equalsIgnoreCase(RequiredConfirmationMessage));
	System.out.println("submitOrder method eecution completed");
}
	
	// to verify if zara coat 3 is displayed in orders page
	@Test(dependsOnMethods = {"submitOrder"})
	public void orderHistoryTest() throws Exception
	{
		System.out.println("orderHistoryTest method eecution started");
		ProductCatalogue productcatalogue = landingpage.login("puneethchidura123@gmail.com", "iloveududE@1");
		//Thread.sleep(10000);
		OrdersPage orderspage = productcatalogue.openOrders();
		//Thread.sleep(10000);
		orderspage.verifyIfOrderDisplayed(productname);
		System.out.println("orderHistoryTest method eecution completed");
	}
	
	@DataProvider
	public Object[] getData() throws IOException
	{		
		return getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\PuneethChidura\\data\\PurchaseOrder.json").toArray();	
	}

}
