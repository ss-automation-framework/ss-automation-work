package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class LoginPageNegativeTest extends BaseTest {
	
	@DataProvider
	public Object[][] getNegativeLoginData() {
		return new Object[][] {
			{"abc","pwd"},
			{"123","p123"},
			{"",""},
			{"","pwd"},
			{"uname",""},
		};
		
	}

	@Test(dataProvider="getNegativeLoginData")
	public void negativeLoginTest(String uname, String pwd) {
		Assert.assertTrue(loginPage.doLoginWithInvalidCreds(uname, pwd));
	}
}
