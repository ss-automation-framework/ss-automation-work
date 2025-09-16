package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.exceptions.ElementException;

public class ElementUtil {
	
	private WebDriver driver;

	public ElementUtil(WebDriver driver){
		this.driver=driver;
	}
	
	public void doSendKeys(By locator, String value) {
		if(value==null) {
			throw new ElementException("== Value cannot be null =="); 
		}
		getElement(locator).sendKeys(value);
		
	}
	
	public void doMultipleSendKeys(By locator, CharSequence... value) {
		getElement(locator).sendKeys(value);
		
	}
	
	public void doClick(By locator) {
		getElement(locator).click();
	}
	
	public String doGetText(By locator) {
	 return getElement(locator).getText();
	}
	
	public boolean isElementDisplayed(By locator) {
		try {
		return getElement(locator).isDisplayed();
		}
		catch(NoSuchElementException e) {
			System.out.println("Element is not displayed on the page..."+locator);
			return false;
		}
	}
	
	public boolean isElementDisplayed(WebElement element) {
		try {
		return element.isDisplayed();
		}
		catch(NoSuchElementException e) {
			System.out.println("Element is not displayed on the page..."+element);
			return false;
		}
	}
	
	public boolean isElementEnabled(By locator) {
		try {
		return getElement(locator).isEnabled();
		}
		catch(NoSuchElementException e) {
			System.out.println("Element is not enabled on the page..."+locator);
			//e.printStackTrace();
			return false;
		}
	}
	
	public boolean isMultipleEleOccurencesExist(By locator, int expectedEleCount) {
		if(getElementsCount(locator)==expectedEleCount) {
		System.out.println("the element : " + locator + " is present on the page " + expectedEleCount + " times");
			return true;
		}
		else {
		System.out.println("the element : " + locator + " is not present on the page " + expectedEleCount + " times");	
			return false;
		}
	}
	
	public boolean isElementExist(By locator) {
		if(getElementsCount(locator)==1) {
		System.out.println("The ele: "+locator+" is present on the page");	
			return true;
		}
		else {
		System.out.println("The ele: "+locator+" is not present on the page");		
			return false;
		}
	}
	
	public String getElementDomAttribute(By locator, String attrName) {
		return getElement(locator).getDomAttribute(attrName);
	}
	
	public List<String> getMultipleElementsDomAttribute(By locator, String attrName) {
		List<WebElement> links = getElements(locator);
		List<String> eleLinksList = new ArrayList<String>();
		for(WebElement l : links) {
			String url = l.getDomAttribute(attrName);
			eleLinksList.add(url);
		}
		return eleLinksList;
	}
	
	public String getElementDomProperty(By locator, String propName) {
		return getElement(locator).getDomProperty(propName);
	}
	
	public void clickElement(By locator, String eleText) {
		List<WebElement> eleList = getElements(locator);
		System.out.println("total number of elements: "+ eleList.size());
		
		for(WebElement e : eleList) {
			String text = e.getText();
			System.out.println(text);
				if(text.contains(eleText)) {
					e.click();
					break;
				}
		}
	}

     public void doSearch(By SearchLocator, String SearchText, By suggestionLocator, String suggValue) throws InterruptedException {
    	 doSendKeys(SearchLocator,SearchText);
    	 Thread.sleep(4000);
    	 
    	 clickElement(suggestionLocator,suggValue);	  
    }
	
     
	public WebElement getElement(By locator) {
		return driver.findElement(locator);
	}
	
	public int getElementsCount(By locator) {
		return getElements(locator).size();
	}
	
	public List<String> getElementsTextList(By locator) {
		List<WebElement> eleList = getElements(locator);
		
		List<String> eleTextList = new ArrayList<String>();
		
		for(WebElement e : eleList) {
			String text = e.getText();
			if(text.length()!=0) {
				eleTextList.add(text);
			}
		}
		return eleTextList;
	}
	
	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}
	
	// ***************************Wait Utils*********************************//
	 /**
	   * An expectation for checking that an element is present on the DOM of a page. This does not
	   * necessarily mean that the element is visible.
	   *
	   * @param locator used to find the element
	   * @return the WebElement once it is located
	   */
	public WebElement waitForElementPresence(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	/**
	   * An expectation for checking that an element is present on the DOM of a page and visible.
	   * Visibility means that the element is not only displayed but also has a height and width that is
	   * greater than 0.
	   *
	   * @param locator used to find the element
	   * @return the WebElement once it is located and visible
	   */
	public WebElement waitForElementVisible(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	/**
	   * An expectation for checking that there is at least one element present on a web page.
	   *
	   * @param locator used to find the element
	   * @return the list of WebElements once they are located
	   */
	public List<WebElement> waitForElementsPresence(By locator, int timeout) {
     WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}
	
	/**
	   * An expectation for checking that all elements present on the web page that match the locator
	   * are visible. Visibility means that the elements are not only displayed but also have a height
	   * and width that is greater than 0.
	   *
	   * @param locator used to find the element
	   * @return the list of WebElements once they are located
	   */
	public List<WebElement> waitForElementsVisible(By locator, int timeout) {
     WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
	
	/**An expectation for checking an element is visible and enabled such that you can click it.
	 * @param locator
	 * @param timeout
	 */
	public void clickElementWhenReady(By locator, int timeout) {
	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
	 wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	
	//********************Utils Methods with Fluent Wait***************************//
	public WebElement waitForElementVisibileWithFluentWait(By locator, int timeout, int pollingtime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofSeconds(pollingtime))
				.ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class)
				.withMessage("====Element NOT VISIBLE ON THE PAGE");
			
      return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public WebElement waitForElementPresenceWithFluentWait(By locator, int timeout, int pollingtime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofSeconds(pollingtime))
				.ignoring(NoSuchElementException.class)
				.ignoring(NoSuchElementException.class)
				.withMessage("====Element NOT PRESENT ON THE PAGE");
			
      return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	public void waitForFrameWithFluentWait(By frameLocator, int timeout, int pollingtime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofSeconds(pollingtime))
				.ignoring(NoSuchFrameException.class)
				.withMessage("====FRAME NOT VISIBLE ON THE PAGE");
			
      wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
	}
	
	public Alert waitForAlertWithFluentWait(By alertLocator, int timeout, int pollingtime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofSeconds(pollingtime))
				.ignoring(NoAlertPresentException.class)
				.withMessage("====ALERT NOT VISIBLE ON THE PAGE");
			
      return wait.until(ExpectedConditions.alertIsPresent());
	}
	
	//************************Alert methods with Waits**********************************//
	
	public Alert waitForAlert(int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.alertIsPresent());
		
	}
	
	public String getAlertText(int timeout) {
		return waitForAlert(timeout).getText();
	}
	
	public void acceptAlert(int timeout) {
		 waitForAlert(timeout).accept();
	}
	
	public void dismissAlert(int timeout) {
		 waitForAlert(timeout).dismiss();
	}
	
	public void sendkeysAlert(int timeout, String value) {
		 waitForAlert(timeout).sendKeys(value);
	}
	
	//**********************Title and URL methods with Waits************************//
	
	public String waitForTitleContains(int timeout, String fractionTitleValue) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		
		try {
		wait.until(ExpectedConditions.titleContains(fractionTitleValue));
		}catch(TimeoutException e) {
			System.out.println("expected title value : "+fractionTitleValue+ " is not present");
		}
		
		return driver.getTitle();
	}
	
	public String waitForTitleIs(int timeout, String expectedTitleValue) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		
		try {
		wait.until(ExpectedConditions.titleContains(expectedTitleValue));
		}catch(TimeoutException e) {
			System.out.println("expected title value : "+expectedTitleValue+ " is not present");
		}
		
		return driver.getTitle();
	}
	
	public String waitForURLContains(int timeout, String fractionURLValue) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		
		try {
		wait.until(ExpectedConditions.urlContains(fractionURLValue));
		}catch(TimeoutException e) {
			System.out.println("expected URL value : "+fractionURLValue+ " is not present");
		}
		
		return driver.getCurrentUrl();
	}
	
	public String waitForURLIs(int timeout, String expectedURLValue) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		
		try {
		wait.until(ExpectedConditions.urlContains(expectedURLValue));
		}catch(TimeoutException e) {
			System.out.println("expected URL value : "+expectedURLValue+ " is not present");
		}
		
		return driver.getCurrentUrl();
	}
		
	// *******************Window(s) and Frame(s) methods with Waits*********************//

		public boolean waitForWindows(int expectedNoOfWindows, int timeout) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));

			try {
				return wait.until(ExpectedConditions.numberOfWindowsToBe(expectedNoOfWindows));
			} catch (TimeoutException e) {
				System.out.println("expected No. of Windows are not correct");
				return false;
			}

		}

		public boolean waitForFrame(By frameLoactor, int timeout) {

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));

			try {
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLoactor));
				return true;
			} catch (TimeoutException e) {
				System.out.println("Frame is not present on the page");
				return false;
			}

		}

		public boolean waitForFrame(int frameIndex, int timeout) {

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));

			try {
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
				return true;
			} catch (TimeoutException e) {
				System.out.println("Frame is not present on the page");
				return false;
			}

		}

		public boolean waitForFrame(String frameNameOrID, int timeout) {

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));

			try {
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameNameOrID));
				return true;
			} catch (TimeoutException e) {
				System.out.println("Frame is not present on the page");
				return false;
			}

		}

}
