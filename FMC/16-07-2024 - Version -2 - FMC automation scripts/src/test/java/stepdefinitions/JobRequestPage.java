package stepdefinitions;

import java.time.Duration;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import driverfactory.DriversFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

public class JobRequestPage extends JobRequestBaseClass
{
	WebDriver driver;

	@Given("User navigates to Job Requests list page")
	public void User_navigates_to_Job_Requests_list_page() 
	{
		driver = DriversFactory.getDriver();
		driver.findElement(By.xpath("//i[@class='fa fa-anchor']")).click();
		driver.findElement(By.xpath("//a[@id='tsk_ADM_TaskRequests']")).click();;
		System.out.println("User got navigated to Job list page");
	}

	@When("User clicks on Add Job Request button in Job list page")
	public void User_clicks_on_Add_Job_Request_button_in_Job_list_page() 
	{
		driver.findElement(By.xpath("//a[@class='btn btn-primary']")).click();
		//	System.out.println("User clicks on Add job request button");
	}

	@Then("A New Job Request window opens with multiple fields to enter")
	public void A_New_Job_Request_window_opens_with_multiple_fields_to_enter()
	{
		System.out.println("User navigates to a New Job Request page");
	}

	@When("User selects the options from the dropdown list for the following fields")
	public void User_selects_the_options_from_the_dropdown_list_for_the_following_fields(DataTable dataTable) throws InterruptedException
	{
		Map<String, String>	dataMap = dataTable.asMap(String.class,String.class);

		String customerName = dataMap.get("Select Customer");		
		driver.findElement(By.xpath("//button[@id='drpdown_Customers']")).click();
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1000));
		Thread.sleep(2000);
		selectDropdownByXPath(driver, "//ul[@id='ui-id-1']//li[@class='ui-menu-item']//div[text() = '"+ customerName + "']");
		System.out.println("Customer Name selected");

		driver.findElement(By.xpath("//span[@class='select2-selection__arrow']")).click();
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));
		Thread.sleep(2000);
		selectDropdownByXPath(driver, "//ul[@class='select2-results__options']//li[@class='select2-results__option' and text()='"+dataMap.get("Service sub Type")+"']");
		System.out.println("serviceSubType selected");

		String VesselType = dataMap.get("Vessel Type");		
		driver.findElement(By.xpath("//button[@id='drpdown_VesselTypes']//span[@class='fa fa-caret-down']")).click();
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1000));
		Thread.sleep(2000);
		selectDropdownByXPath(driver, "//ul[@id='ui-id-2']//li[@class='ui-menu-item']//div[text() = '"+ VesselType + "']");
		System.out.println("VesselType selected");

		String PortName = dataMap.get("Port Name");		
		driver.findElement(By.xpath("//input[@name='Ports.TextField']")).click();
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1000));
		Thread.sleep(2000);
		selectDropdownByXPath(driver, "//ul[@id='ui-id-3']//li[@class='ui-menu-item']//div[text() = ' "+PortName+"']");
		System.out.println("PortName selected");
	}

	@And("Enters the details for the following fields")
	public void Enters_the_details_for_the_following_fieldss(DataTable dataTable) throws InterruptedException
	{
		Map<String, String>	dataMap = dataTable.asMap(String.class,String.class);
			  
		driver.findElement(By.xpath("//input[@id='VesselName']")).sendKeys(dataMap.get("Vessel Name"));
		driver.findElement(By.xpath("//input[@id='IMONo']")).sendKeys(dataMap.get("IMO Number"));
		driver.findElement(By.id("Area")).sendKeys(dataMap.get("Berth/Anchorage/Location"));
		Thread.sleep(2000);
//		driver.findElement(By.id("PreferredAttendanceDate")).click();
//		driver.findElement(By.id("PreferredAttendanceDate")).sendKeys(dataMap.get("PreferredAttendanceDate"));	
//		//driver.findElement(By.id("PreferredAttendanceDate"))
	}
	
	@And("Selects the Preferred Attendance Date, ETA, ETB and ETD from the Calendar Datepicker {string}")
	public void Selects_the_Preferred_Attendance_Date_ETA_ETB_and_ETD_from_the_Calendar_Datepicker(String dateRange) throws InterruptedException
	{
		if (!dateRange.matches("\\d{2}-\\d{2}-\\d{4}-\\d{2}-\\d{2}-\\d{4}")) 
		{
	        throw new IllegalArgumentException("Invalid dateRange format: " + dateRange);
	    }
		
		 String[] dates = dateRange.split("-");
		    if (dates.length != 6)
		    {
		        throw new IllegalArgumentException("Invalid dateRange format: " + dateRange);
		    }
			
	    String startDate = dates[0]+"-"+dates[1]+"-"+dates[2].trim(); // Start date
	    String endDate = dates[3]+"-"+dates[4]+"-"+dates[5].trim(); // End date
	    
	 // Debugging print statements to check values
	    System.out.println("Start Date: " + startDate);
	    System.out.println("End Date: " + endDate);
	    
	    Thread.sleep(5000);
	    
	    // Click on the PreferredAttendanceDate field to open the date picker
	    WebElement preferredAttendanceDateField = driver.findElement(By.id("PreferredAttendanceDate"));
	    preferredAttendanceDateField.click();
	    
	 // Wait for the left calendar to be visible
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Adjust timeout as needed
        WebElement leftCalendar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='drp-calendar left']")));
        
        // Select start date
        WebElement startDateElement = leftCalendar.findElement(By.xpath(".//td[@data-title='r2c0'][text() = '" + dates[0] + "']"));
        startDateElement.click();
        
	    // Assuming your date picker has two separate input fields for start and end dates
	    // Select start date
//	    WebElement startDateElement = driver.findElement(By.xpath("//div[@class='drp-calendar left']//td[@data-title='r2c0'][text() = '" + startDate + "']"));
//	    startDateElement.click();

	    // Select end date
	    WebElement endDateElement = leftCalendar.findElement(By.xpath(".//td[@data-title='r4c1'][text() = '" + dates[3] + "']"));
	    endDateElement.click();

	    // Click on the apply button in the date picker
	    WebElement applyButton = driver.findElement(By.xpath("//div[@class='daterangepicker ltr show-calendar opensright']//button[@class='applyBtn btn btn-sm btn-primary']"));
	    applyButton.click();

	    // Optionally wait for the date picker to close (depending on how your application behaves)
	    Thread.sleep(2000);  // Adjust as needed
	    
	    // Retrieve and print the selected date (if needed)
	    String selectedDate = preferredAttendanceDateField.getAttribute("value");
	    System.out.println("Preferred Date: " + selectedDate);
	}

	@And("Clicks on Add Port button")
	public void Clicks_on_Add_Port_button() throws InterruptedException 
	{
		Thread.sleep(1000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(300));
		driver.findElement(By.id("addDataToTable")).click();
		Thread.sleep(1000);
		System.out.println("User clicks on Add port button");
	}

	@When("User adds the Tasks from Tasks Type dropdown")
	public void User_adds_the_Tasks_from_Tasks_Type_dropdown() throws InterruptedException 
	{	
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(500));
		driver.findElement(By.xpath("//div[@class='ss-values']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[@class='ss-option'][text() ='Maritime surveillance (Mrtsr)']")).click();
		driver.findElement(By.xpath("//div[@class='ss-values']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@class='ss-option'][text() ='Passenger transport (psgnr)']")).click();
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1000));
		Thread.sleep(5000);
		System.out.println("Task type selected");
			
	}
	
	@And("Clicks on Add task type to add new task to the Job Request")
	public void Clicks_on_Add_task_type_to_add_new_task_to_the_Job_Request(DataTable dataTable) throws InterruptedException 
	{			
			
		Map<String, String>	dataMap = dataTable.asMap(String.class,String.class);
		
		driver.findElement(By.id("btn_modaltask")).click();			  
		driver.findElement(By.id("Task_Name")).sendKeys(dataMap.get("Task Name"));
		driver.findElement(By.id("Task_Code")).sendKeys(dataMap.get("Task Code"));
		driver.findElement(By.id("Task_Description")).sendKeys(dataMap.get("Description"));
		
//		String descriptionfield =  "Hi im new to this application"; 
//		driver.findElement(By.id("Task_Description")).sendKeys(descriptionfield);
		driver.findElement(By.xpath("//form[@id='Form_AddTask']//div[@class='ss-values']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@class='ss-option'][text() ='Naval Architecture']")).click();	

		Thread.sleep(1000);
		driver.findElement(By.id("btn_tasksave")).click();
		System.out.println("User clicks on save button and a New task has been added to the list");
		Thread.sleep(5000);
	}
	
	
	
	@And("Uploads the Document or zip folder from local system")
	public void Uploads_the_Document_or_zip_folder_from_local_system() throws InterruptedException 
	{	
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1000));
		WebElement fileInput = driver.findElement(By.id("file"));  // Replace with your file input ID

        // Specify the file path to upload
        String filePath = "D:\\HMA-FMC\\file to be opened.zip";  // Replace with your file path

        // Enter the file path into the file input
        fileInput.sendKeys(filePath);
        Thread.sleep(3000);
		
	}

	@And("Enter any details in the text box provided")
	public void Enter_any_details_in_the_text_box_provided() throws InterruptedException 
	{	
		//Thread.sleep(5000);
		String textField =  "Hi im new to this application"; 
		//driver.findElement(By.id("RequestDetails")).click();
		driver.findElement(By.id("RequestDetails")).sendKeys(textField);
		Thread.sleep(5000);
		System.out.println("The text entered in the details section is : " +textField);
	}

	@And("Clicks on Save button")
	public void Clicks_on_save_button() throws InterruptedException 
	{
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		System.out.println("User clicks on save button");
		Thread.sleep(10000);
	}

	@Then("Job Request should be created successfully")
	public void Job_Request_should_be_created_successfully() throws InterruptedException 
	{
		System.out.println("Job request has been created successfully");	
		Thread.sleep(10000);
	}


}
