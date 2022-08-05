package utility;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Hashtable;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;

import base.TestBase;

public class Utilities extends TestBase {
	public static String screenshotPath;
	public static String screenshotName;

	public static void capturePrint() {
		LocalDateTime ldt = LocalDateTime.now();
		screenshotPath = ".//target/surefire-reports/html";
		screenshotName = "errorAt" + ldt.getHour() + "_" + ldt.getMinute() + ".jpeg";

		TakesScreenshot screen = (TakesScreenshot) driver;
		File src = screen.getScreenshotAs(OutputType.FILE);
		File dest = new File(screenshotPath + "/" + screenshotName);
		try {
			FileUtils.copyFile(src, dest);
		} catch (IOException e) {

		}
	}

	@DataProvider(name = "dp")
	public Object[][] getData(Method m) {
		String sheetName = m.getName();
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);
		Object[][] data = new Object[rows - 1][1];
		Hashtable<String, String> table ;

		for (int rowNum = 2; rowNum <= rows; rowNum++) {
			table = new Hashtable<String, String>();
			for (int colNum = 0; colNum < cols; colNum++) {
				table.put(excel.getCellData(sheetName, colNum, 1), excel.getCellData(sheetName, colNum, rowNum));
				data[rowNum - 2][0] = table;
			}
		}
		return data;
	}

	public static boolean isTestRunnable(String testName, ExcelReader excel) {
		String sheetName = "test_suite";
		int rows = excel.getRowCount(sheetName);
		for (int rnum = 2; rnum < rows; rnum++) {
			String testCase = excel.getCellData(sheetName, "TC_ID", rnum);
			if (testCase.equalsIgnoreCase(testName)) {
				System.out.println(testCase);
				System.out.println(testName);
				String runMode = excel.getCellData(sheetName, "Runmode", rnum);
				if (runMode.equalsIgnoreCase("y")) {
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}
}
