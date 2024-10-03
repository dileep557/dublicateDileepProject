package com.pages.RLL_240Testing_BooksWagon;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class LoginPage {
	WebDriver driver;
	 
    By MyAccount = By.xpath("//span[contains(text(),'My Account')]");
    By loginButton = By.xpath("//div[@id='ctl00_divLogin']/a[@href='https://www.bookswagon.com/login']");
    By emailField = By.xpath("//input[@id='ctl00_phBody_SignIn_txtEmail']");
    By passwordField = By.xpath("//input[@id='ctl00_phBody_SignIn_txtPassword']");
    By submitButton = By.xpath("//a[@id='ctl00_phBody_SignIn_btnLogin']");
    By wishlistIcon = By.xpath("//label[@id='ctl00_lblWishlistCount']");
 
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
 
    public void launchBooksWagon() {
        driver.get("https://www.bookswagon.com/");
        driver.manage().window().maximize();
    }
 
    public void login(String username, String password) throws InterruptedException {
        WebElement profileIcon = driver.findElement(MyAccount);
 
        // Click on the profile icon
        Actions actions = new Actions(driver);
        actions.moveToElement(profileIcon).perform();
        Thread.sleep(1000);
 
        WebElement Logint = driver.findElement(loginButton);
        Logint.click();
 
        // Enter username and password
        WebElement usernameField = driver.findElement(emailField);
        usernameField.sendKeys(username);
 
        WebElement passwordInput = driver.findElement(passwordField);
        passwordInput.sendKeys(password);
        Thread.sleep(1000);
 
        // Click the login button
        WebElement loginButton = driver.findElement(submitButton);
        loginButton.click();
 
        // Click the logo to return to the home page
        driver.findElement(By.xpath("//img[@id='ctl00_imglogo']")).click();
    }
 
    public void scrollAndClickOn7thCard() throws InterruptedException {
        // Scroll to the 7th card and click it
        WebElement targetElement = driver.findElement(By.xpath("(//div[@class='card align-items-center'])[7]"));
 
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", targetElement);
        Thread.sleep(1000);
        targetElement.click();
    }
 
    public void addToWishlist() throws InterruptedException {
        WebElement targetElement2 = driver.findElement(By.xpath("//a[contains(@class, 'btn themeborder themecolor d-block mb-2') and contains(text(), 'Add to Wishlist')]"));
        targetElement2.click();
        Thread.sleep(1000);
    }
    
    public void clickMyWishlist() {
        WebElement wishlistIconElement = driver.findElement(wishlistIcon);
        wishlistIconElement.click();
    }
 
    public boolean isWishlistIconClickable() {
        WebElement wishlistIconElement = driver.findElement(wishlistIcon);
        boolean isDisplayed = wishlistIconElement.isDisplayed();
        boolean isEnabled = wishlistIconElement.isEnabled();
 
        if (isDisplayed && isEnabled) {
            System.out.println("Wishlist icon is displayed and clickable.");
            return true;
        } else {
            System.out.println("Wishlist icon is either not displayed or not clickable.");
            return false;
        }
    }
    

}
