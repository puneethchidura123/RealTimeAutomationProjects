package PuneethChidura.SeleniumFrameworkDesign.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import PuneethChidura.SeleniumFrameworkDesign.PageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;
	public  LandingPage landingpage;
	
	public WebDriver initializeDriver() throws Exception
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(new File(System.getProperty("user.dir")+"\\src\\main\\java\\PuneethChidura\\resources\\GlobalData.properties"));
		prop.load(fis);
		String BrowserName = 
		System.getProperty("browser") != null ?System.getProperty("browser"):prop.getProperty("browser");
		if(BrowserName.contains("chrome"))
		{
			//WebDriverManager.chromedriver().driverVersion("107.0.5304.63").setup();
			//WebDriverManager.chromedriver().setup();
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\Drivers\\chromedriver.exe");
			if(BrowserName.contains("headless"))
			{
				ChromeOptions chromeoptions = new ChromeOptions();
				chromeoptions.addArguments("headless");
				driver = new ChromeDriver(chromeoptions);
			}
			driver = new ChromeDriver();
		}
		else if (BrowserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else
		{
			System.out.println("given browser name is invalid");
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();  
		
		return driver;
	}
	
	@BeforeMethod(alwaysRun = true)
	public void launchApplication() throws Exception
	{
		WebDriver driver = initializeDriver();
		landingpage = new LandingPage(driver);
		landingpage.goTo();
		//return landingpage;
	}

	@AfterMethod(alwaysRun = true)
	public void closeApplication()
	{
		driver.close();
	}
    
	public List<HashMap<String,String>> getJsonDataToMap(String FilePath) throws IOException
	{
	  // reading json to string	
	String JsonContent	=  FileUtils.readFileToString(new File(FilePath),StandardCharsets.UTF_8);
	// string to hashmap using jackson data bind
	ObjectMapper mapper = new ObjectMapper();
	List<HashMap<String,String>> data =
	mapper.readValue(JsonContent, new TypeReference<List<HashMap<String,String>>>() {
	});
	return data;
	}
	
	
	public String getScreenshot(String TestCaseName, WebDriver driver) throws IOException
	{
		TakesScreenshot takescreenshot  =  (TakesScreenshot)driver;
		File f1 = 
		takescreenshot.getScreenshotAs(OutputType.FILE);
		File f2 = new File(System.getProperty("user.dir")+"//ScreenShots//"+TestCaseName+".png");
		FileUtils.copyFile(f1, f2);
		return System.getProperty("user.dir")+"//ScreenShots//"+TestCaseName+".png";
	}
	
	public File getScreenshotInFile(String CompleteFileLocation)
	{
		//FileUtils.copyFile(((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE), new File(CompleteFileLocation));
		 return ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	}

}
