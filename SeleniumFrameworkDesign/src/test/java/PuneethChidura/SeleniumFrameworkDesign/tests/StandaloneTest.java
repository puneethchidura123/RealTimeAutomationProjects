package PuneethChidura.SeleniumFrameworkDesign.tests;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import PuneethChidura.SeleniumFrameworkDesign.PageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandaloneTest{

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		String productname = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client/");
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		
		
		driver.manage().window().maximize();
		
		LandingPage landingpage = new LandingPage(driver);
		driver.findElement(By.xpath("//*[@id='userEmail']")).sendKeys("puneethchidura123@gmail.com");
		driver.findElement(By.xpath("//*[@id='userPassword']")).sendKeys("iloveududE@1");
		driver.findElement(By.xpath("//*[@id='login']")).click();
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		
		List<WebElement> Products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement RequiredProduct = Products.stream().filter(product->product.findElement(By.xpath("//div/div/h5/b")).getText().trim().equals(productname)).findFirst().orElse(null);
		
		if(RequiredProduct != null)
		{
			WebElement RequiredProductAddToCartButton = 
			RequiredProduct.findElement(By.xpath("//button[@class='btn w-10 rounded']"));
			RequiredProductAddToCartButton.click();
		}
		
		
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='toast-container']")));
		
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@routerlink='/dashboard/cart']")));
		
		WebElement CartButton = driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']"));
		try
		{
			CartButton.click();
		}
		catch(ElementClickInterceptedException e)
		{
			System.out.println("inside catch");
			Thread.sleep(10);
			//driver.navigate().refresh();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@routerlink='/dashboard/cart']")));
			CartButton.click();
		}
		
		
		List<WebElement> ProductsInCart = driver.findElements(By.xpath("//*[@class = 'cartSection']/h3"));
		
		Stream<WebElement> ProductsInCartStream = ProductsInCart.stream().filter(product -> product.getText().trim().equals(productname));
		
		Assert.assertEquals(1, ProductsInCartStream.count());
		//System.out.println("no of elements in cart :: "+ProductsInCartStream.count());
		
		WebElement CheckoutButton = driver.findElement(By.xpath("//button[text()='Checkout']"));
		CheckoutButton.click();
		
		String CountryName = "India";
		WebElement SelectCountryTextBox = driver.findElement(By.xpath("//input[@placeholder='Select Country']"));
		SelectCountryTextBox.sendKeys(CountryName);
		
		List<WebElement> CountryOptions = driver.findElements(By.xpath("//button[@class='ta-item list-group-item ng-star-inserted']/span")); 
		WebElement RequiredCountryOption = 
		CountryOptions.stream().filter(country -> country.getText().trim().equals(CountryName)).findFirst().orElse(null);
		
		if(RequiredCountryOption!=null)
		{
			RequiredCountryOption.click();
		}
		
		WebElement PlaceOrderButton = driver.findElement(By.xpath("//a[contains(text(),'Place Order')]"));
		PlaceOrderButton.click();
		
		String ConfirmationMessage = driver.findElement(By.xpath("//h1[@class='hero-primary']")).getText();
		Assert.assertTrue(ConfirmationMessage.equalsIgnoreCase(ConfirmationMessage));
		
		driver.close();
	}

}
