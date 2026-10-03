
@tag
Feature: Error validation 
  I want to use this template for my feature file

 

  @tag2
  Scenario Outline: Verify login with invalid credentials
    Given I landed on "https://rahulshettyacademy.com/client " Ecomerce Page 
    When Logged in with usename  <name> and password <password>
    Then "Incorrect email or password." message is displayed 

    Examples: 
      | name                    | password       | 
      | manish.singh@irdeto.com |     Manish@12 |
