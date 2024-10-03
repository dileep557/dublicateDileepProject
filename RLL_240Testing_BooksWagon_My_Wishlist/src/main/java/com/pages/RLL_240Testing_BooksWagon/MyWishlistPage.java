package com.pages.RLL_240Testing_BooksWagon;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
//import java.util.concurrent.TimeoutException;
import org.openqa.selenium.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyWishlistPage {
	WebDriver driver;
	 
    By wishlistIcon = By.xpath("//label[@id='ctl00_lblWishlistCount']");
    By wishlistHeader = By.xpath("//h1[@class='m-0' and contains(text(), 'My Wishlist')]");
    By addButton = By.xpath("//button[@id='add']");
    By subButton = By.xpath("//button[@id='sub']");
    By totalItemsText = By.xpath("//span[@id=\"ctl00_phBody_WishList_lvWishList_lblTotalQty\"]");
    By removeButton = By.xpath("//a[@id='ctl00_phBody_WishList_lvWishList_ctrl0_ImageButton1']");
    By noItemsMessage = By.xpath("//div[@class='no-items' and contains(text(), 'You do not have any item(s) in Wishlist')]");
    By selectAllCheckbox = By.xpath("//input[@id='ctl00_phBody_WishList_lvWishList_chkAll']");
 
    public MyWishlistPage(WebDriver driver) {
        this.driver = driver;
    }
 

 
    public boolean verifyOnWishlistPage() {
        // Verify that the user is on the Wishlist page
        WebElement wishlistHeaderElement = driver.findElement(wishlistHeader);
        String actualText = wishlistHeaderElement.getText();
        String expectedText = "My Wishlist";
 
        if (actualText.equals(expectedText)) {
            System.out.println("User is on the Wishlist page.");
            return true;
        } else {
            System.out.println("User is not on the Wishlist page.");
            return false;
        }
    }
    public String getWishlistHeaderText() {
        WebElement wishlistHeaderElement = driver.findElement(By.xpath("//h1[contains(text(), 'My Wishlist')]"));
        return wishlistHeaderElement.getText();
    }


    
    
    public void clickAddButton() throws InterruptedException {
        WebElement addButtonElement = driver.findElement(addButton);
        WebElement qtyElement = driver.findElement(By.xpath("//input[@id='ctl00_phBody_WishList_lvWishList_ctrl0_txtqty']"));

        // Get the initial quantity value
        int initialQty = Integer.parseInt(qtyElement.getAttribute("value"));

        // Verify if the "+" button is displayed and enabled
        if (addButtonElement.isDisplayed() && addButtonElement.isEnabled()) {
            // Click the "+" button
            addButtonElement.click();
            System.out.println("Add button clicked successfully.");

            // Wait for the quantity to update (you might need to add an explicit wait here)
            Thread.sleep(2000); // Adjust the sleep time as necessary

            // Get the updated quantity value
            int updatedQty = Integer.parseInt(qtyElement.getAttribute("value"));

            // Verify if the quantity has increased
            if (updatedQty > initialQty) {
                System.out.println("Quantity increased successfully.");
            } else {
                System.out.println("Quantity did not increase.");
                throw new RuntimeException("Quantity did not increase after clicking the add button.");
            }
        } else {
            if (!addButtonElement.isDisplayed()) {
                System.out.println("Add button is not displayed.");
            }
            if (!addButtonElement.isEnabled()) {
                System.out.println("Add button is not clickable.");
            }
            throw new RuntimeException("Add button is either not displayed or not clickable.");
        }
    }

    
    public void clickSubButton() throws InterruptedException {
        WebElement subButtonElement = driver.findElement(subButton);
        WebElement qtyElement = driver.findElement(By.xpath("//input[@id='ctl00_phBody_WishList_lvWishList_ctrl0_txtqty']"));

        // Get the initial quantity value
        int initialQty = Integer.parseInt(qtyElement.getAttribute("value"));

        // Verify if the "-" button is displayed and enabled
        if (subButtonElement.isDisplayed() && subButtonElement.isEnabled()) {
            // Click the "-" button
            subButtonElement.click();
            System.out.println("Subtract button clicked successfully.");

            // Wait for the quantity to update (you might need to add an explicit wait here)
            Thread.sleep(2000); // Adjust the sleep time as necessary

            // Get the updated quantity value
            int updatedQty = Integer.parseInt(qtyElement.getAttribute("value"));

            // Verify if the quantity has decreased
            if (updatedQty < initialQty) {
                System.out.println("Quantity decreased successfully.");
            } else {
                System.out.println("Quantity did not decrease.");
                throw new RuntimeException("Quantity did not decrease after clicking the subtract button.");
            }
        } else {
            if (!subButtonElement.isDisplayed()) {
                System.out.println("Subtract button is not displayed.");
            }
            if (!subButtonElement.isEnabled()) {
                System.out.println("Subtract button is not clickable.");
            }
            throw new RuntimeException("Subtract button is either not displayed or not clickable.");
        }
    }

    
    public void removeItemAndVerify() {
        // Click the remove button
        WebElement removeButtonElement = driver.findElement(removeButton);
        removeButtonElement.click();
        System.out.println("Remove button clicked successfully.");
 
        // Wait until the no items message is visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(noItemsMessage));
 
        // Verify that the no items message is displayed
        WebElement noItemsElement = driver.findElement(noItemsMessage);
        if (noItemsElement.isDisplayed()) {
            String actualText = noItemsElement.getText();
            String expectedText = "You do not have any item(s) in Wishlist";
            if (actualText.equals(expectedText)) {
                System.out.println("Item successfully removed from wishlist. Message displayed: " + actualText);
            } else {
                System.out.println("Message displayed is incorrect. Expected: " + expectedText + ", but found: " + actualText);
            }
        } else {
            System.out.println("No items message is not displayed.");
        }
    }
    
    
    
    public void selectAllCheckbox() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Increased wait time

        try {
            // Wait for the "Select All" checkbox to be clickable
            WebElement selectAllElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='ctl00_phBody_WishList_lvWishList_chkAll']")));

            if (selectAllElement.isDisplayed() && selectAllElement.isEnabled()) {
                selectAllElement.click();
                System.out.println("Select All checkbox clicked successfully.");
            } else {
                throw new RuntimeException("Select All checkbox is either not displayed or not clickable.");
            }
        } catch (NoSuchElementException e) {
            System.out.println("Select All checkbox not found.");
            throw e;
        } catch (TimeoutException e) {
            System.out.println("Timed out waiting for Select All checkbox to be clickable.");
            throw e;
        }
    }
    

    
    public void clickAllAddToCart() throws InterruptedException {
    	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Increased wait time

    	    try {
    	        // Check if the "Select All" checkbox is clicked
    	        WebElement selectAllElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='ctl00_phBody_WishList_lvWishList_chkAll']")));
    	        
    	        if (selectAllElement.isSelected()) {
    	            // Get the initial cart item count
    	            WebElement initialCartCountElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@id='ctl00_lblTotalCartItems']")));
    	            int initialCartCount = Integer.parseInt(initialCartCountElement.getText());
    	            System.out.println("Initial cart item count: " + initialCartCount);

    	            // Find all "Add to Cart" buttons
    	            List<WebElement> addToCartButtons = driver.findElements(By.xpath("//a[@id='imgAddtoCart']"));

    	            // Click each "Add to Cart" button
    	            for (WebElement button : addToCartButtons) {
    	                if (button.isDisplayed() && button.isEnabled()) {
    	                    button.click();
    	                    System.out.println("Add to Cart button clicked successfully.");

    	                    // Wait for the page to update
    	                    Thread.sleep(2000); // Adjust the sleep time as necessary

    	                    // Navigate back to the wishlist page
    	                    driver.navigate().back();

    	                    // Wait for the page to load and the cart count element to be visible again
    	                    WebElement updatedCartCountElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@id='ctl00_lblTotalCartItems']")));
    	                    int updatedCartCount = Integer.parseInt(updatedCartCountElement.getText());
    	                    System.out.println("Updated cart item count after clicking Add to Cart: " + updatedCartCount);
    	                } else {
    	                    System.out.println("Add to Cart button is either not displayed or not clickable.");
    	                }
    	            }

    	            // Get the final cart item count
    	            WebElement finalCartCountElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@id='ctl00_lblTotalCartItems']")));
    	            int finalCartCount = Integer.parseInt(finalCartCountElement.getText());
    	            System.out.println("Final cart item count: " + finalCartCount);

    	            // Verify the cart item count has increased
    	            if (finalCartCount > initialCartCount) {
    	                System.out.println("Cart item count increased successfully.");
    	            } else {
    	                System.out.println("Cart item count did not increase.");
    	                throw new RuntimeException("Cart item count verification failed.");
    	            }
    	        } else {
    	            System.out.println("Select All checkbox is not clicked. Please select the checkbox first.");
    	        }
    	    } catch (NoSuchElementException e) {
    	        System.out.println("Select All checkbox or Add to Cart buttons not found.");
    	        throw e;
    	    } catch (TimeoutException e) {
    	        System.out.println("Timed out waiting for Select All checkbox or Add to Cart buttons.");
    	        throw e;
    	    } catch (InterruptedException e) {
    	        System.out.println("Thread was interrupted.");
    	        throw e;
    	    }
}
}
    

    
    
    


