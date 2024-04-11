package testScripts;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import commonUtils.Utility;

public class LoginUsingExcel extends Utility {

	WebDriver driver;
	Properties prop;

	@DataProvider(name = "config")
	public String[][] getDataFromExcel() throws IOException {

		String path = "C:\\Users\\Ashok\\Desktop\\InputSheet1.xlsx";
		FileInputStream file = new FileInputStream(path);

		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet("config");
		int lastrow = sheet.getLastRowNum();
		String[][] arr = new String[lastrow][];

		for (int i = 0; i <= lastrow; i++) {

			XSSFRow row = sheet.getRow(i);
			if (row.getCell(0).getStringCellValue().equals("Browser")) {
				continue;
			} else {
				String browser = row.getCell(0).getStringCellValue();
				String url = row.getCell(1).getStringCellValue();
				String[] record = { browser, url };
				arr[i - 1] = record;
			}
		}

		file.close();
		workbook.close();
		return arr;
	}

	@DataProvider(name = "loginData")
	public String[][] getDataFromExcelUser() throws IOException {

		String path = "C:\\Users\\Ashok\\Desktop\\InputSheet1.xlsx";
		FileInputStream file = new FileInputStream(path);

		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet("loginData");
		int lastrow = sheet.getLastRowNum();
		String[][] arr = new String[lastrow][];

		for (int i = 0; i <= lastrow; i++) {

			XSSFRow row = sheet.getRow(i);
			if (row.getCell(0).getStringCellValue().equals("Username")) {
				continue;
			} else {
				String browser = row.getCell(0).getStringCellValue();
				String url = row.getCell(1).getStringCellValue();
				String[] record = { browser, url };
				arr[i - 1] = record;
			}
		}

		file.close();
		workbook.close();
		return arr;
	}

//	@Test(dataProvider = "config", priority = 1)
//	public void setup(String browser, String url) throws IOException {
//
//		if (browser.equalsIgnoreCase("chrome")) {
//			driver = new ChromeDriver();
//		} else if (browser.equalsIgnoreCase("edge")) {
//			driver = new EdgeDriver();
//		} else {
//			driver.quit();
//		}
//
//		driver.get(url);
//		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
//
//	}

	@BeforeMethod
	public void setup() {

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

	@Test(dataProvider = "loginData")
	public void login(String Username, String Password) {
		driver.findElement(By.id("username")).sendKeys(Username);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(Password);
		driver.findElement(By.xpath("//*[text()=' Login']")).click();
		String Expected = "The Internet";
		String Actual = driver.getTitle();
		assertEquals(Actual, Expected);
		Utility.getScreenshot(driver, "Loggedin");
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
