package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import webdriverbase.AppPage;


    public class LoginPage extends AppPage {
        public LoginPage(WebDriver driver) {
            super(driver);
        }

        public void login(String username, String password) {

            driver.findElement(By.id("username")).sendKeys(username);
            driver.findElement(By.id("password")).sendKeys(password);
            driver.findElement(By.id("loginButton")).click();
        }

        public void forgotPassword() {
            driver.findElement(By.linkText("Forgot password?")).click();
        }
    
    }
