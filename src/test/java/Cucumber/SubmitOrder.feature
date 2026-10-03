
@tag
Feature: Purchse the order from Ecomerce website
  I want to use this template for my feature file

Background:
Given I landed on "https://rahulshettyacademy.com/client " Ecomerce Page 


  @Regression 
  Scenario Outline: Positive Test of Submitting the order
    Given Logged in with usename  <name> and password <password>
    When I add product <productName> to cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage 
    
    Examples: 
      | name                    | password       | productName | url |
      | manish.singh@irdeto.com |     Manish@123 | ZARA COAT 3 | https://rahulshettyacademy.com/client |
