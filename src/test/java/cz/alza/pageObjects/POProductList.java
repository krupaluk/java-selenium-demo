package cz.alza.pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import utils.ObjectHandling;
import utils.RunUtils;

public class POProductList {

	WebDriver driver;
	ObjectHandling oh;
	SoftAssert sa;
	RunUtils ru;

	public POProductList(WebDriver driver, SoftAssert sa) {
		this.driver = driver;
		this.oh = new ObjectHandling(driver);
		this.sa = sa;
		this.ru = new RunUtils();
	}

//	PO definitions
	By tabPriceDesc = By.xpath("//div[@id='tabs']//a[contains(@href,'cenadesc')]");
	By tabPriceAsc = By.xpath("//div[@id='tabs']//a[contains(@href,'cenaasc')]");
	By productPrices = By.xpath("//div[@id='boxes']/div[contains(@class,'browsingitem')]//span[@class='c2']");
	
	By productBoxes = By.xpath("//div[@id='boxes']/div[contains(@class,'browsingitem')]");
	By productBoxName = By.xpath("//a[contains(@class,'name')]");
	By productBoxPrice = By.xpath("//span[@class='c2']");
	By productBoxFavorite = By.xpath("//span[contains(@class,'favorites')]");
	
	
//	PO methods	
	public List<Integer> getProductsPrices() {
		List<Integer> prices = new ArrayList<Integer>();
		List<WebElement> pricesObjCollection = driver.findElements(productPrices);
		for(int i=0; i<pricesObjCollection.size(); i++) {
			int price = Integer.parseInt( pricesObjCollection.get(i).getText().replaceAll("[^\\d.]", "").replace(",", ""));
			prices.add(price);
		}
		return prices;		
	}
	

//	PO keywords	
	public void tabPriceOrderingDescClick() {
		WebElement we = oh.getElement(tabPriceDesc);
		we.click();
		ru.sleep(1000);
		
	}

	public void tabPriceOrderingAscClick() {
		WebElement we = oh.getElement(tabPriceAsc);
		we.click();
		ru.sleep(1000);
	}
	
//	PO keywords - Boxes	
	public String getBoxProductName(WebElement box) {
		return box.findElement(productBoxName).getText();
	}
	
	public Integer getBoxProductPrice(WebElement box) {
		return Integer.parseInt(box.findElement(productBoxPrice).getText().replaceAll("[^\\d.]", "").replace(",", ""));
	}
	
	public void clickBoxProductFavorite(WebElement box) {
		box.findElement(productBoxFavorite).click();
	}
	
	public WebElement getFirstProductBox() {
		return this.getBox(0);
	}
	
	public WebElement getBox(int index) {
		List<WebElement> boxes = this.getProductBoxes();
		if (index>=boxes.size()) {index=boxes.size()-1;}
		return boxes.get(index);		
	}
	
	public List<WebElement> getProductBoxes() {
		return driver.findElements(productPrices);
	} 
	
}
