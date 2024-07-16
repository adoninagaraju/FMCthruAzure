package stepdefinitions;

import java.time.Duration;
import java.util.Scanner;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import driverfactory.DriversFactory;
import io.cucumber.java.en.*;

public class Login 
{
	WebDriver driver;

	@Given("User navigates to Login page")
	public void User_navigates_to_Login_page()
	{
		driver = DriversFactory.getDriver();
		System.out.println("User got navigated to login page using the URL : " +driver.getCurrentUrl());
		driver.getCurrentUrl();
	}

	@When("User enters valid email address {string}	into email field")
	public void User_enters_valid_email_address_into_email_field(String email)
	{
		driver.findElement(By.id("Email")).sendKeys(email);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
		//System.out.println("User entered valid email address "+emailText);
	}


	@When("Enters valid password {string} into password field")
	public void enters_valid_password_into_password_field(String passwordText) 
	{
		driver.findElement(By.id("Password")).sendKeys(passwordText);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
		//	System.out.println("User entered valid password: "+passwordText);
	}


	@When("Clicks on Login button")
	public void clicks_on_login_button()
	{
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1000));
		//	System.out.println("User clicks on login button");
	}

	@Then("User should get verification code sent to registered email address")
	public void user_should_get_verification_code_sent_to_registered_email_address()
	{
		System.out.println("User gets verification code to gmail account");
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}

	@When("User enters the verification code")
	public void user_enters_the_verification_code() throws InterruptedException 
	{
//		WebElement pascodeField = driver.findElement(By.xpath("//input[@name='passcode']"));
//		Scanner scanner = new Scanner(System.in);
//		System.out.print("Enter your pascode: ");
//		String pascode = scanner.nextLine();
//		pascodeField.sendKeys(pascode);
//		scanner.close();
		
		enterPascode();		
		// Example of verification result handling
		if (verificationFailed()) 
		{             	
			System.out.println("Verification code incorrect. Please try again." );
			WebElement pascodeField1 = driver.findElement(By.xpath("//input[@name='passcode']"));
			// Clear the input field if needed
			pascodeField1.clear();
			user_enters_the_verification_code(); // Recursive call, ensure it's necessary
			Thread.sleep(20000);
		} else 
		{
			System.out.println("Verification code entered successfully.");
		}
	}

	private void enterPascode() throws InterruptedException 
	{
		Thread.sleep(5000);
		WebElement pascodeField = driver.findElement(By.xpath("//input[@name='passcode']"));
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter your pascode: ");
		String pascode = scanner.nextLine();
		Thread.sleep(5000);
		pascodeField.sendKeys(pascode);
		scanner.close();		
	}

	private boolean verificationFailed() 
	{
		driver.findElement(By.xpath("//input[@value='Submit']")).click();		
		boolean isdisplayed = driver.findElements(By.xpath("//div[@class='validation-summary-errors text-danger']//ul//li")).size() > 0 
					          && driver.findElement(By.xpath("//div[@class='validation-summary-errors text-danger']//ul//li")).isDisplayed() ? true : false;
			
			if(isdisplayed) //if true this condition will execute(If same page is still opened)
			{
				System.out.println("checking error message is there or not");
				return true;	
		    }
			else 
			{
				return false;
			}
		
	}
	

	@And("Clicks on submit button")
	public void clicks_on_submit_button() 
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.findElement(By.xpath("//input[@value='Submit']")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		//	System.out.println("User clicks on submit button");
	}

	@Then("User should be able to login successfully")
	public void user_should_be_able_to_login_successfully() throws InterruptedException 
	{
		Assert.assertTrue(driver.findElement(By.id("tskName")).isDisplayed());
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1500));
		Thread.sleep(100);
	}

	@When("User enters invalid email address {string} into email field")
	public void user_enters_invalid_email_address_into_email_field(String invalidemailAddress) 
	{
		driver.findElement(By.id("Email")).sendKeys(invalidemailAddress);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));
		//	System.out.println("User has entered invalid email address: "+invalidemailAddress);
	}

	@When("Enters invalid password {string} into password field")
	public void enters_invalid_password_into_password_field(String invalidpassword)
	{
		driver.findElement(By.id("Password")).sendKeys(invalidpassword);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));
		//	System.out.println("User entered invalid password: "+invalid password);
	}

	@Then("User should get a proper warning message")
	public void user_should_get_a_proper_warning_message() 
	{
		//   Assert.assertTrue(driver.findElement(By.xpath("//div[@class='validation-summary-errors text-danger']")).getText().contains("Invalid Username or Password Please try again"));
		//   above code is used to confirm if the warning message is true or not but below one helps to get the warning message
		WebElement warningMessageElement = driver.findElement(By.xpath("//div[@class='validation-summary-errors text-danger']"));
		String warningMessage = warningMessageElement.getText();
		System.out.println("User gets a proper warning message: " +warningMessage);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10000));
	}


	@When("User dont enter any credentials")
	public void user_dont_enter_any_credentials() 
	{
		driver.findElement(By.id("Email")).sendKeys("");
		driver.findElement(By.id("Password")).sendKeys("");
		System.out.println("User didnt enter any credentials");	
	}

	@Then("User should get the proper warning message")
	public void user_should_get_the_proper_warning_message() 
	{
		String[] xpaths = {"//span[@id='Email-error']","//span[@id='Password-error']"};
		String[] warningMessages = new String[xpaths.length];
		for (int i = 0; i < xpaths.length; i++) 
		{
			WebElement warningMessageElement = driver.findElement(By.xpath(xpaths[i]));
			warningMessages[i] = warningMessageElement.getText();
			System.out.println("Warning message " + (i + 1) + ": " + warningMessages[i]);
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10000));
	}


}
