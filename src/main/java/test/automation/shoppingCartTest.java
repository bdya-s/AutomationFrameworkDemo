package test.automation;

import org.testng.annotations.Test;

import pageEvents.CheckOut;
import pageEvents.HomePage;
import pageEvents.LoginPage;

public class shoppingCartTest extends BaseTest{

	@Test
	public void shopAndCheckout() {
		LoginPage lp = new LoginPage();
		lp.verifyLoginPageOpen();
		lp.enterUserPass();
		lp.login();	
		
		HomePage hp = new HomePage();
		hp.verifyHomePageLanding();
		hp.selectItems();
		hp.goToShoppingCart();
		
		CheckOut co = new CheckOut();
		co.verifyCartHasItems();
		co.checkOut();
		co.enterCheckOutInfo();
		co.continueCheckOut();
		co.finishCheckOut();
		co.verifyOrderCompletion();		
	}
	
}
