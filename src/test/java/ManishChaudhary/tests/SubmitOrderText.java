package ManishChaudhary.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ManishChaudhary.TestComponents.BaseTest;
import ManishChaudhary.pageobjects.CartPage;
import ManishChaudhary.pageobjects.CheckoutPage;
import ManishChaudhary.pageobjects.ConfirmationPage;
import ManishChaudhary.pageobjects.OrderPage;
import ManishChaudhary.pageobjects.ProductCatalogue;


public class SubmitOrderText extends BaseTest{
	//String productName="ZARA COAT 3";
	//String userEmail="manish.singh@irdeto.com";
	//String userPassword="Manish@123";
	String url="https://rahulshettyacademy.com/client";
	String country="india";
	@Test(dataProvider="getData", groups="purchase")
	public void submitOrder(HashMap<String,String> input) throws InterruptedException, IOException
	{   
		landingpage.goTo(url);
		ProductCatalogue productCatalogue= landingpage.loginApplication(input.get("userEmail"),input.get("userPassword"));
		productCatalogue.addProductToCart(input.get("productName"));
		CartPage cartpage = productCatalogue.goToCartPage();
		Boolean match= cartpage.getProductDisplay(input.get("productName"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartpage.goToCheckout();
		checkoutPage.selectCountry(country);
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmationmessage = confirmationPage.getConfirmationMessage();
		System.out.println(confirmationmessage);
		Assert.assertTrue(confirmationmessage.equalsIgnoreCase("Thankyou for the order."));
		}
	
	@Test(dependsOnMethods = {"submitOrder"}, dataProvider="getData")
	public void OrderHistoryTest(String userEmail, String userPassword, String productName) throws InterruptedException, IOException
	{
		landingpage.goTo(url);
		ProductCatalogue productCatalogue= landingpage.loginApplication(userEmail,userPassword);
		OrderPage orderPage = productCatalogue.goToOrderPage();
		Assert.assertTrue(orderPage.getOrderDisplay(productName));
		
		}
	
	
	
	@DataProvider
	public Object[][] getData() throws IOException
	{   
		//\src\test\java\ManishChaudhary\Data\PurchaseOrder.json//
		
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"/src/test/java/ManishChaudhary/Data/PurchaseOrder.json");

		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
	
	
	/*HashMap<String,String> map=new HashMap<>();
	map.put("userEmail", "anshika@gmail.com");
	map.put("userPassword", "Iamking@000");
	map.put("productName", "ZARA COAT 3");
	
	HashMap<String,String> map1=new HashMap<>();
	map1.put("userEmail", "manish.singh@irdeto.com");
	map1.put("userPassword", "Manish@123");
	map1.put("productName", "ADIDAS ORIGINAL");*/
	//@DataProvider
	//public Object[][] getData()
	//{
	//  return new Object[][] {{"anshika@gmail.com","Iamking@000","ZARA COAT 3"},{"manish.singh@irdeto.com","Manish@123","ADIDAS ORIGINAL"}};
	//}
 
}
