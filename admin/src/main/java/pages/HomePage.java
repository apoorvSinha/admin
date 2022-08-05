package pages;

import base.TestBase;

public class HomePage extends TestBase {
	
	public void doLogin() {
		type("email_CSS", "admin@yourstore.com");
		type("password_CSS", "admin");
		click("login_XPATH");
	}
}
