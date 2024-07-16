@VendorIndividualRegistration
Feature: Vendor Account Registration
 
Background:
Given User navigates to Login page
When User enters valid email address "nagaraju.adoni@effiasoft.com"	into email field
And Enters valid password "Hmastest" into password field
And Clicks on Login button
Then User should get verification code sent to registered email address
When User enters the verification code
And Clicks on submit button
Then User should be able to login successfully
 

Scenario: Register with mandatory or all fields
When User clicks on the Add user button popup window opens
And User selects the Vendor Individual from the Dropdown list of select user Role
When User enters the details into the following fields
|Name       				   |Rolls Royce Marine		             |
|Phone Number			     |9877667788	  										 |
|Email			           |preimmekofuti-3338@yopmail.com		 |
|New Password          |HmasTest                           |
|Confirm Password      |HmasTest                           |
|Country							 |India													     |
|State								 |Telangana												   |
|City								   |Hyderabad													 |
And User clicks on the Register button
Then Vendor Individual Account should get created successfully 



Scenario: Register with duplicate email address
When User clicks on the Add user button popup window opens
And User selects the Vendor Individual from the Dropdown list of select user Role
When User enters the details into the following fields
|Name       				   |Rolls Royce Marine		             |
|Phone Number			     |9877667788	  										 |
|Email			           |preimmekofuti-3338@yopmail.com		 |
|New Password          |HmasTest                           |
|Confirm Password      |HmasTest                           |
|Country							 |India													     |
|State								 |Telangana												   |
|City								   |Hyderabad													 |
And User clicks on the Register button
Then User should get the proper warning message informing about a duplicate email address



Scenario: Register without providing any fields
When User clicks on the Add user button popup window opens
And User selects the Vendor Individual from the Dropdown list of select user Role
And User dont enter the details into any of the fields
And User clicks on the Register button
Then Admin User should get the proper warning message to fill all mandatory fields
