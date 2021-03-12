package com.psr.TestCases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.psr.Locators.Xpath;
import com.psr.PageObject.BaseClass;
import com.psr.PageObject.Library;

/**
* The DeleteEventModel program will delete the specified event models
* 
* @author  Shalini
* @version PSR 
*/

public class DeleteEventModel extends BaseClass{
	int i =3;int j=0;
	String ModelName = Excel.getXLData("./data/EventModelAndCode.xlsx", "sheet1",i,j);
	@Test
	public void DeleteEModel()
	{
	try
	{
		Library.print("DELETE EVENT MODEL");
	Library.Interaction.wait_ElementToBeVisible(Xpath.dasboardLinks.dashboard);
	Library.Interaction.click(Xpath.dasboardLinks.dashboard);
	Library.print("CLicked on dashboard");
	
	Library.Interaction.wait_ElementToBeVisible(Xpath.dasboardLinks.admin);
	Library.Interaction.click(Xpath.dasboardLinks.admin);
	Library.print("CLicked on admin");
	
	Library.Interaction.wait_ElementToBeVisible(Xpath.EventModelCreation.eventModelLink);
	Library.Interaction.click(Xpath.EventModelCreation.eventModelLink);
	Library.print("CLicked on Event Model");
	
	Library.Interaction.sendkeys(Xpath.EventModelCreation.modelNameSearchBox, ModelName);
	Library.print("Entered Event Code in search Box");
	
	Thread.sleep(2000);
	Library.Interaction.click(Xpath.EventModelCreation.clickSearchButton);
	Library.print("Clicked On Search Button");
	
	String Jqxid2 = Library.Interaction.GetJqxID(Xpath.EventModelCreation.modelNamesid);
	Thread.sleep(2000);
	
	Library.Interaction.click(Xpath.DeleteEventModel.modelName(Jqxid2));
	
	Library.Interaction.click(Xpath.DeleteEventModel.DeleteEModel);
	Library.print("Deleted Role");
	
	Alert alert = driver.switchTo().alert();
    alert.accept();
	
    String Actual= "Deleted Successfully";
	String Expected = Library.Interaction.ToasterMessage();
	System.out.println("Actual Toast Message :"+Expected);
	System.out.println("Actual Message : "+Actual);
	AssertJUnit.assertEquals(Expected, Actual);
	
	
	
}catch(Exception e)
	{
	
	}
}
}