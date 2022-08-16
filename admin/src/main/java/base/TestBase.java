package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.Markup;

import io.github.bonigarcia.wdm.WebDriverManager;
import utility.ExcelReader;
import utility.ExtentManager;
import utility.Utilities;

public class TestBase {
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static ExcelReader excel = new ExcelReader("./src/main/resources/data/testdata.xlsx");
	public static WebDriverWait wait;
	public static ExtentManager extent;
	public static ExtentTest test;
	public static Actions actions;
	public static String browser;
	public static Logger log = Logger.getLogger(TestBase.class.getName());
	public static JavascriptExecutor js;

	public static WebDriver driver;

	public TestBase() {

		if (driver == null) {

			// load configurations
			try {
				fis = new FileInputStream(".//src/main/resources/properties/config.propeties");
				log.info("Config file found");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				log.info("Config file load error due to " + e.getMessage());
			}
			try {
				config.load(fis);
				log.info("Config file loaded");
			} catch (IOException e1) {
				e1.printStackTrace();
				log.info("Config file load error not found due to " + e1.getMessage());
			}

			// load Object repository
			try {
				fis = new FileInputStream(".//src/main/resources/properties/OR.properties");
				log.info("OBject Repository file found successfully");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				log.info("Object repository file not found due to " + e.getMessage());
			}
			try {
				OR.load(fis);
				log.info("Object Repository file loaded");
			} catch (IOException e) {
				e.printStackTrace();
				log.info("Object repository file load error not found due to " + e.getMessage());
			}

			// jenkins setup browser
			if (System.getenv("browser") != null && !System.getenv("browser").isEmpty()) {
				browser = System.getenv("browser");
				log.info("Browser value " + browser + " received from jenkins");
			} else {
				browser = config.getProperty("browser");
				log.info("Browser value " + browser + "received from config file");
			}
			// if browser value comes from pipeline
			config.setProperty("browser", browser);
			log.info("browser set as " + browser);

			// choosing browser
			
			if (config.getProperty("browser").equals("chrome")) {
				WebDriverManager.chromedriver().setup();
				// setting chrome options
				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("profile.default_content_setting_values.notifications", 2);
				prefs.put("credentials_enable_service", false);
				prefs.put("profile.password_manager_enabled", false);
				ChromeOptions options = new ChromeOptions();
				options.setExperimentalOption("prefs", prefs);
				options.addArguments("--disable-extensions");
				options.addArguments("--disable-infobars");

				driver = new ChromeDriver(options);
				log.info("Chrome driver launched successfully");
			} else if (config.getProperty("browser").equals("edge")) {
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
				log.info("Edge browser launched successfully");
			} else if (config.getProperty("browser").equals("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				log.info("Firefox browser launched successfully");
			}
			
			// choosing browser
			/*
			//using docker
			if (config.getProperty("browser").equals("chrome")) {
				ChromeOptions opt = new ChromeOptions();
				try {
					driver = new RemoteWebDriver(new URL("http://localhost:4444"), opt);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
				log.debug("Chrome driver launched successfully");	
			} else if (config.getProperty("browser").equals("edge")) {
				
			} else if (config.getProperty("browser").equals("firefox")) {
				
			}*/

			// managing window and timeouts
			driver.get(config.getProperty("testsiteurl"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")),
					TimeUnit.SECONDS);
			wait = new WebDriverWait(driver, 5);
			
			// initialize top menu here
		}
	}

	// common interaction

	public static void quit() {
		driver.quit();
	}
	
	public static By findLocatorby(String locator) {
		if (locator.endsWith("_CSS")) {
			return By.cssSelector(OR.getProperty(locator));

		} else if (locator.endsWith("_XPATH")) {
			return By.xpath(OR.getProperty(locator));

		} else if (locator.endsWith("_ID")) {
			return By.id(OR.getProperty(locator));

		} else if (locator.endsWith("_linkText")) {
			return By.linkText(OR.getProperty(locator));
		}
		return null;
	}
	
	public static String findLocatorString(String locator) {
		if (locator.endsWith("_CSS")) {
			return OR.getProperty(locator);

		} else if (locator.endsWith("_XPATH")) {
			return OR.getProperty(locator);

		} else if (locator.endsWith("_ID")) {
			return OR.getProperty(locator);

		} else if (locator.endsWith("_linkText")) {
			return OR.getProperty(locator);
		}
		return null;
	}

	public static void click(String locator) {
		wait.until(ExpectedConditions.presenceOfElementLocated(findLocatorby(locator)));
		driver.findElement(findLocatorby(locator)).click();
//		test.log(Status.INFO, "Clicking on: " + locator);
		log.info("Clicking on: " + locator);
	}
	
	public static void clickCheckbox(String locator) {
		driver.findElement(findLocatorby(locator)).click();
		log.info("Clicking on checkbox: "+ locator);
	}
	
	
	public static void takeaction(String key) {
		actions = new Actions(driver);
		if(key.equalsIgnoreCase("escape")) {
			actions.sendKeys(Keys.ESCAPE).perform();
			log.info("Clicking the escape key");
		}
		
	}
	public static void preessKey(Keys key) {
		actions = new Actions(driver);
		if(key.equals(Keys.HOME)) {
			actions.sendKeys(Keys.HOME).perform();
		}
	}

	public static void type(String locator, String value) {
		wait.until(ExpectedConditions.presenceOfElementLocated(findLocatorby(locator)));		
		driver.findElement(findLocatorby(locator)).sendKeys(value);
//		test.log(Status.INFO, "Typing in: " + locator + " entered value is: " + value);
		log.info("Typing in: " + locator + " entered value is: " + value);
	}

	static WebElement dropDown;

	public static void select(String locator, String value) {
	
		dropDown = driver.findElement(findLocatorby(locator));
		Select selected = new Select(dropDown);
		selected.selectByVisibleText(value);

//		test.log(Status.INFO, "Selecting from dropdown: " + locator + " value as: " + value);
		log.info("Selecting from dropdown: " + locator + " value as: " + value);
	}
	public static void clear(String locator) {

		driver.findElement(findLocatorby(locator)).clear();
//		test.log(Status.INFO, "clearing the: "+ locator);
		log.info("Clearing the auto fill from "+ locator);
	}
	
	public static void switchFrame(String locator) {
		driver.switchTo().frame(findLocatorString(locator));
		log.info("Switching the frame");
	}
	public static void switchParent() {
		driver.switchTo().parentFrame();
		log.info("Switching back to parent frame");
	}
	
	public static void scrollDown() {
		js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0, 500)");
		log.info("scroll down ");
	}
	

	public Boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public static void verifyEquals(String expecetd, String actual) {
		try {
			Assert.assertEquals(actual, expecetd);
		} catch (Throwable t) {
			Utilities.capturePrint();

			// Reportng
			Reporter.log("<br>" + "Verification failure: " + t.getMessage() + "<br>");
			Reporter.log("<a target=\"_blank\" href=" + Utilities.screenshotName + " ><img src="
					+ Utilities.screenshotName + " height=1280 width =720></a>");
			// extent
			test.log(Status.FAIL, "Verification failure: " + t.getMessage());
			test.log(Status.FAIL, (Markup) test.addScreenCaptureFromPath(Utilities.screenshotName));

		}
	}

}
