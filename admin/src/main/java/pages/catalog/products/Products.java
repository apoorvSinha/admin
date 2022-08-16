package pages.catalog.products;

import org.openqa.selenium.Keys;

import base.TestBase;

public class Products extends TestBase{
	String sheetname = "Product";
	public void addnew() {
		int no_of_rows = excel.getRowCount(sheetname);
		for(int i=2; i<=no_of_rows; i++) {
			click("add_new_product_CSS");
			type("productName_XPATH", excel.getCellData(sheetname, "Product name", i));
			type("shortDescription_XPATH", "thin and portable computer");
			switchFrame("switch_frame_ID");
			type("Fulldescription_CSS", "gaming laptop with high performance");
			switchParent();
			type("sku_XPATH", "ABC023");
			click("categories_CSS");
			click("select_categories_CSS");
			click("manufacturers_CSS");
			click("select_manufacturers_CSS");
			scrollDown();
			click("product_tag_CSS");
//			type("product_tag_CSS", "apparel");
			type("GTIN_number_CSS", "ABC34");
			type("manufacturer_part_CSS", "1234");
			click("customer_roles_CSS");
			click("select_customer_roles_CSS");
			takeaction("escape");
			click("limited_to_stores_CSS");
			click("select_limited_to_stores_XPATH");
			takeaction("escape");
			select("vendor_CSS", "Vendor 1");
			scrollDown();
			type("admin_content_CSS", "some content which is irrelavant now");
			click("pre_price_XPATH");
			type("price_XPATH","34");
			click("pre_old_price_XPATH");
			type("old_price_XPATH", "44");
			click("pre_product_cost_XPATH");
			type("product_cost_XPATH", "55");
			clickCheckbox("disable_wishlist_XPATH");
			clickCheckbox("call_for_price_XPATH");
			click("pre_discounts_CSS");
//			click("discounts_CSS");
			takeaction("escape");
			scrollDown();
			click("pre_weight_XPATH");
			type("weight_XPATH", "12.5");
			click("pre_length_XPATH");
			type("length_XPATH", "8.4");
			click("pre_width_XPATH");
			type("width_XPATH", "3.5");
			click("pre_height_XPATH");
			type("height_XPATH", "6.8");
			clickCheckbox("free_shipping_CSS");
			takeaction("save_CSS");
			preessKey(Keys.HOME);
			click("save_CSS");
			
		}
		
	}
	
	public void downloadCatalogasPDF() {
		
	}
	
	public void export(String format, String number) {
		
	}
	
	public void importSheet() {
		
	}
	
	public void delete(String number) {
		
	}
}
