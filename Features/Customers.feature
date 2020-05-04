Feature: Customers 

Background: Below  are some common steps for all test cases in this feature file 
	Given User Launch Chrome browser 
	When User opens URL "http://admin-demo.nopcommerce.com/login"
	And user enters the login credentials from "test.json" file
	And Click on Login 
	Then User can view Dashboad 
	
@regression
Scenario: Add a new Customer 
	When User click on customers Menu 
	And click on customers Menu Item 
	And click on Add new button 
	Then User can view Add new customer page 
	When User enter customer info 
	And click on Save button 
	Then User can view confirmation message
	And close browser 

@regression
Scenario: Search Customer by EMailID 
	When User click on customers Menu 
	And click on customers Menu Item 
	And Enter customer EMail 
	When Click on search button 
	Then User should found Email in the Search table 
	And close browser 
	
@regression
Scenario: Search Customer by Name 
	When User click on customers Menu 
	And click on customers Menu Item 
	And Enter customer FirstName 
	And Enter customer LastName 
	When Click on search button 
	Then User should found Name in the Search table 
	And close browser 