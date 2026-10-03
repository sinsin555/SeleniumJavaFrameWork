package ManishChaudhary.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ManishChaudhary.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent
{
	WebDriver driver;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutEle;
	
	@FindBy(css=".cartSection h3")
	private List <WebElement> cartProducts;
	
	@FindBy(css="tr td:nth-child(3)")
	private List <WebElement> productNames;
	
	By cartProductsBy = By.cssSelector(".cartSection h3");

	public OrderPage(WebDriver driver)
	{   super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public Boolean getOrderDisplay(String productName) {
		//waitForElementToAppear(cartProductsBy);
		Boolean macth = productNames.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
		return macth;
	}
	
	
}
