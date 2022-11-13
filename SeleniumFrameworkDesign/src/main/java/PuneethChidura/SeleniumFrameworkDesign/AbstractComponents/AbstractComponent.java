package PuneethChidura.SeleniumFrameworkDesign.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import PuneethChidura.SeleniumFrameworkDesign.PageObjects.MyCartPage;
import PuneethChidura.SeleniumFrameworkDesign.PageObjects.OrdersPage;

public class AbstractComponent {

	WebDriver driver;
	
	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//button[@routerlink='/dashboard/cart']")
	WebElement CartButton;
	
	@FindBy(xpath = "//button[@routerlink='/dashboard/myorders']")
	WebElement OrdersButton;
	
	public void waitForElementToAppear(By Locator,long timeinseconds)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeinseconds));
		Duration d  =  Duration.ofSeconds(5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3"))); 
	}
	
	public void waitForElementToAppear(By Locator)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		Duration d  =  Duration.ofSeconds(5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(Locator)); 
	}
	
	public void waitForElementToAppear(WebElement webelement)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		Duration d  =  Duration.ofSeconds(5);
		wait.until(ExpectedConditions.visibilityOf(webelement)); 
	}
	
	public void waitForElementToDisappear(By Locator)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		Duration d  =  Duration.ofSeconds(5);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(Locator)); 
	}
	
	public MyCartPage openCart()
	{
		try
		{
			CartButton.click();
		}
		catch(ElementClickInterceptedException e)
		{
			System.out.println("inside catch");
			//driver.navigate().refresh();
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@routerlink='/dashboard/cart']")));
			waitForElementToAppear(CartButton);
			CartButton.click();
		}
		
		return new MyCartPage(driver);
	}
	
	public OrdersPage openOrders()
	{
		OrdersButton.click();
		OrdersPage orderspage = new OrdersPage(driver);
		return orderspage;
	}
}
