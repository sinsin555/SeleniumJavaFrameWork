package ManishChaudhary.pageobjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ManishChaudhary.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver)
	{   super(driver);
		this.driver=driver;	
		PageFactory.initElements(driver, this);		
	}	
	//driver.findElement(By.id("userEmail")).sendKeys("manish.singh@irdeto.com");
	//driver.findElement(By.id("userPassword")).sendKeys("Manish@123");
	//driver.findElement(By.id("login")).click();
	
	//PageFactory
	@FindBy(id="userEmail")
	WebElement userEmail;

	@FindBy(id="userPassword")
	WebElement password;

	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	@FindBy(xpath="button[contains(text(),'Cancel') or contains(text(),'Not Now')]")
	WebElement cancelButton;
	
	public ProductCatalogue loginApplication(String email, String pass)
	{	userEmail.sendKeys(email);
		password.sendKeys(pass);
		submit.click();
		//waitElementToBeClickable(cancelButton);
		return new ProductCatalogue(driver);
	}
	
	public void goTo(String url)
	{
		driver.get(url);
	}
	public String getErrorMessage()
	{   waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
		
	}
	
}
