package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstratctComponents.AbstractComponent;

public class CartPage {
	
	
	WebDriver driver;

	public  CartPage(WebDriver driver)
	{
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	@FindBy(xpath="//h3[text()='ZARA COAT 3']")
	List<WebElement> cartproducts;
	
	@FindBy(xpath="//button[text()='Checkout']")
	WebElement checkout;
	
	public Boolean VerifyProductDisplay(String productName)
	{
		Boolean match=cartproducts.stream().anyMatch(cartprod->cartprod.getText().equalsIgnoreCase(productName));
		return match;
	}
	public CheckoutPage goTocheckout()
	{
		checkout.click();
		return new CheckoutPage(driver);
	}
	

}
