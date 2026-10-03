package ManishChaudhary.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ManishChaudhary.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent
{
	WebDriver driver;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutEle;
	
	@FindBy(css=".cartSection h3")
	private List <WebElement> cartProducts;
	
	By cartProductsBy = By.cssSelector(".cartSection h3");

	public CartPage(WebDriver driver)
	{   super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public Boolean getProductDisplay(String productName) {
		waitForElementToAppear(cartProductsBy);
		Boolean macth = cartProducts.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
		return macth;
	}
	
	public CheckoutPage goToCheckout()
	{
		checkoutEle.click();
		return new CheckoutPage(driver);
	}

}
