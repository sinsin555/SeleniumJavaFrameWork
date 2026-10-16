package ManishChaudhary.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ManishChaudhary.AbstractComponents.AbstractComponent;

public class ProductDetailsPage extends AbstractComponent
{ WebDriver driver;

	By productName = By.xpath("//div[@class='col-lg-6 rtl-text']/div/h2"); 
	By addTocart = By.xpath("//div[@class='product-buttons']/button"); 



	public ProductDetailsPage(WebDriver driver) 
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

}
