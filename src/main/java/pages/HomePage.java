package pages;

import base.TestBase;

public class HomePage extends TestBase {
	
	public void doLogin() {
		clear("email_CSS");
		type("email_CSS", "admin@yourstore.com");
		clear("password_CSS");
		type("password_CSS", "admin");
		click("login_XPATH");
	}
	
}
