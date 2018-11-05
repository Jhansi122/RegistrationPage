package registrationPackage;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class RegistrationTest {
	WebDriver driver;

	@BeforeTest
	public void Driver() {
		System.setProperty("webdriver.gecko.driver", "C:\\Program Files\\Selenium\\Geckodriver\\geckodriver.exe\\");
		driver = new FirefoxDriver();
		driver.get("https://register.theiia.org/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		System.out.println("=========================================================");
		System.out.println("Title of the page is " + driver.getTitle());

	}

	@Test(priority = 1)
	public void CookieManagement() {
		Set<Cookie> cookies = driver.manage().getCookies();
		System.out.println("=========================================================");
		System.out.println("Total number of cookies before deletion = " + cookies.size());
		System.out.println("=========================================================");
		for (Cookie cookie1 : cookies) {
			System.out.println("=========================================================");
			System.out.println("Name of the cookie = " + cookie1.getName());
			System.out.println("Domain of the cookie = " + cookie1.getDomain());
			System.out.println("Path of the cookie = " + cookie1.getPath());
			System.out.println("Name of the cookie = " + cookie1.getValue());
			System.out.println("=========================================================");

		}
		driver.manage().deleteAllCookies();
		Set<Cookie> after_deletion = driver.manage().getCookies();
		System.out.println("Total number of cookies after deletion = " + after_deletion.size());
		System.out.println("=========================================================");
	}

	@Test(priority = 2)
	public void TextFields() throws InterruptedException {
		driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("abc@abc.com");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@id='txtFirstName']")).sendKeys("abc");
		driver.findElement(By.xpath("//input[@id='txtLastName']")).sendKeys("1234");
		Thread.sleep(1000);

	}

	@Test(priority = 3)
	public void ScrollintoView() {
		JavascriptExecutor je = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(By.xpath("//label[@id='lblOrganization']"));
		je.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	@Test(priority = 4)
	public void dropdown() {
		Select country = new Select(driver.findElement(By.xpath("//select[@id='ddlPrimLoc']")));
		System.out.println("First selected option is " + country.getFirstSelectedOption().getText());
		country.selectByVisibleText("Germany");

		List<WebElement> countries = country.getOptions();

		System.out.println("=========================================================");

		for (WebElement Element : countries) {

			System.out.println("Name of the country is " + Element.getText());
		}
		System.out.println("Total number of counties in the list = " + countries.size());
		System.out.println("=========================================================");

	}

	@Test(priority = 5)
	public void enalabled() {
		boolean element = driver.findElement(By.xpath("//label[@id='lblTitle']")).isDisplayed();
		if (element) 
		{
			
		System.out.println("Textbox is displayed");

		} 
		else 
			
		{
			System.out.println("Textbox is not displayed");

		}
		
		JavascriptExecutor je = (JavascriptExecutor) driver;
		WebElement element1 = driver.findElement(By.xpath("	//label[@id='lblJob']"));
		je.executeScript("arguments[0].scrollIntoView(true);", element1);
		boolean button = driver.findElement(By.xpath("	//input[@id='btnSubmit']")).isEnabled();
		if (button) 
		{
		System.out.println("Button is enabled ");
		
		} 
		else 
		{
			System.out.println("Button is enabled ");
		}

	}
	@Test(priority=6)
	public void dropdown2()
	{
		Select industry= new Select(driver.findElement(By.xpath("//select[@id='ddlIndustryGroups']")));
		System.out.println("First selected value = "+industry.getFirstSelectedOption().getText());
		industry.selectByValue("51");
		List<WebElement> industries =industry.getOptions();
		for(WebElement Industry1:industries)
		{
			System.out.println("Elements in the list = " +Industry1.getText());
		}
		System.out.println("Total numbe of elements in the 2nd drop down = "+industries.size());
	}
	@Test(priority=7)
	public void submitButton() 
	{
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("1234");
		driver.findElement(By.xpath("//input[@id='btnSubmit']")).click();
		boolean errorMessage= driver.findElement(By.xpath("//span[@id='regExpPass']")).isDisplayed();
		if(errorMessage)
		{
			System.out.println("Error message is displayed " );
		}
		else
		{
			System.out.println("Error message is not displayed " );
		}
		
		
	}
	
	@Test(priority=8)
	public void button2() throws InterruptedException
	{
		
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@id='btnReadAgreement']")).click();
		//driver.close();
		
	}
	@Test(priority=9)
	public void multipleWindow() throws InterruptedException
	{
	    driver.getWindowHandle();
		Set<String> element = driver.getWindowHandles();
		for (String string : element) 
		{
			driver.switchTo().window(string);
		}
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		WebElement element1 = driver.findElement(By.xpath("//a[contains(text(),'Learning & Events')]"));
		Thread.sleep(1000);
		Actions action= new Actions(driver);
		action.moveToElement(element1).perform();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[@href='/training/Pages/Seminars.aspx']")).click();
		Thread.sleep(1000);
		driver.navigate().back();
		driver.close();
		
	}
	
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

