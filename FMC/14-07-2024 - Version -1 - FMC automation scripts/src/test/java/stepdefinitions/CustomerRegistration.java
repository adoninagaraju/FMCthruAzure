package stepdefinitions;


import java.time.Duration;
import java.util.Map;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import driverfactory.DriversFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

public class CustomerRegistration extends CustomerBaseRegisterClass

{
	WebDriver driver;

	@Given("User navigates to Customer Registration page")
	public void user_navigates_to_customer_registration_page() 
	{
		driver = DriversFactory.getDriver();
		driver.findElement(By.xpath("//i[@title='Customers']")).click();
		System.out.println("User got navigated to Customer registration page");
	}

	@When("User clicks on Add user button popup window opens")
	public void user_clicks_on_Add_user_button_popup_opens() 
	{
		driver.findElement(By.xpath("//a[@class='btn btn-primary']")).click();
		System.out.println("User clicks on Add user button");
	}

	@When("User enters the details into following fields")
	public void user_enters_the_details_into_following_fields(DataTable dataTable)
	{
		Map<String, String>	dataMap = dataTable.asMap(String.class,String.class);

		driver.findElement(By.id("Organization")).sendKeys(dataMap.get("Company Name"));
		driver.findElement(By.id("ContactName")).sendKeys(dataMap.get("Contact Name"));
		driver.findElement(By.id("Phone")).sendKeys(dataMap.get("Phone Number"));
		driver.findElement(By.id("Email")).sendKeys(dataMap.get("Email"));
		driver.findElement(By.id("NewPassword")).sendKeys(dataMap.get("New Password"));
		driver.findElement(By.id("ConfirmPassword")).sendKeys(dataMap.get("Confirm Password"));
	}

	@And("User clicks on Register button")
	public void user_clicks_on_register_button() throws InterruptedException 
	{
		Thread.sleep(1000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
		driver.findElement(By.id("btnSubmit")).click();
		System.out.println("User clicked on Register button");
	}

	@Then("Customer Account should get created successfully")
	public void customer_account_should_get_created_successfully() throws InterruptedException 
	{
		Errormessage1();	
		Thread.sleep(3000);
	}

	@Then("User should get the proper warning message informing about the duplicate email address")
	public void User_should_get_the_proper_warning_message_informing_about_the_duplicate_email_address() 
	{
		Assert.assertTrue(driver.findElement(By.id("Email-error")).getText().contains("Email already registerd."));
		WebElement emailField = driver.findElement(By.id("Email")); // Example: Locate by ID
		System.out.println("The Duplicate Email id is : " + emailField.getAttribute("value"));
	}

	@When("User dont enter details into any of the fields")
	public void user_dont_enter_details_into_any_of_the_fields()
	{
		System.out.println("User has kept all the fields blank");
	}


	@Then("Admin User should get a proper warning message to fill all mandatory fields")
	public void admin_user_should_get_a_proper_warning_message_to_fill_all_mandatory_fields() 
	{
		checkFieldErrorMessage("Organization", "Please enter Company Name");
		checkFieldErrorMessage("ContactName", "Please enter contact name.");
        checkFieldErrorMessage("Phone", "Please enter phone number.");
        checkFieldErrorMessage("Email", "Please enter email address.");
        checkFieldErrorMessage("NewPassword", "Please enter new password.");
        checkFieldErrorMessage("ConfirmPassword", "Please enter confirm password.");          
        
		System.out.println("Warning message pop ups to fill all the mandatory fields");
	}

}
