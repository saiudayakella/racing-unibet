package com.racing.unibet.tests;

import org.testng.annotations.Test;
import com.racing.unibet.pages.LobbyPage;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LobbyPageTest extends BaseTest {
	protected WebDriver driver = null;
	protected LobbyPage lobbyPage;
	
  @Test
  public void validateLobbyPage() {	  
	  lobbyPage  = new LobbyPage(driver);
	  
	  lobbyPage.selectLastFixedPriceEvent();
	  
	  /*
	   * Any lobby page assertions can be added in this test (or) by adding few other tests.
	   */
  }
  
  @BeforeClass
  public void setup() {
	  driver = getDriver();
  }
  
  @AfterClass
  public void afterClass() {
	  
  }
}
