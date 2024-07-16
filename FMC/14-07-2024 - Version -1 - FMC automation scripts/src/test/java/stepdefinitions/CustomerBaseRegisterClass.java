package stepdefinitions;

import java.time.Duration;
import java.util.List;

import javax.swing.JOptionPane;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import driverfactory.DriversFactory;

public class CustomerBaseRegisterClass
{

	WebDriver driver;
	public void Errormessage1() throws InterruptedException 
	{
		driver = DriversFactory.getDriver();
		boolean isdisplayed = (driver.findElement(By.xpath("//form[@action='/HMAS/CustomerRegister']")).isDisplayed());
		if(isdisplayed) //if true this condition will execute(If same page is still opened)
		{
			System.out.println("User Account creation failed");
			boolean isdisplayed1 = driver.findElements(By.id("Email-error")).size() > 0 
					&& driver.findElement(By.id("Email-error")).isDisplayed() ? true : false;
			if(isdisplayed1) //user id validation error
			{
				System.out.println("User Account creation failed, trying with another emailID");
				//  Attempt user creation with new email ID
				String newEmailId = "xyz@gmail.com";
				attemptUserCreation(newEmailId);
			}
			else 
			{
				System.out.println("EmailID error message is not displayed. Cannot proceed.");
				driver.findElement(By.id("btnSubmit")).click();
				//  Handle the scenario where EmailID error message is not displayed
			}
		}
		else
		{
			System.out.println("Customer Account got successfully created");
			//this means form0 which is customer registration page is not displayed. Check your conditions
			Thread.sleep(1000);
		}
	}

	public void attemptUserCreation(String emailid) throws InterruptedException 
	{
		// Clear the EmailID field
		driver.findElement(By.id("Email")).clear();

		// Enter the email ID
		driver.findElement(By.id("Email")).sendKeys(emailid);
		Thread.sleep(2000);

		// Click on the 'Register' button
		driver.findElement(By.id("btnSubmit")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Thread.sleep(5000);

		//check page url if page url is Customer register return true  else page name is list then return false
		//store url in a string and check url using if conditon

		boolean checkCurrentPage1 = checkCurrentPage();
		if(checkCurrentPage1)//if page is registration attempt user creation
		{
			List<WebElement> errorElements = driver.findElements(By.id("Email-error"));
	        boolean isUserIDErrorDisplayed = errorElements.size() > 0 && errorElements.get(0).isDisplayed();
	        
			if (isUserIDErrorDisplayed)

			{
				System.out.println("Email ID validation error. Retrying with another new email ID...");
				// Generate a other new email ID (or fetch dynamically)
				String newEmailId = generateNewEmailId(); // Implement this method as per your requirement
				System.out.println("81 New Email Id is : "+newEmailId);
				// Recursively attempt user creation with the other new email ID
				attemptUserCreation(newEmailId);
			} 
		}
		else //if not check table
			{
				System.out.println("Entered checking table");
				boolean isNewEmailIdPresent = isNewEmailIdPresentInTable(emailid);
				if (isNewEmailIdPresent) 
				{
					System.out.println("New email ID found in the table: " + emailid);
					Assert.assertTrue("New email ID should be present in the table", isNewEmailIdPresent);
					System.out.println("94 User Account created successfully!");	
				} 
				else 
				{
					System.out.println("New email ID not found in the table: " + emailid);
					Assert.fail("Newly created email ID is not present in the table");
					System.out.println("94 User Account is not created successfully!");	
				}
				
			}
		}
	

	public boolean checkCurrentPage() 
	{
		//WebDriver driver = DriversFactory.getDriver();

		// Get the current URL of the page
		String currentUrl = driver.getCurrentUrl();

		// Check if the current URL contains "CustomerRegister"
		if (currentUrl.contains("https://fmc.justbilling.co/HMAS/CustomerRegister")) 
		{
			System.out.println("Admin is in Customer Registeration page");
			return true;
		} 
		else if (currentUrl.contains("https://fmc.justbilling.co/CustomerDetails/CustomerList/")) 
		{
			System.out.println("Admin is in Customer list page");
			return false;
		}
		return false; 
	}


	public String generateNewEmailId() throws InterruptedException 
	{
		// Implement your logic to generate or fetch a new email ID dynamically

		WebElement generateNewEmailId = driver.findElement(By.id("Email"));		
		String EmailID = JOptionPane.showInputDialog("Enter your other New Email ID:");       
		Thread.sleep(3000);

		driver.findElement(By.id("ContactName")).click();
		//driver.findElement(By.id("btnSubmit")).click();
		generateNewEmailId.sendKeys(EmailID);
		return EmailID; 
		// Replace with actual logic
	}

	// Method to verify if a newly created email ID is present in the table
	public boolean isNewEmailIdPresentInTable(String newEmailId) throws InterruptedException 
	{	
		Thread.sleep(3000);
		// Wait for table to be present
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
		WebElement table = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("tbl_Customers")));

		// Assume the table has rows with email IDs in <td> elements
		List<WebElement> tableRows = table.findElements(By.tagName("tr"));
		for (WebElement row : tableRows) 
		{
			Thread.sleep(3000);
			try 
			{
				// Find the first cell (assuming email is in first column)
				WebElement emailCell = row.findElement(By.xpath("./td[2]"));
				String cellText = emailCell.getText().trim();

				// Check for exact match
				if (cellText.equals(newEmailId.trim())) 
				{
					System.out.println("Found the new email ID in the table");
					return true; // Found the email ID, so return true			
				}

			}
			catch(org.openqa.selenium.NoSuchElementException e)
			{
				// Handle exception if element not found or timeout occurs
				// This block ensures the loop continues to the next row
				continue;
			}
		}
		System.out.println("Expected Email ID: " + newEmailId.trim());
		driver.quit();
		return false;

	}

	public void checkFieldErrorMessage(String fieldId, String expectedErrorMessage) 
	{
		driver = DriversFactory.getDriver();
		WebElement errorElement = driver.findElement(By.id(fieldId + "-error"));
		boolean isErrorMessageDisplayed = errorElement.isDisplayed();
		Assert.assertTrue("Error message for " + fieldId + " is not displayed", isErrorMessageDisplayed);
		String errorMessageText = errorElement.getText();
		Assert.assertTrue("Error message for " + fieldId + " does not contain expected text: " + expectedErrorMessage,
				          errorMessageText.contains(expectedErrorMessage));
		System.out.println(fieldId + " error message: " + errorMessageText);
	}

}
