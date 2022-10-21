package pageEvents;

import org.testng.Assert;

import pageElements.LoginPageMap;
import projectUtils.ElementConstruct;

public class LoginPage {
	ElementConstruct em = new ElementConstruct();
	
	public void verifyLoginPageOpen() {
		Assert.assertTrue(em.getWebElement("ID", LoginPageMap.loginButton).isDisplayed(), "Login button is not available.");
	}

	public void enterUserPass() {
		em.getWebElement("ID", LoginPageMap.userNameTxtBox).sendKeys("standard_user");
		em.getWebElement("ID", LoginPageMap.passwordTxtBox).sendKeys("secret_sauce");	
	}
	
	public void login() {
		em.getWebElement("ID", LoginPageMap.loginButton).click();
	}
}
