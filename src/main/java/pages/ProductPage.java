package pages;

import org.openqa.selenium.By;

public class ProductPage extends Page {

	By title = By.className("prodtitle");

	public Object getTitle() {
		return driver.findElement(title).getText();
	}

}
