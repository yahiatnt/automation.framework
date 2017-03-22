package utils;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import pages.Page;

public class BaseTests {
	private static WebDriver driver;
	protected static Page homePage;

	@BeforeClass
	public static void launchApplication() {
		setChromeDriverProperty();
		driver = new ChromeDriver();
		driver.get(Links.HOME);
		homePage = new Page();
	}

	@AfterClass
	public static void closeBrowser() {
		driver.quit();
	}

	public static WebDriver getWebDriver() {
		return driver;
	}

	private static void setChromeDriverProperty() {
		System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
	}
}
