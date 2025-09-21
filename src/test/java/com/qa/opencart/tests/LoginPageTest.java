package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic-101:Login Page Test")
@Feature("F-100:Login Page Features")
@Story("US-99:Login Page Title,URL,Header,forgot Pwd and Login link test")
public class LoginPageTest extends BaseTest {
	
	@Description("Login Page Title Test")
	@Owner("Sweta Sharma")
	@Severity(SeverityLevel.MINOR)
	@Test
	public void loginPageTitleTest() {
		String actualTitle = loginPage.getLoginPageTitle();
		ChainTestListener.log("login page title: "+actualTitle);
	    Assert.assertEquals(actualTitle, AppConstants.LOGIN_PAGE_TITLE);
		
	}
	
	@Description("Login Page URL Test")
	@Owner("Sweta Sharma")
	@Severity(SeverityLevel.MINOR)
	@Test
	public void loginPageURLTest() {
		String actualUrl = loginPage.getLoginPageURL();
		ChainTestListener.log("login page url: "+actualUrl);
	    Assert.assertTrue(actualUrl.contains(AppConstants.LOGIN_PAGE_FRACTION_URL));	
	}
	
	@Description("Login Page Forgot Pwd Test")
	@Owner("Sweta Sharma")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void isForgotPwdLinkExist() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExist()); 	
	}
	
	@Description("Login Page Header Test")
	@Owner("Sweta Sharma")
	@Severity(SeverityLevel.MINOR)
	@Test
	public void isLoginPageHeaderExist() {
		Assert.assertTrue(loginPage.isheaderExist()); 	
	}
	
	@Description("User is able to login to app")
	@Owner("Sweta Sharma")
	@Severity(SeverityLevel.BLOCKER)
	@Test
	public void loginTest() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}
}
