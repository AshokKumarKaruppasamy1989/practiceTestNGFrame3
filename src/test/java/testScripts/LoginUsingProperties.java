package testScripts;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import commonUtils.Utility;

public class LoginUsingProperties {

	WebDriver driver;
	Properties prop;

	@BeforeMethod
	public void setUp() {

		prop = new Properties();
		String path = System.getProperty("user.dir") + "//src//test//resources//configfiles//config.properties";
		FileInputStream file;

		try {

			file = new FileInputStream(path);
			prop.load(file);

		} catch (Exception e) {
			e.getMessage();
		}
		
		String browser = prop.getProperty("browser");
		if (browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else {
			driver.quit();
		}
		
		String url = prop.getProperty("url");
		driver.get(url);
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));

	}
	
	@Test
	public void login() {
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");
		
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
		driver.findElement(By.xpath("//*[text()=' Login']")).click();
		String Expected = "The Internet";
		String Actual = driver.getTitle();
		assertEquals(Actual, Expected);
		Utility.getScreenshot(driver, "LoggedinProperties");
	}
	
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
