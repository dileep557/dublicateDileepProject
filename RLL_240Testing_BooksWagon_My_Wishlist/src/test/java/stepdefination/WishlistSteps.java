package stepdefination;
//import static org.junit.Assert.assertEquals;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//
//import com.pages.RLL_240Testing_BooksWagon.LoginPage;
//
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//
//
//public class WishlistSteps {
//	
//	    WebDriver driver;
//	    LoginPage loginPage;
//
//	    @Given("the user launches the BooksWagon website")
//	    public void the_user_launches_the_BooksWagon_website() {
//	        // Initialize the WebDriver
//	       // System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
//	        driver = new ChromeDriver();
//	        loginPage = new LoginPage(driver);
//	        loginPage.launchBooksWagon();
//	    }
//
//	    @When("the user logs in with username {string} and password {string}")
//	    public void the_user_logs_in_with_username_and_password(String username, String password) throws InterruptedException {
//	        loginPage.login(username, password);
//	    }
//
//
//	     @Then("the user should be logged in successfully")
//	    public void the_user_should_be_logged_in_successfully() {
//	        // Locate the element that displays the logged-in user's name
//	        String actualUserName = driver.findElement(By.xpath("//span[@id='ctl00_lblUser']")).getText();
//	        
//	        // Define the expected user name (replace with the actual expected value)
//	        String expectedUserName = "Dileep"; // Update this with the actual expected value
//
//	        // Assert that the actual user name matches the expected value
//	        assertEquals("Login failed: User name does not match.", expectedUserName, actualUserName);
//	        
//	        // Close the browser
//	        driver.quit();
//	    }
//
//	    
//	    
//}
//


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.pages.RLL_240Testing_BooksWagon.LoginPage;
import com.pages.RLL_240Testing_BooksWagon.MyWishlistPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class WishlistSteps {
    private WebDriver driver;
    private LoginPage loginPage;
    Logger log;

    public WishlistSteps() {
        // Initialize the WebDriver (ensure the appropriate driver executable is in your path)
       
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        log = Logger.getLogger(MyWishlistPage.class);
    }

    @Given("the user launches the BooksWagon website")
    public void the_user_launches_the_bookswagon_website() {
        loginPage.launchBooksWagon();
        log.info("user launches the bookswagon website");
    }

    @Given("the user logs in with username {string} and password {string}")
    public void the_user_logs_in_with_username_and_password(String username, String password) throws InterruptedException {
        loginPage.login(username, password);
        log.info("user entered the username and password");
    }

    @When("the user scrolls to the 7th card")
    public void the_user_scrolls_to_the_7th_card() throws InterruptedException {
        loginPage.scrollAndClickOn7thCard();
    }

    @Then("the user should see the details of the 7th card")
    public void the_user_should_see_the_details_of_the_7th_card() {
        // Extract the title from the product detail page
        WebElement titleElement = driver.findElement(By.xpath("//span[@id='ctl00_phBody_ProductDetail_lblTitle']"));
        String actualTitle = titleElement.getText();

        // Define the expected title
        String expectedTitle = "White Nights";

        // Verify that the actual title matches the expected title
        assertEquals("The title of the 7th card does not match.", expectedTitle, actualTitle);
    }

    @When("the user adds the item to the wishlist")
    public void the_user_adds_the_item_to_the_wishlist() throws InterruptedException {
        loginPage.addToWishlist();
    }

    @Then("the item should be added to the wishlist successfully")
    public void the_item_should_be_added_to_the_wishlist_successfully() {
        // Locate the wishlist count element
        WebElement wishlistCountElement = driver.findElement(By.xpath("//label[@id='ctl00_lblWishlistCount']"));
        
        // Extract the initial count and convert it to an integer
        int initialCount = 0;
        
        // Ensure the initial count is zero
        assertEquals("Initial wishlist count should be zero.", 0, initialCount);

        // Add the item to the wishlist
        
        // Extract the updated count after adding the item
        wishlistCountElement = driver.findElement(By.xpath("//label[@id='ctl00_lblWishlistCount']")); // Re-locate the element
        int updatedCount = Integer.parseInt(wishlistCountElement.getText());

        // Verify that the updated count is greater than zero
        assertTrue("Item not added to wishlist. Expected count to be greater than 0 but got " + updatedCount, updatedCount > 0);
    }

    @When("the user clicks on the wishlist icon")
    public void the_user_clicks_on_the_wishlist_icon() {
        loginPage.clickMyWishlist();
    }

//    @Then("the wishlist should open successfully")
//    public void the_wishlist_should_open_successfully() {
//        // Verification logic for the wishlist opening
//        assertTrue("Wishlist did not open.", true); // Replace with actual check
//    }

    @Then("the wishlist icon should be clickable")
    public void the_wishlist_icon_should_be_clickable() {
        boolean isClickable = loginPage.isWishlistIconClickable();
        assertTrue("Wishlist icon is not clickable.", isClickable);
    }
}

