package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.StringUtils;

public class RegistrationPageTest extends BaseTest{
	

//BT(launch browser+hit url) --> BC(navigate to registration page) --> Test
	
	@BeforeClass
	public void navigateToRegisterPage() {
		registrationPage = loginPage.navigateToRegistrationPage();
	}

	@DataProvider
	public Object[][] getRegistrationData(){
		return new Object[][] {
			{"FTest7", "LTest7","9899765436","Test7@pwd", "yes"},
			{"FTest8", "LTest8","9899665437","Test8@pwd", "no"},
		  //{"FTest6", "LTest6","9898665439","Test6@pwd", "yes"},	
	   };
	}

	@Test(dataProvider="getRegistrationData")
	public void registerUser(String fName, String lName, String tel, String pwd, String subscribe) {
		Assert.assertTrue(registrationPage.registerUser(fName,lName, StringUtils.getRandomEmail(), tel,
				pwd, pwd, subscribe));
		
		
	}
}
