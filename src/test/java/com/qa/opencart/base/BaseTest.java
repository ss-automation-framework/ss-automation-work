package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import com.aventstack.chaintest.plugins.ChainTestListener;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductDetailPage;
import com.qa.opencart.pages.RegistrationPage;
import com.qa.opencart.pages.SearchResultsPage;

@Listeners(ChainTestListener.class)
public class BaseTest {
	
	protected WebDriver driver;
	protected Properties prop;
	DriverFactory df;
	protected LoginPage loginPage;
	protected AccountsPage accPage;
	protected SearchResultsPage serachResultsPage; 
	protected ProductDetailPage productInfoPage;
	protected RegistrationPage registrationPage;
	
	
	@BeforeTest
	public void setUp() {
	 df = new DriverFactory();
	 prop = df.initProp();
	 driver = df.initDriver(prop);
	 loginPage = new LoginPage(driver);	
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
