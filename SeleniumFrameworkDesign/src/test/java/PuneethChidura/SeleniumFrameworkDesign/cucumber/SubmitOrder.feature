Feature: Purchase Order from ecommerce

Background:
Given i landed on ecommerce home page

@Regression
 Scenario Outline: positive test of purchasing the order
 Given i loggedin with <username>  and <password>
 When i add the <product name> to cart
 And chcekout for <product name> and submit order
 Then verify if confirmation message "THANKYOU FOR THE ORDER." is displayed
 
 
 Examples:
|username|password|product name|
|puneethchidura123@gmail.com|iloveududE@1|ZARA COAT 3|

