package com.qa.opencart.pages;

import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;

public class CommonsPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//public constructor
		public CommonsPage(WebDriver driver) {
			this.driver = driver;
			eleUtil = new ElementUtil(driver);
		}

}
