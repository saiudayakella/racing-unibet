package com.racing.unibet.pageutils;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageUtilities {
	/**
	 * 
	 * @param driver
	 * @param element
	 */
	public static void scrollElementIntoView(WebDriver driver, WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	/**
	 * 
	 * @param driver
	 * @param seconds number of seconds to wait for the locator
	 * @param elementLocator
	 */
	public static void waitForVisibility(WebDriver driver, long seconds, By elementLocator) {
		new WebDriverWait(driver, seconds).until(
				ExpectedConditions.visibilityOfElementLocated(elementLocator));
	}
	
	/**
	 * 
	 * @param driver
	 * @param seconds number of seconds to wait for the element to be clickable
	 * @param element
	 */
	public static void waitForElementToBeClickable(WebDriver driver, long seconds, WebElement element) {
		new WebDriverWait(driver, seconds).until(
				ExpectedConditions.elementToBeClickable(element));
	}
	
	/**
	 * 
	 * @param driver
	 * @param seconds number of seconds to wait for the element to disappear
	 * @param element
	 */
	public static void waitForElementInvisbility(WebDriver driver, long seconds, WebElement element) {
		new WebDriverWait(driver, seconds).until(
				ExpectedConditions.invisibilityOf(element));
	}

	/**
	 * @param driver
	 * @param element
	 * @return true/false based on element's visibility in viewport
	 */
	public static Boolean isElementInViewport(WebDriver driver, WebElement element) {
		return (Boolean) ((JavascriptExecutor) driver)
				.executeScript("const elementPosition = arguments[0].getBoundingClientRect();" + "return ("
						+ "elementPosition.top >= 0 &&" + "elementPosition.left >= 0 &&"
						+ "elementPosition.bottom <= (window.innerHeight || document.documentElement.clientHeight) &&"
						+ "elementPosition.right <= (window.innerWidth || document.documentElement.clientWidth)"
						+ ");", element);
	}

	/**
	 * @param zoomType zoomIn/zoomOut
	 * @param numberOfTimes number of times to do the zoom action
	 */
	public static void changeBrowserZoomLevel(String zoomType, int numberOfTimes) {
		int zoomEvent;
		if (zoomType == "ZoomIn") {
			zoomEvent = KeyEvent.VK_ADD;

		} else {
			zoomEvent = KeyEvent.VK_MINUS;

		}
		Robot robot;
		for (int i = 0; i < numberOfTimes; i++) {
			try {
				robot = new Robot();
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(zoomEvent);
				robot.keyRelease(KeyEvent.VK_CONTROL);
				robot.keyRelease(zoomEvent);
			} catch (AWTException e) {
				e.printStackTrace();
			}

		}

	}

}
