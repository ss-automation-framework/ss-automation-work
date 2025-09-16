package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//private By locators: page objects
	private final By emailID = By.id("input-email");
	private final By password = By.id("input-password");
	private final By loginBtn = By.xpath("//input[@value='Login']");
	private final By forgetPwdLink = By.linkText("Forgotten Password");
	private final By header = By.tagName("h2");
	private final By registerLink = By.linkText("Register");
	//private final By loginErrorMsgInavlidCred = By.cssSelector(".alert.alert-danger.alert-dismissible");
	private final By loginErrorMsg = By.cssSelector(".fa.fa-exclamation-circle");
	
	//public constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	//public page methods/actions
	public String getLoginPageTitle() {
		String title = eleUtil.waitForTitleIs(AppConstants.DEFAULT_SHORT_WAIT, AppConstants.LOGIN_PAGE_TITLE);
		System.out.println("Login Page Title is "+title);
		return title;
	}
	
	public String getLoginPageURL() {
		String url = eleUtil.waitForURLContains(AppConstants.DEFAULT_SHORT_WAIT, AppConstants.LOGIN_PAGE_FRACTION_URL);
		System.out.println("Login Page Title is "+url);
		return url;
	}

	public boolean isForgotPwdLinkExist() {
		return eleUtil.isElementDisplayed(forgetPwdLink);
	}
	
	public boolean isheaderExist() {
		return eleUtil.isElementDisplayed(header);
	}
	
	public AccountsPage doLogin(String username, String pwd) {
		System.out.println("Username: "+username+" Password: "+pwd);
		eleUtil.waitForElementVisible(emailID, AppConstants.DEFAULT_MEDIUM_WAIT).sendKeys(username);
		eleUtil.doSendKeys(password, pwd);
        eleUtil.doClick(loginBtn);    	
		return new AccountsPage(driver);
	}
	
	public boolean doLoginWithInvalidCreds(String invalidUname, String invalidPwd) {
		System.out.println("Username: "+invalidUname+" Password: "+invalidPwd);
		WebElement emailEle = eleUtil.waitForElementVisible(emailID, AppConstants.DEFAULT_MEDIUM_WAIT);
		emailEle.clear();
		emailEle.sendKeys(invalidUname);
		eleUtil.getElement(password).clear();
		eleUtil.doSendKeys(password, invalidPwd);
        eleUtil.doClick(loginBtn);    	
		String text = eleUtil.doGetText(loginErrorMsg);
		System.out.println(text);
		if(text.contains(AppConstants.LOGIN_ERROR_MESG_INVALID_CRED)) {
			return true;
		}
		else if(text.contains(AppConstants.LOGIN_ERROR_BLANK_CRED)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public RegistrationPage navigateToRegistrationPage() {
		eleUtil.waitForElementVisible(registerLink, AppConstants.DEFAULT_SHORT_WAIT).click();
		return new RegistrationPage(driver);
		
	}
	
}
