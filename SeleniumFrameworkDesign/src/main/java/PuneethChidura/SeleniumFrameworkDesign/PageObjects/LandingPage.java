package PuneethChidura.SeleniumFrameworkDesign.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import PuneethChidura.SeleniumFrameworkDesign.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{

	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//WebElement UserEmail = driver.findElement(By.xpath("//*[@id='userEmail']"));
	
	@FindBy(xpath = "//*[@id='userEmail']")
	WebElement UserEmail;
	
	@FindBy(xpath = "//*[@id='userPassword']")
	WebElement UserPassword;
	
	@FindBy(xpath = "//*[@id='login']")
	WebElement SubmitButton;
	
    @FindBy(xpath = "//div[@aria-label='Incorrect email or password.']")
    WebElement LoginErrorMessage;
	
	public ProductCatalogue login(String UserName,String Password)
	{
		UserEmail.sendKeys(UserName);
		UserPassword.sendKeys(Password);
		SubmitButton.click();
		return new ProductCatalogue(driver);
	}
	
	public String getLoginErrorMessage()
	{
		waitForElementToAppear(LoginErrorMessage);
		return LoginErrorMessage.getText().trim();
	}
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client/");
	}
	
}
