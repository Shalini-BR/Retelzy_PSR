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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.psr.Locators.Xpath;
import com.psr.PageObject.BaseClass;
import com.psr.PageObject.Library;

/**
* The eventModelCreation program will create event models based on the given input 
* @author  Shalini
* @version PSR 
*/


public class eventModelCreation extends BaseClass{
	int i =3;int j=0;
	String ModelName = Excel.getXLData("./data/EventModelAndCode.xlsx", "sheet1",i,j);
	String MatchingName = Excel.getXLData("./data/EventModelAndCode.xlsx", "sheet1",i,++j);
	String MatchingValue = Excel.getXLData("./data/EventModelAndCode.xlsx", "sheet1",i,++j);
	String EventCode = Excel.getXLData("./data/EventModelAndCode.xlsx", "sheet1",i,++j);
     
	@Test
	public void createEventModel() throws IOException {
		
		Library.print("EVENT MODEL CREATION");
		String Path="./data/EventModelAndCode.xlsx";
		SoftAssert sa = new SoftAssert();
		try {
			int i =3;
			int j=0;

			Library.Interaction.wait_ElementToBeVisible(Xpath.dasboardLinks.dashboard);
			Library.Interaction.click(Xpath.dasboardLinks.dashboard);
			Library.print("CLicked on dashboard");
			
			Library.Interaction.wait_ElementToBeVisible(Xpath.dasboardLinks.admin);
			Library.Interaction.click(Xpath.dasboardLinks.admin);
			Library.print("CLicked on admin");
			
			Library.Interaction.wait_ElementToBeVisible(Xpath.EventModelCreation.eventModelLink);
			Library.Interaction.click(Xpath.EventModelCreation.eventModelLink);
			Library.print("CLicked on Event Model");
			
			Library.Interaction.wait_ElementToBeVisible(Xpath.EventModelCreation.newButton);
			Library.Interaction.click(Xpath.EventModelCreation.newButton);
			Library.print("CLicked on New Button");
			
			Library.Interaction.sendkeys(Xpath.EventModelCreation.enterModelName, ModelName);
			Library.print("Entered Model Name");
			Thread.sleep(2000);
			
			String jqxid = Library.Interaction.GetJqxID(Xpath.EventModelCreation.eventModeljqxid);
			Library.print("Captured Unique Model Id" + jqxid);
			
			Library.Interaction.wait_ElementToBeVisible(Xpath.EventModelCreation.selectMatchCase(jqxid));
			Library.Interaction.click(Xpath.EventModelCreation.selectMatchCase(jqxid));
			Library.print("Clicked on Please Choose");
		
			Thread.sleep(1000);
			
			Library.Interaction.wait_ElementToBeVisible(Xpath.EventModelCreation.enterMatchCaseValue(jqxid));
			Library.Interaction.click(Xpath.EventModelCreation.enterMatchCaseValue(jqxid));
			Library.print("Clicked on dropdown");
			
			Library.Interaction.wait_ElementToBeVisible(Xpath.EventModelCreation.selectDropDownValues(jqxid));
			Library.Interaction.GetListAndSelectValue(Xpath.EventModelCreation.selectDropDownValues(jqxid), MatchingName);
			Library.print("Selected Drop Down Value");
			
			Thread.sleep(3000);
			
			Library.Interaction.MoveToEle_AndClick(Xpath.EventModelCreation.clickMatchingValue(jqxid));
			Library.print("Clicked on matching values");
			Library.Interaction.MoveToEle_AndEnterData(Xpath.EventModelCreation.enterMatchCaseValue(jqxid), MatchingValue);
			Library.print("Entered Match criteria");
			
			Library.Interaction.click(Xpath.EventModelCreation.buildButton);
			Library.print("Clicked on Build");
	
			Thread.sleep(3000);
			Library.Interaction.wait_ElementToBeVisible(Xpath.EventModelCreation.addNewRowButton);
			Library.Interaction.click(Xpath.EventModelCreation.addNewRowButton);
			Library.print("Clicked On Add New Row Button");
			
			String jqxid1 = Library.Interaction.GetJqxID(Xpath.EventModelCreation.eventModelDetailsid);
			//Thread.sleep(2000);
			Library.Interaction.MoveToEle_AndEnterData(Xpath.EventModelCreation.eventCode(jqxid1),EventCode);
			Library.print("Entered Event Code");

			Library.Interaction.click(Xpath.EventModelCreation.saveButton);
			Library.print("Clicked On save");
			
			Library.Interaction.ToasterMessage();
			
			Library.Interaction.click(Xpath.EventModelCreation.eventModelLink);
			
			Library.Interaction.sendkeys(Xpath.EventModelCreation.modelNameSearchBox, ModelName);
			Library.print("Entered Event Code in search Box");
			
			Thread.sleep(2000);
			Library.Interaction.click(Xpath.EventModelCreation.clickSearchButton);
			Library.print("Clicked On Search Button");
			
			String Jqxid2 = Library.Interaction.GetJqxID(Xpath.EventModelCreation.modelNamesid);
			Thread.sleep(2000);
		    Library.Interaction.GetListAndCheckValue(Xpath.DeleteEventModel.modelName(Jqxid2), ModelName);
			Library.print("model created successfully");
			
			
			
            }catch (Exception e) {
	         Library.Interaction.captureScreen(driver, "EventModelCreation");
	         AssertJUnit.assertFalse(false);
	         log.info("Test Failed");
	         System.out.print(e.getMessage());
}
}
}