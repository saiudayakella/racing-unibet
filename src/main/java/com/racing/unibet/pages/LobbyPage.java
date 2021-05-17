package com.racing.unibet.pages;

import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.racing.unibet.pageutils.PageUtilities;

public class LobbyPage extends BasePage {
	private By cookieBotDialog = By.id("CybotCookiebotDialog");
	private By acceptCookieButton = By.id("CybotCookiebotDialogBodyButtonAccept");
	private By raceLauncherContainer = By.id("race-launcher-main-container");
	private By nextToGoList = By.xpath("//div[@data-test-id='ntg-event-list']");
	
	public LobbyPage(WebDriver driver) {
		super(driver);
		PageUtilities.waitForVisibility(driver, 20, raceLauncherContainer);				
	}

	/**
	 * 
	 * @return specific event at particular oder under next to go events
	 */

	private WebElement getEvent(String type, String position) {
		WebElement cookieBotDialogElement = driver.findElement(cookieBotDialog);		

		if (cookieBotDialogElement.isDisplayed()) {
			PageUtilities.waitForElementToBeClickable(driver, 10, cookieBotDialogElement);
			driver.findElement(acceptCookieButton).click();
			PageUtilities.waitForElementInvisbility(driver, 10, cookieBotDialogElement);
			PageUtilities.changeBrowserZoomLevel("ZoomOut", 1);
			PageUtilities.waitForVisibility(driver, 10, nextToGoList);
		}		

		WebElement nextToGoListElement = driver.findElement(nextToGoList);

		List<WebElement> nextToGoEvents = nextToGoListElement.findElements(By.xpath("./a"));

		int priceIndex = 0;

		for (int i = 0; i < nextToGoEvents.size(); i++) {
			if (type.equalsIgnoreCase("FIXED") && position.equalsIgnoreCase("LAST")) {
				if (!nextToGoEvents.get(i).findElements(By.xpath(".//*[name()='svg' and @variant='pricef']")).isEmpty()) {
					priceIndex = i;
				}
				
			}
			
		}

		return nextToGoEvents.get(priceIndex);
	}

	public EventPage selectLastFixedPriceEvent() {
		WebElement lastFixedPriceEvent = getEvent("FIXED", "LAST");

		System.out.println("Last fixed price event details: " + lastFixedPriceEvent.getAttribute("href"));

		while (!PageUtilities.isElementInViewport(driver, lastFixedPriceEvent)) {
			PageUtilities.scrollElementIntoView(driver, lastFixedPriceEvent);
			PageUtilities.waitForElementToBeClickable(driver, 10, lastFixedPriceEvent);
		}

		lastFixedPriceEvent.click();

		return new EventPage(driver);
	}

	/**
	 * 
	 * @return last fixed price event details
	 */
	public List<String> getLastFixedPriceEventDetails() {
		WebElement lastFixedPriceEvent = getEvent("FIXED", "LAST");
		
		String lastFixedPriceEventNumber = lastFixedPriceEvent.findElement(By.xpath(".//p[1]")).getText();;
		
		String lastFixedPriceEventName = lastFixedPriceEvent.findElement(By.xpath(".//p[2]")).getText();;
		
		WebElement lastFixedPriceEventType = lastFixedPriceEvent.findElement(
				By.xpath(".//*[name()='svg' and contains(@variant,'race')]"));
		
		String lastFixedPriceEventTypePath = lastFixedPriceEventType.findElement(
				By.xpath(".//*[name()='path']")).getAttribute("d");
		
		return Arrays.asList(
				lastFixedPriceEventTypePath, 
				lastFixedPriceEventNumber, 
				lastFixedPriceEventName
		);
		
	}
}
