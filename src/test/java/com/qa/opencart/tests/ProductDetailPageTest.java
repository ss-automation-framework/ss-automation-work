package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class ProductDetailPageTest extends BaseTest {

	//BT(chrome+url) -> BC(login) -> @Test
	
	@BeforeClass
	public void productDetailSetUp() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@DataProvider
	public Object[][] getProducts() {
		return new Object[][] {
			{"macbook", "MacBook Pro"},
			{"Samsung", "Samsung SyncMaster 941BW"},
			{"imac","iMac"},
		};
	}
	

	@DataProvider
	public Object[][] getProductImages() {
		return new Object[][] {
			{"macbook", "MacBook Pro",4},
			{"Samsung", "Samsung SyncMaster 941BW",1},
			{"imac","iMac",3},
		};
	}
	
	@Test(dataProvider="getProducts")
	public void ProductHeaderTest(String searchKey, String productName) {
			 serachResultsPage = accPage.doSearch(searchKey);
			 productInfoPage = serachResultsPage.selectProduct(productName);
			 String actualHeader = productInfoPage.getProductHeader();
			 Assert.assertEquals(actualHeader, productName);
		}
	
	@Test(dataProvider="getProductImages")
	public void ProductImagesCountTest(String searchKey, String productName, int imageCount) {
		serachResultsPage = accPage.doSearch(searchKey);
		 productInfoPage = serachResultsPage.selectProduct(productName);
		 int actImagesCount = productInfoPage.getProductImages();
		 Assert.assertEquals(actImagesCount, imageCount);
	}
	
	//Assngmt: increase product quantity and click on AddToCart btn, land to shopping cart pages
}

