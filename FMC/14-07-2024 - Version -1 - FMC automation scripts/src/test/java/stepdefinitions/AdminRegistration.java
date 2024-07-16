package stepdefinitions;


import java.time.Duration;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import driverfactory.DriversFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;


public class AdminRegistration extends AdminBaseRegisterClass
{
	WebDriver driver;

	@Given("User navigates to Users Registration page")
	public void user_navigates_to_users_registration_page() 
	{
		driver = DriversFactory.getDriver();
		driver.findElement(By.xpath("//i[@title='Users']")).click();
		System.out.println("User got navigated to Admin Account registeration page");
	}

	@When("User clicks on Add user button popup opens")
	public void user_clicks_on_add_user_button_popup_opens() 
	{
		driver.findElement(By.id("btn_modalservicetype")).click();
		//	System.out.println("User clicks on Add user button");
	}

	@When("User enters the details into below fields")
	public void user_enters_the_details_into_below_fields(DataTable dataTable)
	{
		Map<String, String>	dataMap = dataTable.asMap(String.class,String.class);

		driver.findElement(By.id("UserID")).sendKeys(dataMap.get("UserID"));
		driver.findElement(By.id("Name")).sendKeys(dataMap.get("Name"));
		driver.findElement(By.id("Mobile")).sendKeys(dataMap.get("Mobile"));
		driver.findElement(By.id("NewPassword")).sendKeys(dataMap.get("Password"));
		driver.findElement(By.id("ConfirmPassword")).sendKeys(dataMap.get("Confirm Password"));

	}

	@And("User selects Activation date {string} from calendar popup")
	public void user_selects_activation_date_from_calendar_popup(String date) throws InterruptedException
	{
		driver.findElement(By.id("ActivationDate")).sendKeys("27-06-2024");
		Thread.sleep(1000);

		// Print or use the formatted date
		System.out.println("Current Date :  " +driver.findElement(By.id("ActivationDate")).getAttribute("value"));
		Thread.sleep(1000);

	}

	@And("User checks Enabled button")
	public void user_checks_enabled_button() 
	{
		boolean ischecked=	driver.findElement(By.id("Enabled")).isSelected();
		if(!ischecked)
			driver.findElement(By.id("Enabled")).click();
		//	System.out.println("User checks Enabled button");
	}

	@And("User checks Admin User button")
	public void user_checks_admin_user_button()
	{
		boolean ischecked=	driver.findElement(By.id("AdminUser")).isSelected();
		if(!ischecked)
			driver.findElement(By.id("AdminUser")).click();
		//	System.out.println("User checks Admin user button");
	}

	@And("User clicks on Save button")
	public void user_clicks_on_save_button() throws InterruptedException 
	{
		Thread.sleep(1000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
		driver.findElement(By.id("btn_addUser")).click();
		System.out.println("User clicks on save button");
	}

	@Then("User Account should get created successfully")
	public void User_account_should_get_created_successfully() throws InterruptedException 
	{
		Errormessage();	
		Thread.sleep(1000);
	}


	@Then("User should get a proper warning message informing about the duplicate email address")
	public void user_should_get_a_proper_warning_message_informing_about_the_duplicate_email_address()
	{
		//		Assert.assertTrue(driver.findElement(By.id("UserID-error")).getText().contains("User already registered."));
		boolean isUserIDErrorDisplayed = driver.findElements(By.id("UserID-error")).size() > 0 
				&& driver.findElement(By.id("UserID-error")).isDisplayed() ? true : false;
		if (isUserIDErrorDisplayed) 
		{
			WebElement userIDField = driver.findElement(By.id("UserID"));
			String duplicateEmailId = userIDField.getAttribute("value");

			System.out.println("The Duplicate id is :" +duplicateEmailId);
			System.out.println("You cannot create the user account with this duplicate email id");
		}
		else
		{
			System.out.println("No Duplicate Email ID error message found");
		}
	}

	@When("User dont enter details into any fields")
	public void user_dont_enter_details_into_any_fields() throws InterruptedException 
	{
		System.out.println("User has kept all the fields blank");
		Thread.sleep(3000);
	}

	@Then("User should get a proper warning message to fill all mandatory fields")
	public void user_should_get_a_proper_warning_message_to_fill_all_mandatory_fields() 
	{
		checkFieldErrorMessage("UserID", "Please enter User ID.");
		checkFieldErrorMessage("Name", "Please enter name");
        checkFieldErrorMessage("Mobile", "Please enter mobile");
        checkFieldErrorMessage("NewPassword", "Please enter new password.");
        checkFieldErrorMessage("ConfirmPassword", "Please enter confirm password.");
        checkFieldErrorMessage("ActivationDate", "Please select activation date.");
	}

}
