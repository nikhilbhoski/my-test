package webdriverbase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);
	
    private WebDriver driver;

    @BeforeSuite
    public void beforeSuite() {
    	WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }
    
    @AfterSuite
    public void afterSuite() {
        try {
            if (driver != null) {

                // Use quit() to close all associated browser windows
                driver.quit(); 
                logger.info("WebDriver session closed successfully");
            }
        } catch (Exception e) {
            logger.error("Error during WebDriver teardown", e);
        }
    }

    public WebDriver getDriver() {
        if (this.driver == null) {
            throw new IllegalStateException("WebDriver has not been initialized");
        }
        return this.driver;
    }
}


