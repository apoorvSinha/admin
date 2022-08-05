package testCases;

import org.testng.annotations.Test;

import pages.DashBoard;

public class ProductPage {
	DashBoard dash= new DashBoard();
	
	@Test
	public void productPage() {
		dash.gotoCatalog().gotoProducts();
	}
}
