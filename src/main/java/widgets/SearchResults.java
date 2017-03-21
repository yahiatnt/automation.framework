package widgets;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.CartPage;
import pages.ProductPage;

public class SearchResults {
	private WebDriver driver;
	By productTitle = By.xpath("//h2[@class='prodtitle']/a");
	By grid = By.id("grid_view_products_page_container");
	By products = By.xpath(".//div[contains(@class,'product_grid_item')]");
	By originalPrice = By.className("oldprice");
	By currentPrice = By.className("currentprice");
	By addToCartButton = By.className("wpsc_buy_button");
	By goToCheckoutButton = By.className("go_to_checkout");

	public SearchResults(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isProductListed(String productName) {
		return findProduct(productName) != null;
	}

	public Object getOriginalPrice(String productName) {
		WebElement product = findProduct(productName);
		return product.findElement(originalPrice).getText();
	}

	public Object getCurrentPrice(String productName) {
		WebElement product = findProduct(productName);
		return product.findElement(currentPrice).getText();
	}

	public int getNumberOfListedProducts() {
		return getListedProducts().size();
	}

	private WebElement findProduct(String productName) {
		List<WebElement> products = getListedProducts();
		for (WebElement product : products) {
			if (productName.equals(product.findElement(productTitle).getText())) {
				return product;
			}
		}

		return null;
	}

	private List<WebElement> getListedProducts() {
		return driver.findElement(grid).findElements(products);
	}

	public ProductPage clickProduct(String productName) {
		WebElement product = findProduct(productName);
		product.findElement(productTitle).click();
		return new ProductPage();
	}

	public CartPage addProductToCart(String productName) {
		clickAddProductToCart(productName);
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.presenceOfElementLocated(goToCheckoutButton)).click();
		return new CartPage();
	}

	public void clickAddProductToCart(String productName) {
		WebElement product = findProduct(productName);
		product.findElement(addToCartButton).click();
	}
}
