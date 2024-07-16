package hooks;

import java.time.Duration;

import org.openqa.selenium.WebDriver;

import driverfactory.DriversFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class MyHooksProjectrunner 
{
	WebDriver driver;
	@Before
	public void setup()
	{
		DriversFactory.initializeBrowser("chrome");
		driver = DriversFactory.getDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://fmc.justbilling.co/");
	}
	
	@After
	public void teardown()
	{
		driver.quit();
	}
	
	

}
