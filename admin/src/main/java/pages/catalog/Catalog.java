package pages.catalog;

import base.TestBase;
import pages.catalog.products.Products;

public class Catalog extends TestBase{
	public Products gotoProducts() {
		click("product_CSS");
		return new Products();
	}
	public void gotoCategories() {
		
	}
	public void gotoManufacturers() {
		
	}
}
