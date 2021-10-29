package utils;

import java.time.Duration;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class RunUtils {

	public enum DriverType {
		FIREFOX,
		CHROME,
	}
	
	public WebDriver initDriver(DriverType driverType) {
		return initDriver(driverType, new Dimension(1280, 900));
	}
	
	public WebDriver initDriver(
			DriverType driverType,
			Dimension dimension
			) {
		WebDriver driver;
		if (driverType.equals(DriverType.CHROME)) {
			System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
			driver = new ChromeDriver();	
		} else {
			System.setProperty("webdriver.chrome.driver", "src/main/resources/geckodriver.exe");
			driver = new FirefoxDriver();
		}		
		driver.manage().window().setSize(new Dimension(1280, 900));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		return driver;
	}

	public void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
