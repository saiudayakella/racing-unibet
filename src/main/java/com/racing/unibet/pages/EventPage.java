package com.racing.unibet.pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.racing.unibet.pageutils.PageUtilities;

public class EventPage extends BasePage {
	private By runnersTable = By.xpath("//div[contains(@data-test-id,'runners-table')]");
	private By fixedPrices = By
			.xpath("//div[contains(@data-test-id, 'runners-table')]//button[contains(@data-test-id,'Fixed')]");
	
	private By betSlip = By.id("betslip");
	private WebElement runnerElement;	

	public EventPage(WebDriver driver) {
		super(driver);
		PageUtilities.waitForVisibility(driver, 10, runnersTable);
	}
	
	/**
	 * 
	 * @return lowest fixed price from the runners table. If there are duplicate prices,
	 * price which is first in the order will be picked
	 */
	private WebElement getLowestFixedPrice() {
		WebElement runnersTableElement = driver.findElement(runnersTable);

		PageUtilities.scrollElementIntoView(driver, runnersTableElement);

		List<WebElement> fixedPricesElements = driver.findElements(fixedPrices);

		List<Float> selectedEventFixedPrices = new ArrayList<>(fixedPricesElements.size());

		for (int i = 0; i < fixedPricesElements.size(); i++) {
			float fixedPrice = Float.parseFloat(fixedPricesElements.get(i).getText());

			selectedEventFixedPrices.add(fixedPrice);
		}

		float lowestFixedPrice = Collections.min(selectedEventFixedPrices);

		int indexOfLowestFixedPrice = selectedEventFixedPrices.indexOf(lowestFixedPrice);

		return fixedPricesElements.get(indexOfLowestFixedPrice);
	}

	public void selectLowestFixedPrice() {
		WebElement lowestFixedPriceElement = getLowestFixedPrice();

		while (!PageUtilities.isElementInViewport(driver, lowestFixedPriceElement)) {
			PageUtilities.scrollElementIntoView(driver, lowestFixedPriceElement);
			PageUtilities.waitForElementToBeClickable(driver, 10, lowestFixedPriceElement);
		}

		lowestFixedPriceElement.sendKeys(Keys.ENTER);
		
		PageUtilities.waitForVisibility(driver, 5, betSlip);
	}
	
	/**
	 * 
	 * @return lowest fixed price runner details from the runners grid
	 */
	public List<String> getLowestFixedPriceRunnerDetails() {
		WebElement lowestFixedPriceElement = getLowestFixedPrice();
		
		String lowestFixedPrice = lowestFixedPriceElement.getText();
		
		String priceType = lowestFixedPriceElement.getAttribute("data-test-id");
		
		String lowestFixedPriceType = priceType.substring(priceType.indexOf("Fixed"));
		
		WebElement parentElement = lowestFixedPriceElement.findElement(
				By.xpath(".//parent::div"));
		
		String runnerSequenceId = parentElement.getAttribute("data-test-id");
		
		String lowestFixedPriceRunnerSequenceNumber = runnerSequenceId.substring("sequence-".length());
				
		if (lowestFixedPriceType.equalsIgnoreCase("FixedPlace")) {
			runnerElement = parentElement.findElement(
					By.xpath(".//preceding-sibling::div[@data-test-id='" + runnerSequenceId + "'][5]"));
		} else {
			runnerElement = parentElement.findElement(
					By.xpath(".//preceding-sibling::div[@data-test-id='" + runnerSequenceId + "'][4]"));
		}
		
		String runnerName = runnerElement.findElement(
				By.xpath("./div[1]")).getAttribute("data-test-id");
		
		String lowestFixedPriceRunnerName = runnerName.substring(runnerSequenceId.length());
		
		return Arrays.asList(
				lowestFixedPriceRunnerSequenceNumber,
				lowestFixedPriceRunnerName,
				lowestFixedPrice,
				lowestFixedPriceType
		);
		
	}
	
	public class BetSlip {
		private By eventNameOnBetSlip = By.xpath("//div[@data-test-id='bet-card-race-title']");
		private By eventTypeOnBetSlip = By.xpath("//div[@data-test-id='bet-card']//*[name()='svg' and contains(@variant,'race')]");
		private By priceOnBetSlip = By.xpath("//span[contains(@data-test-id, 'bet-odds-price-per-market')]");
		private By priceTypeOnBetSlip = By.xpath("//span[contains(@data-test-id, 'bet-odds-price-type')]");
		private By runnerDetailsOnBetSlip = By.xpath("//div[@data-test-id='bet-history-runner']");
		
		public String getEventNameOnBetSlip() {
			WebElement eventNameElement = driver.findElement(eventNameOnBetSlip);
			return eventNameElement.getText();			
		}
		
		/**
		 * 
		 * @return event type details i.e. event number and name on the betslip
		 */
		public WebElement getEventTypeOnBetSlip() {
			WebElement eventTypeElement = driver.findElement(eventTypeOnBetSlip);
			return eventTypeElement;
		}
		
		/**
		 * 
		 * @return event type details i.e. svg data on the betslip
		 */
		public String getEventTypePath() {
			return getEventTypeOnBetSlip().findElement(
					By.xpath(".//*[name()='path']")).getAttribute("d");
		}
		
		/**
		 * 
		 * @return runner details on the bet slip
		 */
		public String getRunnerDetailsOnBetSlip() {
			WebElement runnerDetailsElement = driver.findElement(runnerDetailsOnBetSlip);
			return runnerDetailsElement.getAttribute("innerHTML");			
		}
		
		/**
		 * 
		 * @return price on the bet slip
		 */
		public String getPriceOnBetSlip() {
			WebElement priceElement = driver.findElement(priceOnBetSlip);
			return priceElement.getText();		
		}
		
		/**
		 * 
		 * @return price type on the bet slip
		 */
		public String getPriceTypeOnBetSlip() {
			WebElement priceTypeElement = driver.findElement(priceTypeOnBetSlip);
			return priceTypeElement.getText().replaceAll("\\p{P}","").replaceAll("\\s+", "");
		}
		
	}
	
	
}
