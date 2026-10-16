package ManishChaudhary.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ManishChaudhary.AbstractComponents.AbstractComponent;

public class ProductDetailsPage extends AbstractComponent
{ WebDriver driver;

	By productNameText = By.xpath("//div[@class='col-lg-6 rtl-text']/div/h2");
	By product = By.xpath("//div[@class='col-lg-6 rtl-text']/div[1]");
	By addToCart = By.xpath("//div[@class='product-buttons']/button"); 
	By toastMessage = By.cssSelector("#toast-container");
	By productsBy = By.cssSelector(".row");

	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	@FindBy(css=".ngx-spinner-overlay")
	WebElement spinner_overlay;
	
	public ProductDetailsPage(WebDriver driver) 
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public WebElement getProductByName(String productName)
	{	waitForElementToAppear(productsBy);
		WebElement prod = driver.findElement(product);
		boolean match =prod.findElement(By.cssSelector("h2")).getText().equalsIgnoreCase(productName);
		if(match==true)
		{
			return prod;
		}
		else 
		{
			return null;
		}
		
	}
	
	public void addProductToCart(String productName) throws InterruptedException 
	{	WebElement prod = getProductByName(productName);
		waitForElementToDisappear(spinner_overlay);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(spinner);
		
	}

}
