package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import com.aventstack.chaintest.plugins.ChainTestListener;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.listeners.TestAllureListener;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductDetailPage;
import com.qa.opencart.pages.RegistrationPage;
import com.qa.opencart.pages.SearchResultsPage;

import io.qameta.allure.Description;

//@Listeners(ChainTestListener.class)
//@Listeners({ChainTestListener.class, TestAllureListener.class})
public class BaseTest {
	
	protected WebDriver driver;
	protected Properties prop;
	DriverFactory df;
	protected LoginPage loginPage;
	protected AccountsPage accPage;
	protected SearchResultsPage serachResultsPage; 
	protected ProductDetailPage productInfoPage;
	protected RegistrationPage registrationPage;
	
	@Description("this method to help launch the browser: {0} and url")
	@Parameters({"browser"})
	@BeforeTest
	public void setUp(String browserName) {
	 df = new DriverFactory();
	 prop = df.initProp();
	 
	 if(browserName!=null) {
		 prop.setProperty("browser", browserName);
	 }
	 driver = df.initDriver(prop);
	 loginPage = new LoginPage(driver);	
	}
	
	
	@AfterMethod
	public void attachScreenshot(ITestResult result) {
		if(!result.isSuccess()) {
			ChainTestListener.embed(DriverFactory.getScreenshotFile(), "image/png");
		}
	}
	
	@Description("this method is to help close the browser")
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
