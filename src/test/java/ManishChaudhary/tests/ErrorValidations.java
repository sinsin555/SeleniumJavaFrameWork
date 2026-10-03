package ManishChaudhary.tests;


import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import ManishChaudhary.TestComponents.BaseTest;
import ManishChaudhary.TestComponents.Retry;
import ManishChaudhary.pageobjects.CartPage;
import ManishChaudhary.pageobjects.ProductCatalogue;

public class ErrorValidations extends BaseTest{
	
	@Test(groups="ErrorHandling", retryAnalyzer =Retry.class)
	public void LoginErrorValidation()
	{
		//String productName="ZARA COAT 3";
		String userEmail="manish.singh@irdeto.com";
		String userPassword="Manish@12";
		String url="https://rahulshettyacademy.com/client";
		landingpage.goTo(url);
		landingpage.loginApplication(userEmail, userPassword);
		Assert.assertEquals("Incorrect email or password.", landingpage.getErrorMessage()); 
	}
	
	@Test
	public void ProductErrorValidation() throws InterruptedException, IOException
	{
		String productName="ZARA COAT 3";
		String userEmail="manish.singh@irdeto.com";
		String userPassword="Manish@123";
		String url="https://rahulshettyacademy.com/client";
		//String country="india";
		landingpage.goTo(url);
		ProductCatalogue productCatalogue= landingpage.loginApplication(userEmail,userPassword);
		productCatalogue.addProductToCart(productName);
		CartPage cartpage = productCatalogue.goToCartPage();
		Boolean match= cartpage.getProductDisplay(productName);
		Assert.assertTrue(match);
		
		}

}
