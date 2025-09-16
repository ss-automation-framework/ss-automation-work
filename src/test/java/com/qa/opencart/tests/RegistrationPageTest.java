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
			{"FTest1", "LTest1","9898765432","Test1@pwd", "yes"},
			{"FTest2", "LTest2","9898665432","Test2@pwd", "no"},
			{"FTest3", "LTest3","9898665431","Test3@pwd", "yes"},	
	   };
	}

	@Test(dataProvider="getRegistrationData")
	public void registerUser(String fName, String lName, String tel, String pwd, String subscribe) {
		Assert.assertTrue(registrationPage.registerUser(fName,lName, StringUtils.getRandomEmail(), tel,
				pwd, pwd, subscribe));
		
		//add code to save user creds of successful registration
	}
}
