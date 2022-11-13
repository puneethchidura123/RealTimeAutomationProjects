package PuneethChidura.SeleniumFrameworkDesign.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import PuneethChidura.SeleniumFrameworkDesign.AbstractComponents.AbstractComponent;

public class MyCartPage extends AbstractComponent {

	WebDriver driver;
	
	public MyCartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//*[@class = 'cartSection']/h3")
	List<WebElement> ProductsInCart;
	
	@FindBy(xpath = "//button[text()='Checkout']")
	WebElement CheckOutButton;
	
	public long getNoOfProductsInCart(String ProductName)
	{ 
		return ProductsInCart.stream().filter(product -> product.getText().trim().equals(ProductName)).count();
	}
	
	public PaymentPage checkOutCart()
	{
		CheckOutButton.click();
		return new PaymentPage(driver);
	}

	
}
