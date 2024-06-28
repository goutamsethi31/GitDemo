package rahulshettyacademy;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {
	String productName = "ZARA COAT 3";
	    @Test(dataProvider="getData" ,groups={"Purchase"})
		public void submitOrder(HashMap<String,String> input) throws IOException
		{
		
		
		ProductCatalogue productcatalogue=landingPage.loginApplication(input.get("email"),input.get("password"));
		List<WebElement> products=productcatalogue.getProductList();
		productcatalogue.addProductToCart(input.get("product"));
		CartPage cartpage=productcatalogue.goToCartPage();
		
		Boolean match=cartpage.VerifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutpage =cartpage.goTocheckout();
		checkoutpage.selectCountry("india");
		ConfirmationPage confirmationpage =checkoutpage.submitOrder();
		
		String confirmationMessage =confirmationpage.getConfirmationMessage();
		Assert.assertTrue(confirmationMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		}
		
		 @Test(dependsOnMethods = {"submitOrder"})
		    public void OrderHistoryTest()
		    {
		    	//To verify ZARA COAT 3 is displaying in orders page
		    	ProductCatalogue productcatalogue=landingPage.loginApplication("goutam@gmail.com","Goutam@12345");
		    OrderPage orderspage=	productcatalogue.goToOrdersPage();
		   Assert.assertTrue(orderspage.VerifyOrderDisplay(productName));
		    	
	    }
//		 @DataProvider
//		 public Object[][] getData()
//		 {
//			 return new Object [][] {{"goutam@gmail.com","Goutam@12345","ZARA COAT 3"},{"Karishma@gmail.com","Karishma@12345",
//				 "ADIDAS ORIGINAL"}};
//		 }
		 @DataProvider
		 public Object[][] getData()
		 {
			 HashMap<String,String> map=new HashMap<String,String>();
			 map.put("email", "goutam@gmail.com");
			 map.put("password", "Goutam@12345");
			 map.put("product", "ZARA COAT 3");
			 

			 HashMap<String,String> map1=new HashMap<String,String>();
			 map1.put("email", "Karishma@gmail.com");
			 map1.put("password", "Karishma@12345");
			 map1.put("product", "ADIDAS ORIGINAL");
			 
			 return new Object [][] {{map},{map1}};
	 }
		 
	

}
