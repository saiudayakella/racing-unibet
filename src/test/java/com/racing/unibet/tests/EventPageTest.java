package com.racing.unibet.tests;

import org.testng.annotations.Test;

import com.racing.unibet.pages.EventPage;
import com.racing.unibet.pages.LobbyPage;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;

public class EventPageTest extends BaseTest {
	protected WebDriver driver = null;
	protected LobbyPage lobbyPage;
	protected EventPage eventPage;
	protected EventPage.BetSlip betSlip;

	@Test
	public void validateBetCard() {
		lobbyPage  = new LobbyPage(driver);
		
		List<String> eventDetails = lobbyPage.getLastFixedPriceEventDetails();
		
		String expectedEventTypePath = eventDetails.get(0);
		
		String expectedEventNumber = eventDetails.get(1);
		
		String expectedEventName = eventDetails.get(2);
		
		eventPage = lobbyPage.selectLastFixedPriceEvent();
		
		List<String> runnerDetails = eventPage.getLowestFixedPriceRunnerDetails();
		
		String expectedRunnerSequenceNumber = runnerDetails.get(0);
		
		String expectedRunnerName = runnerDetails.get(1);
		
		String expectedPrice = runnerDetails.get(2);
		
		String expectedPriceType = runnerDetails.get(3);		
		
		eventPage.selectLowestFixedPrice();
		
		betSlip = eventPage.new BetSlip();
		
		Assert.assertEquals(betSlip.getEventTypePath(), expectedEventTypePath, 
				"Event type icon on the bet slip does not match with the selected event");
		
		Assert.assertTrue(betSlip.getEventNameOnBetSlip().equalsIgnoreCase(expectedEventNumber+' '+expectedEventName), 
				"Event name on the bet slip does not match with the selected event");
		
		Assert.assertEquals(betSlip.getPriceOnBetSlip(), expectedPrice, 
				"Price on the bet slip does not match with the selected price");
		
		Assert.assertEquals(betSlip.getRunnerDetailsOnBetSlip(), expectedRunnerSequenceNumber
				+".  "+expectedRunnerName, "Runner details on the bet slip does not match the selected runner in the grid");

		Assert.assertEquals(betSlip.getPriceTypeOnBetSlip(), expectedPriceType, 
				"Price type on the bet slip does not match");	
	}

	@BeforeClass
	public void setup() {
		driver = getDriver();
	}

}
