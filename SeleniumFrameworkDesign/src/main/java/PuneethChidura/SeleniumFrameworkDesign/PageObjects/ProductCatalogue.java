package PuneethChidura.SeleniumFrameworkDesign.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import src.test.java.PuneethChidura.SeleniumFrameworkDesign;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import PuneethChidura.SeleniumFrameworkDesign.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent{

	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//List<WebElement> Products = driver.findElements(By.cssSelector(".mb-3"));
	
	
	By ProductsList = By.cssSelector(".mb-3");
	By AddToCart = By.xpath("//button[@class='btn w-10 rounded']");
	By AddToCartToastMessage = By.xpath("//*[@id='toast-container']");
	 
	
	@FindBy(css = ".mb-3")
	List<WebElement> Products;
	
	
	
	public List<WebElement> getProductsList()
	{
		waitForElementToAppear(ProductsList, 15);
		return Products;
	}
	
	public WebElement getProductByName(String ProductName)
	{
		WebElement RequiredProduct = Products.stream().filter(product->product.getText().trim().contains(ProductName)).findAny().orElse(null);
		return RequiredProduct;
	}
	
	public void addProductToCart(String ProductName)
	{
		WebElement Product = getProductByName(ProductName);
		Product.findElement(AddToCart).click();
		waitForElementToAppear(AddToCartToastMessage);
		waitForElementToDisappear(By.cssSelector(".ng-animating"));
	}
	
	
	
}
