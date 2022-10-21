package pageEvents;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import pageElements.HomePageMap;
import projectUtils.ElementConstruct;

public class HomePage {
	ElementConstruct elements = new ElementConstruct();
	
	public void verifyHomePageLanding() {
		Assert.assertTrue(elements.getWebElement("CSS", HomePageMap.addToCartButton).isDisplayed(), "Home page not loaded!");
	}
	
	public void selectItems() {
		List<WebElement> lst = elements.getListWebElements("CSS", HomePageMap.addToCartButton);
		
		if(lst.size() > 0) {
			lst.get(new Random().nextInt(lst.size()-1)).click();
		}
	}
	
	public void goToShoppingCart() {
		elements.getWebElement("CLASS", HomePageMap.shoppingCart).click();
	}

}
