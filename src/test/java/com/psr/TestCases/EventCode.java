package com.psr.TestCases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.psr.Locators.Xpath;
import com.psr.PageObject.BaseClass;
import com.psr.PageObject.Library;

/**
* The EventCode program will create event codes based on the given input
* @author  Shalini
* @version PSR 
*/


public class EventCode extends BaseClass {

	int i = 3;
	int j = 0;
	String ECode = Excel.getXLData("./data/EventModelAndCode.xlsx", "sheet2", i, j);
	String Edesc = Excel.getXLData("./data/EventModelAndCode.xlsx", "sheet2", i, ++j);
	String Ecat = Excel.getXLData("./data/EventModelAndCode.xlsx", "sheet2", i, ++j);

	@Test(priority = 1)
	public void CreateEventCode() throws IOException {
		WebDriverWait wait = new WebDriverWait(driver, 100);
		SoftAssert sa = new SoftAssert();
		try {
			Library.print("EVENT CODE CREATION");
			
			Library.Interaction.wait_ElementToBeVisible(Xpath.dasboardLinks.dashboard);
			Library.Interaction.click(Xpath.dasboardLinks.dashboard);
			Library.print("dashboard");

			Library.Interaction.wait_ElementToBeVisible(Xpath.dasboardLinks.admin);
			Library.Interaction.click(Xpath.dasboardLinks.admin);
			Library.print("admin");

			Library.Interaction.wait_ElementToBeVisible(Xpath.EventCodeCreation.eventCodeLink);
			Library.Interaction.click(Xpath.EventCodeCreation.eventCodeLink);
			Library.print("Ecode");

			Library.Interaction.wait_ElementToBeVisible(Xpath.EventCodeCreation.newButton);
			Library.Interaction.click(Xpath.EventCodeCreation.newButton);
			Library.print("New");

			Library.Interaction.wait_ElementToBeVisible(Xpath.EventCodeCreation.eventCode);
			Library.Interaction.sendkeys(Xpath.EventCodeCreation.eventCode, ECode);
			Library.print("EventCode");

			Library.Interaction.wait_ElementToBeVisible(Xpath.EventCodeCreation.eventDesc);
			Library.Interaction.sendkeys(Xpath.EventCodeCreation.eventDesc, Edesc);
			Library.print("EventCode");

			Library.Interaction.wait_ElementToBeVisible(Xpath.EventCodeCreation.eventCat);
			Library.Interaction.sendkeys(Xpath.EventCodeCreation.eventCat, Ecat);
			Library.print("EventCat");

			
			Library.Interaction.wait_ElementToBeVisible(Xpath.EventCodeCreation.saveButton);
			Library.Interaction.click(Xpath.EventCodeCreation.saveButton);
			Library.print("Save");

			
			
			Library.Interaction.wait_ElementToBeVisible(Xpath.EventCodeCreation.eCodeSearch);
			Library.Interaction.sendkeys(Xpath.EventCodeCreation.eCodeSearch, ECode);
			Library.print("ECodeSearch");
			Thread.sleep(3000);

			Library.Interaction.ToasterMessage();
			
			Library.Interaction.click(Xpath.EventCodeCreation.eCodeSearchBTN);
			Library.print("ECodeSearchBTN");
			Thread.sleep(3000);

			String jqxid = Library.Interaction.GetJqxID(Xpath.EventCodeCreation.eCodejqxid);
			Library.print("ECodejqxid");

			Library.Interaction.GetListAndCheckValue(Xpath.EventCodeCreation.getXpathOfJqxGridId(jqxid), ECode);
			Library.print("ECode created successfully");
			
			

		} catch (Exception e) {
			Library.Interaction.captureScreen(driver, "CreateEventCode");
			AssertJUnit.assertFalse(false);
			log.info("Test Failed");
		}
	}

}
