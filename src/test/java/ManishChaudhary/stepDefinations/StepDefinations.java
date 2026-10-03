package ManishChaudhary.stepDefinations;

import java.io.IOException;

import org.testng.Assert;

import ManishChaudhary.TestComponents.BaseTest;
import ManishChaudhary.pageobjects.CartPage;
import ManishChaudhary.pageobjects.CheckoutPage;
import ManishChaudhary.pageobjects.ConfirmationPage;
import ManishChaudhary.pageobjects.LandingPage;
import ManishChaudhary.pageobjects.ProductCatalogue;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinations extends BaseTest{
	
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmationPage;
	
	@Given("I landed on {string} Ecomerce Page")
	public void I_landed_on_Ecomerce_Page(String url) throws IOException
	{   
		landingPage = launchApplication();
		landingpage.goTo(url);
	}
	
	
	@Given("^Logged in with usename  (.+) and password (.+)$")
	public void Logged_in_with_usename_and_password(String userName, String password)
	{
		productCatalogue= landingpage.loginApplication(userName,password);
		
	}
	
	@When("^I add product (.+) to cart$")
	public void I_add_product_to_cart(String productName) throws InterruptedException
	{
		productCatalogue.addProductToCart(productName);
	}
	
	@When("^Checkout (.+) and submit the order$")
	public void Checkout_and_submit_the_order(String productName)
	{
		CartPage cartpage = productCatalogue.goToCartPage();
		Boolean match= cartpage.getProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartpage.goToCheckout();
		checkoutPage.selectCountry("india");
		confirmationPage = checkoutPage.submitOrder();
	}
	
	
	@Then("{string} message is displayed on ConfirmationPage")
	public void message_is_displayed_on_ConfirmationPage(String message)
	{
		String confirmationmessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmationmessage.equalsIgnoreCase(message));
		tearDown();
	}
	
	
	@Then("{string} message is displayed")
	public void incorrect_login_message_is_displayed(String message)
	{
		Assert.assertEquals(message, landingpage.getErrorMessage()); 
		tearDown();
	}
}
