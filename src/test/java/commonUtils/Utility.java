package commonUtils;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Utility {
	
	public static void getScreenshot(WebDriver driver, String name) {
		
		TakesScreenshot scr = (TakesScreenshot)driver;
		File source = scr.getScreenshotAs(OutputType.FILE);
		
		String fileName = System.getProperty("user.dir") + "//screenshots//" + name + "_" + System.currentTimeMillis() + ".PNG";
		File dest = new File(fileName);
		
		try {
			FileUtils.copyFile(source, dest);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
