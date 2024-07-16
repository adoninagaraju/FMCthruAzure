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

public class AdminBaseRegisterClass

{
	WebDriver driver;
	//	WebDriver driver = DriversFactory.getDriver();
	public void Errormessage() throws InterruptedException
	{
		driver = DriversFactory.getDriver();
		boolean isdisplayed = (driver.findElement(By.id("ModalAddUser")).isDisplayed());
		if(isdisplayed) //if true this condition will execute(If popup is open)
		{
			System.out.println("User Account creation failed");
			boolean isdisplayed1 = driver.findElements(By.id("UserID-error")).size() > 0 
					              && driver.findElement(By.id("UserID-error")).isDisplayed() ? true : false;
			if(isdisplayed1) //user id validation error
			{
				System.out.println("User Account creation failed, trying with another emailID");
				//  Attempt user creation with initial email ID
				String initialEmailId = "xyz@gmail.com";
				attemptUserCreation(initialEmailId);
			}
			else 
			{
				System.out.println("UserID error message is not displayed. Cannot proceed.");
				driver.findElement(By.id("btn_addUser")).click();
				//  Handle the scenario where UserID error message is not displayed
			}
		}
		else
		{
			System.out.println("User Account got successfully created");
			//this means ModalAddUser popup is not displayed. Check your conditions
			Thread.sleep(1000);
		}
	}

	public void attemptUserCreation(String emailid) throws InterruptedException 
	{
		// Clear the UserID field
		driver.findElement(By.id("UserID")).clear();

		// Enter the email ID
		driver.findElement(By.id("UserID")).sendKeys(emailid);
		Thread.sleep(2000);

		// Click on the 'save' button
		driver.findElement(By.id("btn_addUser")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//Thread.sleep(5000);
		
		
		boolean isdisplayed = (driver.findElement(By.id("ModalAddUser")).isDisplayed());
		if(isdisplayed) //if true this condition will execute(If popup is open)
		{
		// Check if UserID-error is displayed (validation error)
		List<WebElement> errorElements = driver.findElements(By.id("UserID-error"));
		boolean isUserIDErrorDisplayed = !errorElements.isEmpty() && errorElements.get(0).isDisplayed();
		if (isUserIDErrorDisplayed)
		{
			System.out.println("User ID validation error. Retrying with a new email ID...");
			// Generate a new email ID (or fetch dynamically)
			String newEmailId = generateNewEmailId(); // Implement this method as per your requirement
			System.out.println("75 New Email Id is : "+newEmailId);
			// Recursively attempt user creation with the new email ID
			attemptUserCreation(newEmailId);
		} 
		else 
		{
			System.out.println("Entered checking table");
			boolean isNewEmailIdPresent = isNewEmailIdPresentInTable(emailid);
			if (isNewEmailIdPresent) 
			{
				System.out.println("New email ID found in the table: " + emailid);
				Assert.assertTrue("New email ID should be present in the table", isNewEmailIdPresent);
				System.out.println("84 User Account created successfully!");	
			} else {
				System.out.println("New email ID not found in the table: " + emailid);
				Assert.fail("Newly created email ID is not present in the table");
				System.out.println("88 User Account not successful!");	
			}
			// Success message or further validation/assertions can be added here
				System.out.println("91 User Account created successfully!");	

		}
		}
		else 
		{
			System.out.println("Entered checking table");
			boolean isNewEmailIdPresent = isNewEmailIdPresentInTable(emailid);
			if (isNewEmailIdPresent) 
			{
				System.out.println("New email ID found in the table: " + emailid);
				Assert.assertTrue("New email ID should be present in the table", isNewEmailIdPresent);
				System.out.println(">> User Account created successfully!");	
			} else {
				System.out.println("New email ID not found in the table: " + emailid);
				Assert.fail("Newly created email ID is not present in the table");
				System.out.println("88 User Account not successful!");	
			}
			// Success message or further validation/assertions can be added here
				System.out.println("> User Account created successfully!");	

		}
	}
	public String generateNewEmailId() throws InterruptedException 
	{
		// Implement your logic to generate or fetch a new email ID dynamically

		WebElement generateNewEmailId = driver.findElement(By.id("UserID"));		
		String EmailID = JOptionPane.showInputDialog("Enter your Email ID:");       
		Thread.sleep(3000);

		driver.findElement(By.id("Name")).click();
		//		driver.findElement(By.id("btn_addUser")).click();
		generateNewEmailId.sendKeys(EmailID);
		return EmailID; 
		// Replace with actual logic
	}

	// Method to verify if a newly created email ID is present in the table
	public boolean isNewEmailIdPresentInTable(String newEmailId) throws InterruptedException 
	{	
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Thread.sleep(1000);
		// Wait for table to be present
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement table = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("tbl_Listing")));

		// Assume the table has rows with email IDs in <td> elements
		List<WebElement> tableRows = table.findElements(By.tagName("tr"));
		for (WebElement row : tableRows) 
		{
			Thread.sleep(1000);
			try 
			{
				// Find the first cell (assuming email is in first column)
				WebElement emailCell = row.findElement(By.xpath("./td[1]"));
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

