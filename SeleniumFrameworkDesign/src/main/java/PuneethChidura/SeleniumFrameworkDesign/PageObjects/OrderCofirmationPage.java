package PuneethChidura.SeleniumFrameworkDesign.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderCofirmationPage {
	
	WebDriver driver;
	public OrderCofirmationPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//h1[@class='hero-primary']")
	WebElement OrderConfirmationMessageHeader;
	
	public String getConfirmationPageHeader()
	{
		return OrderConfirmationMessageHeader.getText().trim();
	}

}
