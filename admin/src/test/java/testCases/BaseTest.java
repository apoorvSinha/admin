package testCases;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import base.TestBase;

public class BaseTest extends TestBase {
	@BeforeSuite
	public void baseTest() {
		TestBase base = new TestBase();
	}
	@AfterSuite
	public void quitsession() {
		quit();
	}
}
