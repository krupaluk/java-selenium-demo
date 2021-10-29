package utils;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ObjectHandling {
	
	WebDriver driver;
	WebDriverWait wait;
	RunUtils ru;
	int timeout;
	
	public ObjectHandling(WebDriver driver) {
		this.driver = driver;
		this.timeout = 10;
		this.ru = new RunUtils();
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(this.timeout));
	}
	
	public boolean elementExists(By by) {
		return this.elementExists(by, this.timeout);
	}
	
	public boolean elementExists(By by, int timeout) {
		WebDriverWait localWait = new WebDriverWait(driver, Duration.ofSeconds(1));
		try {
			localWait.until(ExpectedConditions.presenceOfElementLocated(by));
			System.out.println("-> Element '" + by + "' FOUND (in " + timeout + " secs).");
			return true;
		} catch(Exception e) {}
		System.out.println("-> Element '" + by + "' NOT FOUND (in " + timeout + " secs).");
		return false;		 
	}
	
	public boolean elementDisappears(By by) {
		return this.elementDisappears(by, this.timeout);
	}
	
	public boolean elementDisappears(By by, int timeout) {
		for (int i=0; i<timeout; i++) {
			if (this.getElement(by, 1)==null) {
				System.out.println("Object " + by + " disappeared.");
				return true;
			} else {
				System.out.println("Object " + by + " still exists. Waiting 1 sec.");
				ru.sleep(1000);
			}
		}
		return false;
	}
	
	public boolean clickUntilDisappears(By by) {
		return this.clickUntilDisappears(by, this.timeout);
	}
	
	public boolean clickUntilDisappears(By by, int attempts) {
		for (int attempt=0; attempt<attempts; attempts++) {
			// test may wants to click when object already disappeared
			try {this.getClickableElement(by, 1).click();} 
			catch(Exception e) {return false;} 			
			if (this.elementDisappears(by, 1)) {
				return true;
			}
		}
		return false;
	}
		

	public WebElement getElement(By by) {
		try {
		return this.wait.until(ExpectedConditions.presenceOfElementLocated(by));
		} catch(Exception e) {
			return null;
		}
	}

	public WebElement getElement(By by, int timeout) {
		try {
			WebDriverWait localWait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return localWait.until(ExpectedConditions.presenceOfElementLocated(by));
		} catch(Exception e) {
			return null;
		}
	}
	
	public WebElement getClickableElement(By by) {
		return this.getClickableElement(by, this.timeout);
	}
	
	public WebElement getClickableElement(By by, int timeout) {
		try {
			WebDriverWait localWait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return localWait.until(ExpectedConditions.elementToBeClickable(by));
		} catch(Exception e) {
			return null;
		}		
	}
	
}
