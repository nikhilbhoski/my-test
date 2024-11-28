package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import webdriverbase.AppPage;

public class HomePage extends AppPage {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void verifyHomePage() {
        if (!driver.getCurrentUrl().equals("https://www.happyfox.com/home")) {
            throw new IllegalStateException("Not on the home page");
        }
    }

    public void navigateToProfile() {
        driver.findElement(By.id("profileLink")).click();
    }
}