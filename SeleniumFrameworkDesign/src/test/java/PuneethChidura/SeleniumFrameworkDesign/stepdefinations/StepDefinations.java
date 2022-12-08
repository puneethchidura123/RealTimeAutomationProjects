package PuneethChidura.SeleniumFrameworkDesign.stepdefinations;

import org.testng.Assert;
import PuneethChidura.SeleniumFrameworkDesign.PageObjects.LandingPage;
import PuneethChidura.SeleniumFrameworkDesign.PageObjects.MyCartPage;
import PuneethChidura.SeleniumFrameworkDesign.PageObjects.OrderCofirmationPage;
import PuneethChidura.SeleniumFrameworkDesign.PageObjects.PaymentPage;
import PuneethChidura.SeleniumFrameworkDesign.PageObjects.ProductCatalogue;
import PuneethChidura.SeleniumFrameworkDesign.TestComponents.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinations extends BaseTest{

	public LandingPage landingpage ;
	public ProductCatalogue productcatalogue;
	public MyCartPage mycartpage;
	public PaymentPage paymentpage;
	public OrderCofirmationPage orderconfirmationpage;
	
	@Given("i landed on ecommerce home page")
	public void i_landed_on_ecommerce_home_page() throws Exception
	{
		System.out.println("entered first method");
		landingpage =  launchApplication();
		System.out.println("completed executing  first method");
	}
	
	@Given("^i loggedin with (.+)  and (.+)$")
	public void i_loggedin_with_username_and_password(String username,String password)
	{
		System.out.println("entered second method");
		productcatalogue =  landingpage.login(username, password);
		System.out.println("completed executing second method");
	}
	
	@When("^i add the (.+) to cart$")
	public void i_add_the_product_name_to_cart(String productname)
	{
		productcatalogue.addProductToCart(productname);
	}
	
	@And("^chcekout for (.+) and submit order")
	public void chcekout_for_product_name_and_submit_order(String productname)
	{
		mycartpage = productcatalogue.openCart();
		paymentpage = mycartpage.checkOutCart();
		orderconfirmationpage   =    paymentpage.enterCountryNameAndPlaceOrder("India");
		
	}
	
	@Then("verify if confirmation message {string} is displayed")
	public void verify_if_confirmation_message_is_displayed(String RequiredConfirmationMessage)
	{
		String  encounteredconfirmationmessage =   orderconfirmationpage.getConfirmationPageHeader();
		System.out.println("encounteredconfirmationmessage :: "+encounteredconfirmationmessage);
		Assert.assertTrue(RequiredConfirmationMessage.equals(encounteredconfirmationmessage.trim()));
		driver.close();
	}
	
	@Then("{string} message is displayed")
	public void message_is_displayed(String ExpectedErrorMessage)
	{
		Assert.assertTrue(ExpectedErrorMessage.equals(landingpage.getLoginErrorMessage()));
		driver.close();
	}
}
