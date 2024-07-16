@CustomerRegistration
Feature: Customer Account Registration
 
Background:
Given User navigates to Login page
When User enters valid email address "nagaraju.adoni@effiasoft.com"	into email field
And Enters valid password "Hmastest" into password field
And Clicks on Login button
Then User should get verification code sent to registered email address
When User enters the verification code
And Clicks on submit button
Then User should be able to login successfully

#
#Scenario: Register with mandatory or all fields
#When User clicks on Add user button popup window opens
#When User enters the details into following fields
#|Company Name          |MARINA Marine					            |
#|Contact Name          |Raju	  												    |
#|Phone Number          |9877667788 												|
#|Email                 |soigrubreipeupeu-765@yopmail.com   |
#|New Password          |HmasTest                           |
#|Confirm Password      |HmasTest                           |
#And User clicks on Register button
#Then Customer Account should get created successfully 
#
#
#
#Scenario: Register with duplicate email address
#When User clicks on Add user button popup window opens
#When User enters the details into following fields
#|Company Name          |MARINA Marine                      |
#|Contact Name          |Raju					                      |
#|Phone Number          |9877667788                         |
#|Email                 |soigrubreipeupeu-765@yopmail.com   |
#|New Password          |HmasTest                           |
#|Confirm Password      |HmasTest                           |
#And User clicks on Register button
#Then User should get the proper warning message informing about the duplicate email address



Scenario: Register without providing any fields
When User clicks on Add user button popup window opens
And User dont enter details into any of the fields
And User clicks on Register button
Then Admin User should get a proper warning message to fill all mandatory fields
