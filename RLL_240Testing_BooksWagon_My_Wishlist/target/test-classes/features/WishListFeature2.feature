#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
Feature: My Wishlist Functionality
     As a user,
     I want to manage items in my wishlist,
     So that I can easily add, remove, and adjust quantities of items before purchasing
    
     Background:
     Given  user launches the BooksWagon website for the wishlist 
     And user logs in with username "9198473884" and password "Dkyp12@@"
     

     Scenario: Verify user is on the Wishlist page
     Given the user is click on the Wishlist icon
     Then the user should see the header "My Wishlist"
     When the user clicks the add button
     Then the quantity should increase

    
     When the user clicks the subtract button
     Then the quantity should decrease
     When the user selects all items
     Then all items should be selected
     When the user selects all items and clicks Add to Cart
     Then the cart item count should increase
     When the user removes an item
     Then the user should see the message "You do not have any item(s) in Wishlist"

  
    

 #
   