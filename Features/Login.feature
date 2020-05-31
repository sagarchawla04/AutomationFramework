Feature: Login to application

  @regression
  Scenario: Successful Login with Valid Credentials
    Given User Launch Chrome browser
    When User opens URL "http://admin-demo.nopcommerce.com/login"
    And user enters the login credentials from "test.json" file
    And Click on Login
    Then Page Title should be "Dashboard / nopCommerce administration"
    When User click on Log out link
    Then Page Title should be "Your store. Login"
    And close browser

  @regression
  Scenario Outline: Login Data Driven
    Given User Launch Chrome browser
    When User opens URL "http://admin-demo.nopcommerce.com/login"
    And User enters Email as "<email>" and Password as "<password>"
    And Click on Login
    Then Page Title should be "Dashboard / nopCommerce administration"
    When User click on Log out link
    Then Page Title should be "Your store. Login"
    And close browser

    Examples:
    # passwords in below data have been encrypted
      | email                | password     |
      | admin@yourstore.com  | YWRtaW4=     |
      | admin1@yourstore.com | YWRtaW4xMjM= |