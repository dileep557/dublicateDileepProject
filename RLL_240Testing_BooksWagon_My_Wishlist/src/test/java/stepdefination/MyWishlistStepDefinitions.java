package stepdefination;

import static org.junit.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pages.RLL_240Testing_BooksWagon.LoginPage;
import com.pages.RLL_240Testing_BooksWagon.MyWishlistPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class MyWishlistStepDefinitions {
	       WebDriver driver;
	       MyWishlistPage myWishlistPage;
	       LoginPage loginPage;
	       By noItemsMessage = By.xpath("//div[@class='no-items' and contains(text(), 'You do not have any item(s) in Wishlist')]");
	       
	    

	    public MyWishlistStepDefinitions() {
	        // Initialize WebDriver and Page Object
	    	driver = new ChromeDriver();
	    	this.myWishlistPage = new MyWishlistPage(driver);
	    	//driver = new ChromeDriver();
	        loginPage = new LoginPage(driver);
	    	
	        
	    }
	    @Given("user launches the BooksWagon website for the wishlist")
	    public void user_launches_the_bookswagon_website_for_the_wishlist() {
	        loginPage.launchBooksWagon();
	    }


	    
	    @Given("user logs in with username {string} and password {string}")
	    public void user_logs_in_with_username_and_password(String username, String password) throws InterruptedException {
	        loginPage.login(username, password);
	    }
	    
	    @Given("the user is click on the Wishlist icon")
	    public void the_user_is_click_on_the_Wishlist_icon() {
	        // Logic to navigate to the wishlist page if necessary
	        loginPage.clickMyWishlist();
	    }

	    @Then("the user should see the header {string}")
	    public void the_user_should_see_the_header(String expectedHeader) {
	        // Call the method to verify if the user is on the Wishlist page
	        boolean isOnWishlistPage = myWishlistPage.verifyOnWishlistPage();
	        
	        // If on the Wishlist page, get the actual header text to compare
	        if (isOnWishlistPage) {
	            String actualHeader = myWishlistPage.getWishlistHeaderText(); // You will need to implement this method
	            assertEquals("Header does not match.", expectedHeader, actualHeader);
	        } else {
	            fail("User is not on the Wishlist page.");
	        }
	    }



	    @When("the user clicks the add button")
	    public void the_user_clicks_the_add_button() throws InterruptedException {
	        myWishlistPage.clickAddButton();
	    }

	    @Then("the quantity should increase")
	    public void the_quantity_should_increase() throws InterruptedException {
	        // Locate the quantity input element
	        WebElement qtyElement = driver.findElement(By.xpath("//input[@id='ctl00_phBody_WishList_lvWishList_ctrl0_txtqty']"));
	        
	        // Extract the initial quantity value and convert it to an integer
	        int initialQty = Integer.parseInt(qtyElement.getAttribute("value"));
	        
	        // Simulate the action that increases the quantity (e.g., clicking the add button)
	        myWishlistPage.clickAddButton(); // Ensure this method is available in your MyWishlistPage class

	        // Extract the updated quantity value after the action
	        qtyElement = driver.findElement(By.xpath("//input[@id='ctl00_phBody_WishList_lvWishList_ctrl0_txtqty']")); // Re-locate the element
	        int updatedQty = Integer.parseInt(qtyElement.getAttribute("value"));

	        // Verify that the updated quantity is greater than the initial quantity
	      
	        assertTrue(updatedQty > initialQty, "Quantity did not increase. Initial: " + initialQty + ", Updated: " + updatedQty);

	    }



	    @When("the user clicks the subtract button")
	    public void the_user_clicks_the_subtract_button() throws InterruptedException {
	        myWishlistPage.clickSubButton();
	    }

	    @Then("the quantity should decrease")
	    public void the_quantity_should_decrease() throws InterruptedException {
	    	 WebElement qtyElement = driver.findElement(By.xpath("//input[@id='ctl00_phBody_WishList_lvWishList_ctrl0_txtqty']"));
		        
		        // Extract the initial quantity value and convert it to an integer
		        int initialQty = Integer.parseInt(qtyElement.getAttribute("value"));
		        
		        // Simulate the action that increases the quantity (e.g., clicking the add button)
		        myWishlistPage.clickSubButton(); // Ensure this method is available in your MyWishlistPage class

		        // Extract the updated quantity value after the action
		        qtyElement = driver.findElement(By.xpath("//input[@id='ctl00_phBody_WishList_lvWishList_ctrl0_txtqty']")); // Re-locate the element
		        int updatedQty = Integer.parseInt(qtyElement.getAttribute("value"));

		        // Verify that the updated quantity is greater than the initial quantity
		      
		        assertTrue(updatedQty < initialQty, "Quantity did not increase. Initial: " + initialQty + ", Updated: " + updatedQty);
	        
	    }
	    
	    @When("the user selects all items")
	    public void the_user_selects_all_items() {
	        myWishlistPage.selectAllCheckbox();
	    }

	    @Then("all items should be selected")
	    public void all_items_should_be_selected() {
	    	
	    	List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@id='ctl00_phBody_WishList_lvWishList_ctrl0_chkAdd']"));

	        // Verify that all checkboxes are selected
	        for (WebElement checkbox : checkboxes) {
	           // assertTrue("Checkbox is not selected.", checkbox.isSelected());
	        	 assertTrue(checkbox.isSelected(), "Checkbox is not selected.");
	        }
	    	
	        // Logic to verify all items are selected can be placed here, if needed.
	    }
	    
	    @When("the user selects all items and clicks Add to Cart")
	    public void the_user_selects_all_items_and_clicks_add_to_cart() throws InterruptedException {
	        myWishlistPage.clickAllAddToCart();
	    }

	    @Then("the cart item count should increase")
	    public void the_cart_item_count_should_increase() {
	        // Logic to verify that the cart count has increased can be placed here, if needed.
	    	// Locate the cart count element
	        WebElement cartCountElement = driver.findElement(By.xpath("//label[@id='ctl00_lblTotalCartItems']"));

	        // Extract the updated count after adding items (assumed action is already performed)
	        int updatedCount = Integer.parseInt(cartCountElement.getText());

	        // Assert that the updated count is greater than zero
	       // assertTrue("Cart item count did not increase. Expected count to be greater than 0 but got: " + updatedCount, updatedCount > 0);
	        assertTrue(updatedCount > 0, "Cart item count did not increase. Expected count to be greater than 0 but got: " + updatedCount);

	    }


	    @When("the user removes an item")
	    public void the_user_removes_an_item() {
	        myWishlistPage.removeItemAndVerify();
	    }

	    @Then("the user should see the message {string}")
	    public void the_user_should_see_the_message(String expectedMessage) {
	        // Implement logic to verify the message after item removal if needed
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.visibilityOfElementLocated(noItemsMessage));

	        // Verify that the no items message is displayed
	        WebElement noItemsElement = driver.findElement(By.xpath("//div[@class='no-items' and contains(text(), 'You do not have any item(s) in Wishlist')]"));
	        
	        assertTrue(noItemsElement.isDisplayed(), "No items message is not displayed.");

	        String actualText = noItemsElement.getText();
	        String expectedText = "You do not have any item(s) in Wishlist";
	        
	        // Assert that the actual text matches the expected text
	        assertEquals("Message displayed is incorrect.", expectedText, actualText);
	    }
     

	   
}

	   
//	
//	    
//	
//
//}
