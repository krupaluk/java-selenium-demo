package cz.alza.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import utils.ObjectHandling;
import utils.RunUtils;

public class POSearchBar {
	
	WebDriver driver;
	ObjectHandling oh;
	SoftAssert sa;
	RunUtils ru;
	
	public POSearchBar(WebDriver driver, SoftAssert sa) {
		this.driver = driver;
		this.oh = new ObjectHandling(driver);
		this.ru = new RunUtils();
		this.sa = sa;
	}	
	
//	PO definitions
	By searchInput = By.id("edtSearch");
	By searchButton = By.id("btnSearch");

//	PO modules
    public void search(String searchValue){
    	this.searchInputSet(searchValue);
        this.searchButtonClick();
        this.ru.sleep(1);
    }

//	PO keywords	
    public void searchInputSet(String searchValue){
        WebElement si = driver.findElement(searchInput);
        si.clear();
        si.sendKeys(searchValue);
    }
    
	public void searchButtonClick() {
		WebElement sb = oh.getElement(searchButton, 10);
		sb.click();
	}
}
