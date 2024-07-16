package driverfactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;




public class DriversFactory 
{
	static WebDriver driver;
	public static void initializeBrowser(String browserName)
	{
		WebDriverManager.chromedriver().setup();
//		WebDriverManager.edgedriver().setup();
		
	//	WebDriver driver = new ChromeDriver();
//		WebDriver driver = new EdgeDriver();
//		driver.get("https://fmc.justbilling.co/");
		
		
		
		if(browserName.equals("chrome"))
		{
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--incognito");
			driver = new ChromeDriver(options);
		}

		else if(browserName.equals("edge"))
		{
			driver = new EdgeDriver();
		}

		
	}
	public static WebDriver getDriver()
	{
		return driver;
	}
	
	

}
