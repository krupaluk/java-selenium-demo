package cz.alza.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import utils.ObjectHandling;
import utils.RunUtils;

public class POTopMenu {

	WebDriver driver;
	ObjectHandling oh;
	SoftAssert sa;
	RunUtils ru;

	public POTopMenu(WebDriver driver, SoftAssert sa) {
		this.driver = driver;
		this.oh = new ObjectHandling(driver);
		this.sa = sa;
		this.ru = new RunUtils();
	}

//	PO definitions
	By signInLink = By.xpath("//a[@id='lblLogin']");
	By basket = By.id("basketLink");

//	PO keywords	
	public void signInLinkClick() {
		WebElement si = oh.getElement(signInLink, 10);
		si.click();
	}

	public boolean signInLinkExists() {
		return oh.getElement(signInLink) != null;
	}
	
	public boolean signInLinkExists(int timeout) {
		return oh.getElement(signInLink, timeout) != null;
	}
	
	public void basketClick() {
		WebElement we = oh.getElement(basket, 10);
		we.click();
		ru.sleep(500);
	}
	

}
