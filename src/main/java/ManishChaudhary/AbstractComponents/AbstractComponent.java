package ManishChaudhary.AbstractComponents;

import java.time.Duration;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ManishChaudhary.pageobjects.CartPage;
import ManishChaudhary.pageobjects.OrderPage;

public class AbstractComponent {

	 WebDriver driver;

	public AbstractComponent(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
	}
	
	@FindBy(css="[routerlink*='cart']")
	WebElement cartHeader;
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement orderHeader;
	
	
	public void waitForElementToAppear(By findBy)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	public void waitForWebElementToAppear(WebElement findBy)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	public void waitForElementToDisappear(WebElement ele) throws InterruptedException
	{   Thread.sleep(1000);
		//WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(2));
		//wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	public void waitElementToBeClickable(WebElement ele) 
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		WebElement cancelButton= wait.until(ExpectedConditions.elementToBeClickable(ele));
		cancelButton.click();
	}
	public CartPage goToCartPage()
	{	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    // Wait until loading spinner disappears
    wait.until(ExpectedConditions.invisibilityOfElementLocated(
            By.cssSelector(".ngx-spinner-overlay")
    ));

    // Wait until Cart button is clickable
    wait.until(ExpectedConditions.elementToBeClickable(cartHeader));
		cartHeader.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
	public OrderPage goToOrderPage()
	{
		orderHeader.click();
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;
	}
	
}
