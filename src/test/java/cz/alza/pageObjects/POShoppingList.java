package cz.alza.pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.asserts.SoftAssert;

import utils.ObjectHandling;
import utils.RunUtils;

public class POShoppingList {

	WebDriver driver;
	ObjectHandling oh;
	SoftAssert sa;
	RunUtils ru;

	public POShoppingList(WebDriver driver, SoftAssert sa) {
		this.driver = driver;
		this.oh = new ObjectHandling(driver);
		this.sa = sa;
		this.ru = new RunUtils();
	}

//	PO definitions
	//	tabs
	By tabBasket = By.xpath("//div[@class='line']//div[contains(@class,'basket')]");
	By tabFavorites = By.xpath("//div[@data-type='favorites']");
	By tabFavoritesDelete = By.xpath("//div[@data-type='favorites']//span[@class='deleteLink']");
	By shoppingList = By.xpath("//div[@class='shoppingListOrder1']");
	
	//	favorite items
	By favProdRows = By.xpath("//div[contains(@class,'itemsContainer')]//li");
	By favProductName = By.xpath("//div[contains(@class,'itemsContainer')]//li//a[@class='name']");
	
	//	alza dialog
	By alzaDialog = By.id("alzaDialog");
	By alzaDialogYesButton = By.xpath("//div[@id='alzaDialog']//span[contains(@class,'green')]");
	

//	PO modules
	public void removeAllFromFavorite() {
		this.tabFavoritesClick();
		this.tabFavoritesDeleteClick();
		this.removeFromBasketDialogYesClick();
	}
	
//	PO keywords	
	public void tabBasketClick() {
		WebElement we = oh.getClickableElement(tabBasket, 5);
		we.click();
	}
	
	public void tabFavoritesClick() {
		WebElement we = oh.getClickableElement(tabFavorites, 5);
		we.click();
		ru.sleep(500);
	}
		
	public void tabFavoritesDeleteClick() {
		Actions action = new Actions(driver);
		WebElement fa = oh.getElement(tabFavorites, 5);
		WebElement we = oh.getElement(tabFavoritesDelete, 5);
		action.moveToElement(fa).moveToElement(we);
		ru.sleep(500);
		we.click();
	}
	
	public void removeFromBasketDialogYesClick() {
		oh.getElement(alzaDialog, 5);
		oh.clickUntilDisappears(alzaDialogYesButton);
	}

	
//	PO keywords - Favourite list
	public List<String> getFavouriteProductNames() {
		List<String> favProdNames = new ArrayList<String>();
		List<WebElement> favProdRows = driver.findElements(favProductName);
		for(int i=0; i<favProdRows.size(); i++) {
			String name = favProdRows.get(i).getText();
			favProdNames.add(name);
		}
		return favProdNames;
	}
	
	public String getFavouriteProductName(WebElement favProdRow) {
		return favProdRow.findElement(favProductName).getText();
	}
	
	public WebElement getFavProdRow(int index) {
		List<WebElement> favProdRows = this.getFavProdRows();
		if (index>=favProdRows.size()) {index=favProdRows.size()-1;}
		return favProdRows.get(index);		
	}
	
	public List<WebElement> getFavProdRows() {
		return driver.findElements(favProdRows);
	} 
}
