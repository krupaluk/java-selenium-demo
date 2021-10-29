package cz.alza.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import utils.ObjectHandling;

public class POSearchResultControls {

	WebDriver driver;
	ObjectHandling oh;
	SoftAssert sa;

	public POSearchResultControls(WebDriver driver, SoftAssert sa) {
		this.driver = driver;
		this.oh = new ObjectHandling(driver);
		this.sa = sa;
	}

	//	PO definitions
	By numberOfHitsText = By.id("lblNumberItem");
	By searchedText = By.xpath("//div[@id='h1c']/h1");

//	PO methods	
	public String getCounterResults() {
		WebElement cr = oh.getElement(numberOfHitsText, 10);
		return cr.getText();
	}
	
	public String getSearchedValue() {
		WebElement sv = oh.getElement(searchedText, 10);		
		return sv.getText().split(": ")[1];
	}	
	
	public boolean wasSearchSuccessful() {
		return oh.getElement(numberOfHitsText) != null;
	}
		
	public void searchResultsCheck(String expText, boolean shouldFindSmth) {
		boolean searchFoundSmth = this.wasSearchSuccessful();
		sa.assertTrue(searchFoundSmth==shouldFindSmth, "Search engine should found something");
		
		if (searchFoundSmth) {
			String resultText = this.getSearchedValue();
			int amountOfHits = Integer.parseInt(this.getCounterResults().replaceAll("[^\\d.]", "").replace(",", ""));
			sa.assertTrue(amountOfHits>0, "Search number of hits should be greater than 0");
			sa.assertEquals(resultText, expText);			
		}
		
		
	}
}
