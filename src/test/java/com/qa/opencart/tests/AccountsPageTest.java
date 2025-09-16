package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.pages.AccountsPage;


public class AccountsPageTest extends BaseTest{
	
	//BT--> BC --> Test
	
	@BeforeClass
	public void accPageSetup() {
	// login page driver is initialized in base test and returned to accounts page from doLogin() of login page	
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void isLogoutLinkExistTest() {
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}
	
	@Test
	public void accPageHeadersTest() {
		List<String> actualHeadersList = accPage.getAccPageHeaders();
		Assert.assertEquals(actualHeadersList.size(), AppConstants.ACC_PAGE_HEADERS_COUNT);
	    Assert.assertEquals(actualHeadersList, AppConstants.expectedAccountPageHeadersList);
	}
	
}
 