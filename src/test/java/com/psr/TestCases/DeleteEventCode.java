package com.psr.TestCases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.Alert;
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
import com.relevantcodes.extentreports.LogStatus;

/**
* The DeleteEventCode program will delete the specified event codes
* 
* @author  Shalini
* @version PSR 
*/

public class DeleteEventCode extends BaseClass {
	@Test
	public void DeleteECode()
	{
		 extentTest = extent.startTest("ClientDemo");
		 extentTest.log(LogStatus.INFO, "testig extent report");
	}
		
	/*try
	{ Library.print("DELETE EVENT CODE ");
		Library.Interaction.wait_ElementToBeVisible(Xpath.dasboardLinks.dashboard);
		Library.Interaction.click(Xpath.dasboardLinks.dashboard);
		Library.print("dashboard");

		
		Library.Interaction.wait_ElementToBeVisible(Xpath.dasboardLinks.admin);
		Library.Interaction.click(Xpath.dasboardLinks.admin);
		Library.print("admin");
		
		Library.Interaction.wait_ElementToBeVisible(Xpath.EventCodeCreation.eventCode);
		Library.Interaction.click(Xpath.EventCodeCreation.eventCode);
		Library.print("Ecode");
		
		Library.Interaction.wait_ElementToBeVisible(Xpath.EventCodeCreation.eCodeSearch);
		Library.Interaction.sendkeys(Xpath.EventCodeCreation.eCodeSearch, "z001");
		Library.print("ECodeSearch");
		Thread.sleep(2000);
		
		Library.Interaction.click(Xpath.EventCodeCreation.eCodeSearchBTN);
		Library.print("ECodeSearchBTN");
		Thread.sleep(3000);
		
		
		String jqxid = Library.Interaction.GetJqxID(Xpath.EventCodeCreation.eCodejqxid);
		Library.Interaction.click(Xpath.EventCodeCreation.getXpathOfJqxGridId(jqxid));
		Library.print("selected role to delete");
		Thread.sleep(3000);
		
		Library.Interaction.click(Xpath.DeleteEventCode.deleteEcode);
		Library.print("Deleted Event Code");
		
		Alert alert = driver.switchTo().alert();
        alert.accept();
		
        String Actual= "Deleted Successfully";
		String Expected = Library.Interaction.ToasterMessage();
		System.out.println("Actual Toast Message :"+Expected);
		System.out.println("Actual Message : "+Actual);
		AssertJUnit.assertEquals(Expected, Actual);
		Assert.assertEquals("S", "S");
		extentTest.log(LogStatus.PASS, "Test case passed");
		extentTest.log(LogStatus.PASS, "Test case passed");
		
		
}catch(Exception e)
	{
	System.out.println(e.getMessage());
	}

}
*/}
