@JobRequestPage
Feature: Admin JobRequest page

Background:
Given User navigates to Login page
When User enters valid email address "nagaraju.adoni@effiasoft.com"	into email field
And Enters valid password "Hmastest" into password field
And Clicks on Login button
Then User should get verification code sent to registered email address
When User enters the verification code
And Clicks on submit button
Then User should be able to login successfully



Scenario: Creating a Job Request with mandatory fields
Given User navigates to Job Requests list page
When User clicks on Add Job Request button in Job list page
Then A New Job Request window opens with multiple fields to enter
When User selects the options from the dropdown list for the following fields
|Select Customer					|Raju 								 			  |
|Service sub Type				  |Naval Architecture    			  |
|Vessel Type						  |Fishing Vessels 						  |
|Port Name							  |St Catharines (Canada) 			|
And Enters the details for the following fields
|Vessel Name											|cargo ships	 			  |
|IMO Number												|12345678							|
|Berth/Anchorage/Location     		|vizag  							|
And Selects the Preferred Attendance Date, ETA, ETB and ETD from the Calendar Datepicker "14-07-2024-29-07-2024"
And Clicks on Add Port button
When User adds the Tasks from Tasks Type dropdown
And Clicks on Add task type to add new task to the Job Request
|Task Name					|Web1 					  |
|Task Code				  |wbss1    			  |
|Description        |Hi im new to this application			  |
And Uploads the Document or zip folder from local system
And Enter any details in the text box provided
And Clicks on Save button
Then Job Request should be created successfully

