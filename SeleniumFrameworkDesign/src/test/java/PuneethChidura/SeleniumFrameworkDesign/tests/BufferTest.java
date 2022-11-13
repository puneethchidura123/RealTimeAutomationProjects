package PuneethChidura.SeleniumFrameworkDesign.tests;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
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
import io.github.bonigarcia.wdm.WebDriverManager;


public class BufferTest extends BaseTest{

	//LandingPage lp = landingpage;
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		
		
        System.setProperty("webdriver.chrome.driver", "D:\\Real Time Automation Projects\\SeleniumFrameworkDesign\\Drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/client/");
        driver.findElement(By.xpath("//*[@id='userEmail']")).sendKeys("puneethjb@gmail.com");
        driver.findElement(By.xpath("//*[@id='userPassword']")).sendKeys("testuseR@1");
        driver.findElement(By.xpath("//*[@id='login']")).click();
        Thread.sleep(1000);
        List<WebElement> Products = driver.findElements(By.cssSelector(".mb-3"));
        System.out.println(Products.size());
        
        Iterator itr = Products.iterator();
        while(itr.hasNext())
        {
        	WebElement CurrentWe = (WebElement) itr.next();
        	String CurrentWeText = CurrentWe.getText().trim();
        	System.out.println("CurrentWeText :: "+CurrentWeText);
        	if(CurrentWeText.contains("ADIDAS ORIGINAL"))
        	{
        		System.out.println("element found");
        		CurrentWe.findElement(By.xpath("//button[@class='btn w-10 rounded']")).click();
        		break;
        	}
        }
	}

}
