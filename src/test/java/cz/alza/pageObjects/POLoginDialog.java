package cz.alza.pageObjects;

import java.util.HashMap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;
import utils.ObjectHandling;

public class POLoginDialog {

	WebDriver driver;
	ObjectHandling oh;
	SoftAssert sa;

	public POLoginDialog(WebDriver driver, SoftAssert sa) {
		this.driver = driver;
		this.oh = new ObjectHandling(driver);
		this.sa = sa;
		
	}	
	
//	PO definitions
	By dialogActive = By.xpath("//div[@id='alzaDialogModalLayer'][not(contains(@style,'display: none'))]");
	By dialogInactive = By.xpath("//div[@id='alzaDialogModalLayer'][contains(@style,'display: none')]");
	By loginIframe = By.id("loginIframe");
	By emailInput = By.id("userName");
	By passwordInput = By.id("password");
	By loginButton = By.id("btnLogin");
	
//	PO modules
	public void login(HashMap<String, String> data) {
		this.login(data.get("email"), data.get("password"));
	}	
	
	public void login(String email, String password) {
		this.switchToLoginDialog();
		this.emailSet(email);
		this.passwordSet(password);
		this.loginClick();
		this.switchBack();
		oh.elementDisappears(dialogActive);
	}	
	
//	PO keywords	
	public void emailSet(String email) {
		WebElement we = oh.getElement(emailInput);
		we.clear();
		we.sendKeys(email);
	}

	public void passwordSet(String password) {
		WebElement we = oh.getElement(passwordInput);
		we.clear();
		we.sendKeys(password);
	}
	
	public void loginClick() {
		WebElement we = oh.getElement(loginButton);
		we.click();
	}	
	
	public boolean isLoginDialogActive(int timeout) {
		return oh.elementExists(dialogActive, timeout);
	}
	
	public void switchToLoginDialog() {
		driver.switchTo().frame("loginIframe");
	}
	
	public void switchBack() {
		driver.switchTo().defaultContent();
	}
}
