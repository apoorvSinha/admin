package rough;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginTest {
	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver= new ChromeDriver();
		
		driver.get("file:///C:/Users/apoorv/OneDrive/Desktop/admin/admin-demo.nopcommerce.com/login1f43.html");
	//	driver.get("https://www.google.co.in/");
		
	}
}
