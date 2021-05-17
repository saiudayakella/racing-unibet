package com.racing.unibet.pages;

import org.openqa.selenium.WebDriver;

public class BasePage {
	protected WebDriver driver;
	
	public BasePage(WebDriver driver) {
		this.driver = driver;
	}
	
	/*
	 * Apart from instantiating driver, 
	 * common functionalities across the pages can be exposed via methods in this page
	 */

}
