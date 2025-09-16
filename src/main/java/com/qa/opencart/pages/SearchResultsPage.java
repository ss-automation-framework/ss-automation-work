package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class SearchResultsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	private By searchResults = By.cssSelector("div.product-thumb");
	private By resultsHeader = By.cssSelector("div#content h1");
	
	//add waitForElementPresence method in util and use it
	public List<WebElement> getResultsCount() {
		List<WebElement> searchResultList = eleUtil.waitForElementsPresence(searchResults, AppConstants.DEFAULT_MEDIUM_WAIT);
		System.out.println("results count --> "+searchResultList.size());
		return searchResultList;
	}
	
	public String getResultsHeaderValue() {
		String header = eleUtil.doGetText(resultsHeader);
		System.out.println("results header--> "+header);
		return header;
	}
	
	public ProductDetailPage selectProduct(String productName) {
		System.out.println("product name-->"+productName);
		eleUtil.doClick(By.linkText(productName));
		return new ProductDetailPage(driver);
	}
}
