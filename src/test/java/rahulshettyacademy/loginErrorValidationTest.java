package rahulshettyacademy;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class loginErrorValidationTest extends BaseTest {

	    @Test (groups= {"ErroHandling"})
		public void submitOrder() throws IOException
		{
		String productName = "ZARA COAT 3";
		
		landingPage.loginApplication("goutam@gmail.com","xyztam@12345");
		
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
	}
	    @Test
		public void ProductErrorValidation() throws IOException
		{
		String productName = "ZARA COAT 3";
		
		ProductCatalogue productcatalogue=landingPage.loginApplication("goutam@gmail.com","Goutam@12345");
		List<WebElement> products=productcatalogue.getProductList();
		productcatalogue.addProductToCart(productName);
		CartPage cartpage=productcatalogue.goToCartPage();
		
		Boolean match=cartpage.VerifyProductDisplay("ZARA COAT 3");
		Assert.assertTrue(match);
		}
	    
	   
	   
	    
	    

}
