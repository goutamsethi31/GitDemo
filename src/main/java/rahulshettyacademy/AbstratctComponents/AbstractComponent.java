package rahulshettyacademy.AbstratctComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.OrderPage;

public class AbstractComponent {

	
	WebDriver driver;
	public AbstractComponent(WebDriver driver) {
		this.driver=driver;
	PageFactory.initElements(driver, this);
	}
	@FindBy(css="button[routerlink='/dashboard/cart']")
	WebElement cartHeader;
	
	@FindBy(xpath="//button[@routerlink='/dashboard/myorders']")
	WebElement orderHeader;
	

	public void wiatForElementToAppear(By FindBy)
	{
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOfElementLocated(FindBy));
	
	}
	public void wiatForWebElementToAppear(WebElement FindBy)
	{
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOf(FindBy));
	
	}
	
	public CartPage goToCartPage()
	{
	//	driver.findElement(By.cssSelector("button[routerlink='/dashboard/cart']")).click();
		cartHeader.click();
		CartPage cartpage=new CartPage(driver);
		return cartpage;
	}
	public OrderPage goToOrdersPage()
	{
	//	driver.findElement(By.cssSelector("button[routerlink='/dashboard/cart']")).click();
		orderHeader.click();
		OrderPage orderpage=new OrderPage(driver);
		return orderpage;
	}
	public void waitForElementToDisappear(WebElement ele)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	
	
}
