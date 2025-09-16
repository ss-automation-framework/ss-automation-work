package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.SearchResultsPage;

public class SearchTest extends BaseTest {
	
	//BT(chrome+url) -> BC(login) -> @Test
	
	@BeforeClass
	public void searchSetUp() {
		accPage = loginPage.doLogin("march2024@open.com", "Selenium@12345");
	}
	
	@Test
	public void searchTest() {
		 serachResultsPage = accPage.doSearch("macbook");
		 productInfoPage = serachResultsPage.selectProduct("MacBook Pro");
		 String actualHeader = productInfoPage.getProductHeader();
		 Assert.assertEquals(actualHeader, "MacBook Pro");
	}
	
	
}
