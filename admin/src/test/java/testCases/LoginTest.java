package testCases;

import org.testng.annotations.Test;

import base.TestBase;
import pages.HomePage;

public class LoginTest {
	
	HomePage home = new HomePage();
	@Test
	public void loginTest() {
		home.doLogin();
	}
}
