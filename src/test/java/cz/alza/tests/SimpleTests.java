package cz.alza.tests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import cz.alza.pageObjects.POLoginDialog;
import cz.alza.pageObjects.POProductList;
import cz.alza.pageObjects.POSearchBar;
import cz.alza.pageObjects.POSearchResultControls;
import cz.alza.pageObjects.POShoppingList;
import cz.alza.pageObjects.POTopMenu;
import settings.Settings;
import settings.Settings.ENV;
import utils.RunUtils;
import utils.RunUtils.DriverType;

public class SimpleTests {

	SoftAssert sa = new SoftAssert();
	RunUtils ru = new RunUtils();
	Settings settings;
	POLoginDialog poLoginDialog;
	POTopMenu poTopMenu;
	POSearchBar poSearchBar;
	POSearchResultControls poSearchRC;
	POProductList poProductList;
	POShoppingList poShoppingList;

// TESTS
	/*
	 * Simple Search Test 
	 * This test will open https://www.alza.cz and check simple
	 * searching via search input. Validation will be done only by checking <amount
	 * of hits> results for <search value>. The test should accept array of values
	 * to be used in searching.
	 */
	@Test
	public void simpleSearchTest() {
		// init
		WebDriver driver = ru.initDriver(DriverType.CHROME);
		poSearchBar = new POSearchBar(driver, this.sa);
		poSearchRC = new POSearchResultControls(driver, this.sa);
		settings = new Settings(ENV.TEST);

		// steps
		driver.get(settings.data.get("url"));
		for (String searchValue : new String[] { "computer", "jacobs barista", "alza table" }) {
			poSearchBar.search(searchValue);
			poSearchRC.searchResultsCheck(searchValue, true);
		}

		// finalizing
		driver.close();
		driver.quit();
		sa.assertAll();
	}

	/*
	 * Login Test 
	 * This test performs several login attempts to https://www.alza.cz:
	 *  - using wrong login name (failed login expected)
	 *  - using wrong password (failed login expected)
	 *  - using correct credentials (success login expected)
	 */
	@Test
	public void loginTest() {
		// init
		WebDriver driver = ru.initDriver(DriverType.CHROME);
		poTopMenu = new POTopMenu(driver, this.sa);
		poLoginDialog = new POLoginDialog(driver, sa);
		settings = new Settings(ENV.TEST);

		// steps
		driver.get(settings.data.get("url"));
		poTopMenu.signInLinkClick();
//		poLoginDialog.login(settings.data);

		// failure login attempts
		poLoginDialog.switchToLoginDialog();
		poLoginDialog.emailSet(settings.data.get("email"));
		poLoginDialog.passwordSet("wrongPassword");
		poLoginDialog.loginClick();
		poLoginDialog.switchBack();
		sa.assertTrue(poLoginDialog.isLoginDialogActive(1), "Login fail expected");

		poLoginDialog.switchToLoginDialog();
		poLoginDialog.emailSet("nonexisting username");
		poLoginDialog.passwordSet(settings.data.get("password"));
		poLoginDialog.loginClick();
		poLoginDialog.switchBack();
		sa.assertTrue(poLoginDialog.isLoginDialogActive(1), "Login fail expected");

		// success login attempts
		poLoginDialog.switchToLoginDialog();
		poLoginDialog.emailSet(settings.data.get("email"));
		poLoginDialog.passwordSet(settings.data.get("password"));
		poLoginDialog.loginClick();
		poLoginDialog.switchBack();
		sa.assertFalse(poLoginDialog.isLoginDialogActive(1), "Login success expected");

		// finalizing
		driver.close();
		driver.quit();
		sa.assertAll();
	}

	/*
	 * Price Ordering Test 
	 * This test logs in https://www.alza.cz, perform search for specific product and then 
	 * verifies if ordering according to price works by:
	 *  - clicking on ordering based on price from expensive to cheapest
	 *  - clicking on ordering based on price from cheapest to expensive
	 *  while checking if prices are ordered accordingly...
	 */
	@Test
	public void priceOrderingTest() {
		// init
		WebDriver driver = ru.initDriver(DriverType.CHROME);
		poTopMenu = new POTopMenu(driver, this.sa);
		poLoginDialog = new POLoginDialog(driver, sa);
		poSearchBar = new POSearchBar(driver, this.sa);
		poSearchRC = new POSearchResultControls(driver, this.sa);
		poProductList = new POProductList(driver, this.sa);
		settings = new Settings(ENV.TEST);

		// steps
		driver.get(settings.data.get("url"));
		poTopMenu.signInLinkClick();
		poLoginDialog.login(settings.data);

		String searchVal = "Alza table";
		poSearchBar.search(searchVal);
		poSearchRC.searchResultsCheck(searchVal, true);

		// prices descending order check
		poProductList.tabPriceOrderingDescClick();		
		List<Integer> productPrices_found = poProductList.getProductsPrices();
		List<Integer> productPrices_expected = new ArrayList<Integer>(productPrices_found);
		Collections.sort(productPrices_expected, Collections.reverseOrder());
		sa.assertEquals(productPrices_found, productPrices_expected, "Prices should be ordered in descending way. \nProductPrices_found: \n" + productPrices_found + "\n ProductPrices_expected: \n" + productPrices_expected + ".");

		// prices ascending order check
		poProductList.tabPriceOrderingAscClick();		
		productPrices_found = poProductList.getProductsPrices();
		productPrices_expected = new ArrayList<Integer>(productPrices_found);
		Collections.sort(productPrices_expected);
		sa.assertEquals(productPrices_found, productPrices_expected, "Prices should be ordered in ascending way. \nProductPrices_found: \n" + productPrices_found + "\n ProductPrices_expected: \n" + productPrices_expected + ".");
		
		// finalizing
		driver.close();
		driver.quit();
		sa.assertAll();
	}

	/*
	 * Favourite Management Test 
	 * This test logs in https://www.alza.cz, and then 
	 *  - perform 1st search adds first found product to favourites
	 *  - perform 2nd search adds first found product to favourites
	 *  - Go to favourites section, check that products are there and remove them.
	 */
	@Test
	public void favouritesManagementTest() {
		// init
		WebDriver driver = ru.initDriver(DriverType.CHROME);
		poTopMenu = new POTopMenu(driver, this.sa);
		poLoginDialog = new POLoginDialog(driver, sa);
		poSearchBar = new POSearchBar(driver, this.sa);
		poSearchRC = new POSearchResultControls(driver, this.sa);
		poProductList = new POProductList(driver, this.sa);
		poShoppingList = new POShoppingList(driver, sa);
		settings = new Settings(ENV.TEST);

		// steps
		driver.get(settings.data.get("url"));
		poTopMenu.signInLinkClick();
		poLoginDialog.login(settings.data);

	//	search for product & get name & click favourite		
		String[] searchValues = {"einhell aku", "segafredo arabica"};
		List<String> foundProductNames = new ArrayList<String>();
		for (String searchVal: searchValues) {
			poSearchBar.search(searchVal);
			poSearchRC.searchResultsCheck(searchVal, true);			
			WebElement box = poProductList.getFirstProductBox();
			poProductList.clickBoxProductFavorite(box);	
			foundProductNames.add(poProductList.getBoxProductName(box));
		}		

		poTopMenu.basketClick();
		poShoppingList.tabFavoritesClick();
		
		List<String> favouriteProductNames = poShoppingList.getFavouriteProductNames();
		for (String productName: foundProductNames) {
			favouriteProductNames.contains(productName);	
			sa.assertTrue(
					favouriteProductNames.contains(productName),
					"Product '" + productName + "' should be in favourite product table' collection '"
					+ favouriteProductNames + "'.");
		}
		
		poShoppingList.removeAllFromFavorite();

		// finalizing
		driver.close();
		driver.quit();
		sa.assertAll();
	}
}
