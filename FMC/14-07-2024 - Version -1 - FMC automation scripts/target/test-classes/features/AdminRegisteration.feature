@AdminRegister
Feature: Admin User Registration

Background:
Scenario: Given User navigates to Login page

Given User navigates to Login page
When User enters valid email address "nagaraju.adoni@effiasoft.com"	into email field
And Enters valid password "Hmastest" into password field
And Clicks on Login button
Then User should get verification code sent to registered email address
When User enters the verification code
And Clicks on submit button
Then User should be able to login successfully


Scenario: Register with mandatory or all fields
When User clicks on Add user button popup opens
When User enters the details into below fields
|UserID          |Shailaja.mudatanapalli@effiasoft.com	  |
|Name            |shailaja									  						|
|Mobile          |9877667788  														|
|Password        |Hmastest   															|
|Confirm Password|Hmastest    														|
And User selects Activation date "dd-mm-yyyy" from calendar popup
And User checks Enabled button
And User checks Admin User button
And User clicks on Save button
Then User Account should get created successfully 



Scenario: Register with duplicate email address
When User clicks on Add user button popup opens
When User enters the details into below fields
|UserID           |Rajutsr@gmail.com					|
|Name             |Raju					              |
|Mobile           |9877667788  								|
|Password         |HmasTest    								|
|Confirm Password |HmasTest   							  |
And User selects Activation date "dd-mm-yyyy" from calendar popup
And User checks Enabled button
And User checks Admin User button
And User clicks on Save button
Then User should get a proper warning message informing about the duplicate email address



Scenario: Register without providing any fields
When User clicks on Add user button popup opens
And User dont enter details into any fields
And User clicks on Save button
Then User should get a proper warning message to fill all mandatory fields
