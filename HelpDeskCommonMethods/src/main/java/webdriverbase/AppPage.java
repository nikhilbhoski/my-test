package webdriverbase;

import java.util.Objects;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AppPage {
	private static final Logger logger = LoggerFactory.getLogger(AppPage.class);
	
	public static String PATH_TO_TEST_DATA_FILE = "src/main/resources/";
	public static String WINDOWS_PATH_TO_TEST_DATA_DIR = "src/main/resources/";
	public static int WAIT_TIME_SEC = 60;	
	protected WebDriver driver ;
	
	JavascriptExecutor javaScriptExecutor;
	
	public AppPage(WebDriver driver) {
		this.driver = Objects.requireNonNull(driver, "WebDriver cannot be null");
		PageFactory.initElements(driver, this);
		maximizeWindow();
	}
	
	public WebDriver getDriver() {
		return this.driver;
	}

	public void get(String url) {
		this.driver.get(url);
	}

	public String getCurrentUrl() {
		return this.driver.getCurrentUrl();
	}
	
	public void maximizeWindow() {
			driver.manage().window().maximize();		
	}
	
	public void clearAndType(WebElement element, String text) {
		element.clear();
		element.sendKeys(text);
	}
	
	public void switchToDefaultContent() {
		this.driver.switchTo().defaultContent();
	}
	
	public void switchToFrame(WebElement frame) {
		this.driver.switchTo().frame(frame);
	}
	
	public void hoverOverElementUsingJS(WebElement element) {
		String js = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover',true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
		getJavaScriptExecutor().executeScript(js, element);
	}
	
	public JavascriptExecutor getJavaScriptExecutor() {
		if (javaScriptExecutor == null){
			javaScriptExecutor = (JavascriptExecutor) driver;
		}	
		return javaScriptExecutor;
	}
	
	public void scrolltoElement(String locator) {
		try {
			WebElement element = this.driver.findElement(By.xpath(locator));

			scrolltoElement(element);
		} catch (Exception ex) {
			logger.error("Failed to scroll to element", ex.getMessage());
			throw new RuntimeException(ex);
		}
	}
	
	public void scrolltoElement(WebElement element) throws InterruptedException {
		try {
            getJavaScriptExecutor().executeScript("arguments[0].scrollIntoView({block: 'center'})", element);
            waitForVisible(element);
            logger.debug("Scrolled to element");
        } catch (Exception e) {
            logger.error("Failed to scroll to element", e);
            throw new RuntimeException("Scroll to element failed", e);
        }
	}
	
	public void waitForVisible(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, WAIT_TIME_SEC);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	
	public String getCurrentWorkingDirectory()
	{
		return System.getProperty("user.dir");
		
	}
	
	public String getTestDataFullDirPath(String fileName) {
		return String.format("%s%s%s", getCurrentWorkingDirectory(), WINDOWS_PATH_TO_TEST_DATA_DIR, fileName);
	}
	
	
	public enum OSType {
	    Windows, MacOS, Linux, Other
	  };
	  
	 protected static OSType detectedOS;
	 
	 public static OSType getOperatingSystemType() 
	 {
		 detectedOS = OSType.Windows;
		 return detectedOS;
	 }
	

}
