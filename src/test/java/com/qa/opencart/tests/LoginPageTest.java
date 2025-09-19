package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class LoginPageTest extends BaseTest {
	
	@Test
	public void loginPageTitleTest() {
		String actualTitle = loginPage.getLoginPageTitle();
		ChainTestListener.log("login page title: "+actualTitle);
	    Assert.assertEquals(actualTitle, AppConstants.LOGIN_PAGE_TITLE);
		
	}
	
	@Test
	public void loginPageURLTest() {
		String actualUrl = loginPage.getLoginPageURL();
		ChainTestListener.log("login page url: "+actualUrl);
	    Assert.assertTrue(actualUrl.contains(AppConstants.LOGIN_PAGE_FRACTION_URL));	
	}
	
	@Test
	public void isForgotPwdLinkExist() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExist()); 	
	}
	
	@Test
	public void isLoginPageHeaderExist() {
		Assert.assertTrue(loginPage.isheaderExist()); 	
	}
	
	@Test
	public void loginTest() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}
}
