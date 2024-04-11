package commonUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;

public class Utility {

	public static void getScreenshot(WebDriver driver, String name) {

		TakesScreenshot scr = (TakesScreenshot) driver;
		File source = scr.getScreenshotAs(OutputType.FILE);

		String fileName = System.getProperty("user.dir") + "//screenshots//" + name + "_" + System.currentTimeMillis()
				+ ".PNG";
		File dest = new File(fileName);

		try {
			FileUtils.copyFile(source, dest);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
