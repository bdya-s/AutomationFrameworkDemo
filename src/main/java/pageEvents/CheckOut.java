package pageEvents;

import org.testng.Assert;

import pageElements.CartPageMap;
import projectUtils.ElementConstruct;

public class CheckOut {
	ElementConstruct element = new ElementConstruct();
	
	public void verifyCartHasItems() {
		Assert.assertTrue(element.getWebElement("CLASS", CartPageMap.cartItem).isDisplayed(), "Empty cart!");
	}
	
	public void checkOut() {
		element.getWebElement("ID", CartPageMap.checkOut).click();
	}
	
	public void enterCheckOutInfo() {
		element.getWebElement("ID", CartPageMap.checkOutFN).sendKeys("John");	
		element.getWebElement("ID", CartPageMap.checkOutLN).sendKeys("Doe");	
		element.getWebElement("ID", CartPageMap.checkOutZip).sendKeys("55111");	
	}
	
	public void continueCheckOut() {
		element.getWebElement("ID", CartPageMap.checkOutContinue).click();
	}
	
	public void finishCheckOut() {
		element.getWebElement("ID", CartPageMap.checkOutFinish).click();
	}
	
	public void verifyOrderCompletion() {
		Assert.assertTrue(element.getWebElement("ID", CartPageMap.checkOutComplete).isDisplayed(), "Checkout failed!");
	}
}
