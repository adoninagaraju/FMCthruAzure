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

public class VendorIndividualRegistration extends VendorBaseRegisterClass
{
	WebDriver driver;
	@Given("User navigates to Vendor Registration page")
	public void user_navigates_to_Vendor_Registration_page()
	{
		driver = DriversFactory.getDriver();
		driver.findElement(By.xpath("//i[@title='Vendors']")).click();
		System.out.println("User got navigated to Vendor registration page");
	}

	@When("User clicks on the Add user button popup window opens")
	public void User_clicks_on_the_Add_user_button_popup_window_opens() throws InterruptedException 
	{
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@class='btn btn-primary']")).click();
		//driver.findElement(By.id("//select[@id='roleselection']")).click();		
		System.out.println("A dropdown window appeared");
	}

	@And("User selects the Vendor Individual from the Dropdown list of select user Role")
	public void user_selects_the_vendor_individual_from_the_dropdown_list_of_select_user_Role() 
	{
		driver.findElement(By.xpath("//option[@value='3']")).click();
		driver.findElement(By.xpath("//div[@class='taskName']")).click();
		System.out.println("User selected the Vendor Individual from the dropdown role list");
	}	

	@When("User enters the details into the following fields")
	public void user_enters_the_details_into_the_following_fields(DataTable dataTable) throws InterruptedException 
	{
		Map<String, String>	dataMap = dataTable.asMap(String.class,String.class);

		driver.findElement(By.id("Name")).sendKeys(dataMap.get("Name"));
		driver.findElement(By.xpath("//form[@id='form1']//input[@id='Phone']")).sendKeys(dataMap.get("Phone Number"));
		driver.findElement(By.xpath("//form[@id='form1']//input[@id='Email']")).sendKeys(dataMap.get("Email"));
		driver.findElement(By.xpath("//form[@id='form1']//input[@id='NewPassword']")).sendKeys(dataMap.get("New Password"));
		driver.findElement(By.xpath("//form[@id='form1']//input[@id='ConfirmPassword']")).sendKeys(dataMap.get("Confirm Password"));
		Thread.sleep(2000);

		driver.findElement(By.xpath("//form[@id='form1']//span[@aria-labelledby='select2-CountryID-container']")).click();
		selectDropdownByXPath(driver, "//li[@class='select2-results__option' and text()='"+dataMap.get("Country")+"']");	
		System.out.println("country selected");

		Thread.sleep(2000);

		driver.findElement(By.xpath("//form[@id='form1']//span[@aria-labelledby='select2-StateID-container']")).click();
		selectDropdownByXPath(driver, "//li[@class='select2-results__option' and text()='"+dataMap.get("State")+"']");	
		System.out.println("state selected");

		Thread.sleep(2000);
		driver.findElement(By.xpath("//form[@id='form1']//span[@aria-labelledby='select2-CityID-container']")).click();
		selectDropdownByXPath(driver, "//li[@class='select2-results__option' and text()='"+dataMap.get("City")+"']");	
		System.out.println("City selected");

	}


	@And("User clicks on the Register button")
	public void user_clicks_on_the_register_button() throws InterruptedException 
	{
		Thread.sleep(1000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
		driver.findElement(By.xpath("//form[@id='form1']//input[@type='submit']")).click();
		System.out.println("User clicked on Register button");
	}

	@Then("Vendor Individual Account should get created successfully")
	public void vendor_Individual_account_should_get_created_successfully() throws InterruptedException 
	{
		Errormessage();	
		Thread.sleep(3000);
	}


	@Then("User should get the proper warning message informing about a duplicate email address")
	public void user_should_get_the_proper_warning_message_informing_about_a_duplicate_email_address() 
	{
		Assert.assertTrue(driver.findElement(By.id("Email-error")).getText().contains("Email already registerd."));
		WebElement emailField = driver.findElement(By.xpath("//form[@id='form1']//input[@id='Email']")); // Example:Locate by ID
		System.out.println("The Duplicate Email id is : " + emailField.getAttribute("value"));
	}

	@When("User dont enter the details into any of the fields")
	public void user_dont_enter_the_details_into_any_of_the_fields()
	{
		System.out.println("User has kept all the fields blank");
	}

	@Then("Admin User should get the proper warning message to fill all mandatory fields")
	public void admin_user_should_get_the_proper_warning_message_to_fill_all_mandatory_fields() 
	{

		checkFieldErrorMessage(driver, "Name", "Please enter name");
		checkFieldErrorMessage(driver, "Phone", "Please enter phone number.");
		checkFieldErrorMessage(driver, "Email", "Please enter email address.");
		checkFieldErrorMessage(driver, "NewPassword", "Please enter new password.");
		checkFieldErrorMessage(driver, "ConfirmPassword", "Please enter confirm password.");
		checkFieldErrorMessage(driver, "CountryID", "Please Select Country");
		checkFieldErrorMessage(driver, "CityID", "Please Select City");

		System.out.println("Warning message pop ups to fill all the mandatory fields");

	}
}


