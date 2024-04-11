package testScripts;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commonUtils.Utility;

public class LoginUsingParameters {

	WebDriver driver;

	@Parameters({ "browser", "url" })
	@BeforeMethod
	public void setUp(String browser, String url) {

		if (browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else {
			driver.quit();
		}

		driver.get(url);
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
	}

	@Parameters({ "username", "password" })
	@Test
	public void login(String Username, String Password) {
		driver.findElement(By.id("username")).sendKeys(Username);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(Password);
		driver.findElement(By.xpath("//*[text()=' Login']")).click();
		String Expected = "The Internet";
		String Actual = driver.getTitle();
		assertEquals(Actual, Expected);
		Utility.getScreenshot(driver, "Loggedin");
	}
	
	
//	@Parameters({ "username1", "password1" })
//	@Test
//	public void loginduplicate(String Username1, String Password1) {
//		driver.findElement(By.id("username")).sendKeys(Username1);
//		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(Password1);
//		driver.findElement(By.xpath("//*[text()=' Login']")).click();
//		String Expected = "The Internet";
//		String Actual = driver.getTitle();
//		assertEquals(Actual, Expected);
//		Utility.getScreenshot(driver, "Loggedin");
//	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
