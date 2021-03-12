/*package com.psr.TestCases;
//
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.psr.Locators.Name;
import com.psr.Locators.Xpath;
import com.psr.PageObject.BaseClass;
import com.psr.PageObject.Library;

public class COS01_LocationCreation extends BaseClass {
	
	String locationId="123";
	String SalesSystemId="6789";
	String name="Agra";
	String ExpectedcreateLocaLabel="Create Location";

	@Test
	public void location() throws IOException {
		try {
			Library.Interaction.userWait();
			Library.Interaction.click(Xpath.LocationCreation.merchandising);
			Library.Interaction.print("User Clicked on Merchandising");
			Library.Interaction.userWait();
			Library.Interaction.moveToElement(Xpath.LocationCreation.create);
			Library.Interaction.print("User Clicked on Create");
			Library.Interaction.userWait();
			Library.Interaction.click(Xpath.LocationCreation.location);
			Library.Interaction.print("User Clicked on Location");
			String createLocationLabel = driver.findElement(By.xpath(Xpath.LocationCreation.verifyLocationPage)).getText();
			Assert.assertEquals(createLocationLabel,ExpectedcreateLocaLabel);
            log.info("User are in Create Location Page");
			} 
		catch (Exception e) {
			captureScreen(driver, "location");
			Assert.assertFalse(false);
			log.info("Test Failed");
		}
     }

	@Test(dependsOnMethods = "location")
	public void createLocationForm() throws IOException {
		try {
			//Library.Interaction.setTextBoxByXpath(Xpath.LocationCreation.locationID,locationId);
			Library.Interaction.print("User is able to enter text to 'Location ID'");
			Library.Interaction.setTextBoxByName(Name.LocationCreation.salesSystemID,SalesSystemId);
			Library.Interaction.print("User is able to enter text to 'Sales System ID'");
			Library.Interaction.setTextBoxByName(Name.LocationCreation.Name,name);
			Library.Interaction.selectAndSerachEle(Xpath.LocationCreation.fascia,Xpath.LocationCreation.fasciaSerch,Xpath.LocationCreation.fasciaName,"Cou");
			Library.Interaction.print("User is able to select 'Facia'");
			Library.Interaction.selectAndSerachEle(Xpath.LocationCreation.StoreType,Xpath.LocationCreation.StoreTypeSearch,Xpath.LocationCreation.StoteName,"All Cou");
			Library.Interaction.print("User is able to select 'Store Type'");
			log.info("User are able to Create Location");
			Thread.sleep(5000);
		} 
		catch (Exception e) {
			captureScreen(driver, "createLocationForm");
			Assert.assertFalse(false);
			log.info("Test Failed");
		}
    }
}
*/