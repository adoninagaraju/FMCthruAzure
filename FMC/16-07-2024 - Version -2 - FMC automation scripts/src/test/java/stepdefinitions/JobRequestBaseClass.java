package stepdefinitions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class JobRequestBaseClass 
{
	WebDriver driver;

	public static void selectDropdownByXPath(WebDriver driver, String xpath) 
	{
		 //	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		try 
		{
			WebElement dropdownElement = driver.findElement(By.xpath(xpath));// Locate the dropdown element by XPath
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5000)); // Use Duration object
			wait.until(ExpectedConditions.elementToBeClickable(dropdownElement));  
			driver.findElement(By.xpath(xpath)).click();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public void Errormessage() throws InterruptedException 
	{
		
	}

}
