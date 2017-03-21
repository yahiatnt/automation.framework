package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import utils.BaseTests;
import widgets.SearchResults;

public class Page {
	protected WebDriver driver = BaseTests.getWebDriver();
	By account = By.id("account");// new comment
	By cart = By.id("header_cart");
	String menuItem_Format = ".//li[contains(@class,'menu-item') and text()='%s']";
	By searchField = By.name("s");

	public CartPage clickCart() {
		driver.findElement(cart).click();
		return new CartPage();
	}

	public AccountPage clickMyAccount() {
		driver.findElement(account).click();
		return new AccountPage();
	}
	
	public Page clickMenuItem(String menuItem){
		findMenuItem(menuItem).click();
		return new Page();
	}
	
	public Page clickMenuItem(String menuItem, String submenuItem) {
		Actions actions = new Actions(driver);
		actions.moveToElement(findMenuItem(menuItem)).perform();
		return clickMenuItem(submenuItem);
	}

	public SearchResults search(String text) {
		WebElement field = driver.findElement(searchField);
		field.clear();
		field.sendKeys(text);
		field.sendKeys(Keys.ENTER);
		return new SearchResults(driver);
	}

	private WebElement findMenuItem(String menuItem) {
		String xpath = String.format(menuItem_Format, menuItem);
		return driver.findElement(By.xpath(xpath));
	}

	public Page goTo(String postUrl) {
		driver.get(postUrl);
		return new Page();
	}
}
