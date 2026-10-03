package ManishChaudhary.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import ManishChaudhary.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

public class BaseTest {
	public WebDriver driver;
	public LandingPage landingpage;
	
	public WebDriver initializeDriver() throws IOException
	{
		// properties class 
		  Properties prop = new Properties();
		  FileInputStream fis = new FileInputStream(
				    System.getProperty("user.dir") + "/src/main/java/ManishChaudhary/resourses/GlobalData.properties"
				);
		  prop.load(fis);
		  String browserName = System.getProperty("browser")!=null? System.getProperty("browser"): prop.getProperty("browser");
		  
		  if(browserName.equalsIgnoreCase("edge"))
		  {
			  WebDriverManager.edgedriver().setup();  
	          driver =new EdgeDriver();
			  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			  driver.manage().window().maximize();
		  }
		  else if(browserName.contains("chrome"))
		  {   ChromeOptions options =new ChromeOptions();
			  WebDriverManager.chromedriver().setup();
			  if (browserName.contains("headless"))
			  {
				  options.addArguments("headless");
			  }
			  driver=new ChromeDriver(options);
			  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			  //driver.manage().window().maximize();
			  driver.manage().window().setSize(new Dimension(1440, 900));
		  }
		  else if(browserName.equalsIgnoreCase("firefox"))
		  {        
				WebDriverManager.firefoxdriver().setup();  
			    driver=new FirefoxDriver();
			    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				driver.manage().window().maximize();
		  }
		  else if(browserName.equalsIgnoreCase("safari"))
		  {       
			    driver = new SafariDriver();
			    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				driver.manage().window().maximize();
		  }
		  
		  return driver;
	}
	
	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException
	{
		driver = initializeDriver();
		landingpage=new LandingPage(driver);
		return landingpage;	
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDown()
	{    if(driver != null)
		    {
				driver.quit();
		    }
		
	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException
	{
		//read json to string
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
		
		//String to HashMap - jackson data bind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){});
		
		return data;
	}
	public String getScreenShot(String testCaseName, WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"\\reports\\"+testCaseName+".png");
		FileUtils.copyFile(source, file);
		
		return System.getProperty("user.dir")+"\\reports\\"+testCaseName+".png";
	}
	
}
