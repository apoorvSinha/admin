package rough;

import base.TestBase;
import pages.HomePage;

public class LoginTest {
	public static void main(String[] args) {
		TestBase base = new TestBase();
		HomePage home = new HomePage();
		home.doLogin();
	}
}
