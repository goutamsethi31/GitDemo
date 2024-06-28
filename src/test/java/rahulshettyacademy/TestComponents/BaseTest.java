package rahulshettyacademy.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.LandingPage;

public class BaseTest {

	public WebDriver driver;
	public LandingPage landingPage;

	public WebDriver initializeDriver() throws IOException {
		// properties class
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "\\src\\main\\java\\rahulshettyacademy\\resources\\GlobalData.properties");
		prop.load(fis);
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") :prop.getProperty("browser");

		if (browserName.equalsIgnoreCase("chrome")) {
			
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			 driver = new FirefoxDriver();
			// Firefox
		} else if (browserName.equalsIgnoreCase("edge")) {
			// Edge
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;

	}
	public String getScreenshot(String testCases,WebDriver driver) throws IOException
	 {
		TakesScreenshot ts=(TakesScreenshot)driver; 
		File source=ts.getScreenshotAs(OutputType.FILE);
		File file =new File(System.getProperty("user.dir")+"\\ reports\\" + testCases +".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"\\ reports\\" + testCases +".png";
	 }
	
	
	@BeforeMethod(alwaysRun=true)
    public LandingPage launchApplication() throws IOException
    {
    	driver=initializeDriver();
    	landingPage =new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	
    }
	@AfterMethod(alwaysRun=true)
	public void tearDown()
	{
		driver.close();
	}
}
