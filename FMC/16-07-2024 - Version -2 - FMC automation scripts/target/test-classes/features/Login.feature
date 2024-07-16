@Loginfunctionality
Feature: Login functionality
Registered User should be able to login to access Account details 

Background:
Given User navigates to Login page

@Login
Scenario: Login with valid credentials
When User enters valid email address "nagaraju.adoni@effiasoft.com"	into email field
And Enters valid password "Hmastest" into password field
And Clicks on Login button
Then User should get verification code sent to registered email address
When User enters the verification code
And Clicks on submit button
Then User should be able to login successfully


#
#@invalid
#Scenario: Login with invalid credentials
#When User enters invalid email address "raju@gmail.com" into email field
#And Enters invalid password "Ad@12345678" into password field
#And Clicks on Login button
#Then User should get a proper warning message
#
#
#@invalidpassword
#Scenario: Login with valid email address and invalid password
#When User enters valid email address "nagaraju.adoni@effiasoft.com"	into email field
#And Enters invalid password "12345678" into password field
#And Clicks on Login button
#Then User should get a proper warning message
#
#
#@invalidemailaddress
#Scenario: Login with invalid email address and valid password
#When User enters invalid email address "raju@gmail.com" into email field
#And Enters valid password "Ad@12345678" into password field
#And Clicks on Login button
#Then User should get a proper warning message
#
#
#@withoutcredentials
#Scenario: Login without providing any credentials
#When User dont enter any credentials
#And Clicks on Login button
#Then User should get the proper warning message