package PuneethChidura.SeleniumFrameworkDesign.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import PuneethChidura.SeleniumFrameworkDesign.AbstractComponents.AbstractComponent;

public class PaymentPage extends AbstractComponent {

	WebDriver driver;
	
	public PaymentPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@placeholder='Select Country']")
	WebElement SelectCountryTextBox;
	
	@FindBy(xpath = "//a[contains(text(),'Place Order')]")
	WebElement PlaceOrderButton;
	
	public OrderCofirmationPage enterCountryNameAndPlaceOrder(String CountryName)
	{
		SelectCountryTextBox.sendKeys(CountryName);
		List<WebElement> CountryOptions = driver.findElements(By.xpath("//button[@class='ta-item list-group-item ng-star-inserted']/span")); 
		WebElement RequiredCountryOption = 
		CountryOptions.stream().filter(country -> country.getText().trim().equals(CountryName)).findFirst().orElse(null);
		if(RequiredCountryOption!=null)
		{
			RequiredCountryOption.click();
		}
		clickOnWebElement(PlaceOrderButton);
		return new OrderCofirmationPage(driver);
	}
}
