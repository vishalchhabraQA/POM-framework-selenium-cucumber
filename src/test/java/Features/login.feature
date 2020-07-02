Feature: Application Login

#Scenario: Positive test validating Login
#Given initialize the browser with chrome
#Given navigate to "http://qaclickacademy.com/" site
#Given click on login link to navigate to sign in page
#When User enters "username" and "password" and logs in
#Then verify that user logged in to application successfully

#Parameterizing selenium tests with Cucumber 

Scenario Outline: Positive test validating Login
Given initialize the browser with chrome
Given navigate to "http://qaclickacademy.com/" site
Given click on login link to navigate to sign in page
When User enters <username> and <password> and logs in
Then verify that user logged in to application successfully

Examples:
|	username	|	password	|
|	a			|	11			|
|	b			|	22			|
|	c			|	33			|
|	d			|	44			|