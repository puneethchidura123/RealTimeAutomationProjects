@tag
Feature: error validation
  I want to use this template for my feature file

  @ErrorValidation @Regression
  Scenario Outline: Title of your scenario outline
    Given i landed on ecommerce home page
    When i loggedin with <username>  and <password>
    Then "Incorrect email or password." message is displayed

     Examples:
|username|password|
|puneethchdura123@gmail.com|iloeududE@1|