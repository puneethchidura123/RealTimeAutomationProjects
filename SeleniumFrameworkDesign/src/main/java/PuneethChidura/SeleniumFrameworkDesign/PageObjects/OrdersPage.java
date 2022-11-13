package PuneethChidura.SeleniumFrameworkDesign.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import PuneethChidura.SeleniumFrameworkDesign.AbstractComponents.AbstractComponent;

public class OrdersPage extends AbstractComponent{

	WebDriver driver;
	
	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> ProductNames; 
	
	
	public boolean verifyIfOrderDisplayed(String ProductName)
	{ 
		boolean isOrderDisplayed =   ProductNames.stream().anyMatch(productname -> productname.getText().trim().equals(ProductName));
		return isOrderDisplayed;
	}

}
