package com.racing.unibet.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;


public class BaseTest {
	private WebDriver driver = null;
	
	/**
	 * 
	 * @return instance of driver
	 */
	public WebDriver getDriver() {
		return driver;
	}
	
	@Parameters({ "browserType", "appURL" })
	@BeforeClass
	public void initializeDriver(String browserType, String appURL) {
		switch (browserType) {
		case "Chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case "Firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		case "Safari":
			driver = new SafariDriver();
			break;
		default:
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
			
		}
		
		driver.manage().window().maximize();
		driver.get(appURL);		
	}

	@AfterClass
	public void tearDown() {		
		if (driver != null) {
			//driver.quit();
		}
		
		
	}

}
