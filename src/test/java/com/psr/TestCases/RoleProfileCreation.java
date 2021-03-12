package com.psr.TestCases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.psr.Locators.Xpath;
import com.psr.PageObject.BaseClass;
import com.psr.PageObject.Library;
	

/**
* The RoleProfileCreation program will create different roles based on given input
* 
* @author  Shalini
* @version PSR 
*/

public class RoleProfileCreation extends BaseClass{

	int i =3;int j=0;
	String RoleName = Excel.getXLData("./data/EventModelAndCode.xlsx", "sheet4",i,j);
	String RoleDesc = Excel.getXLData("./data/EventModelAndCode.xlsx", "sheet4",i,++j);
	String AppName = Excel.getXLData("./data/EventModelAndCode.xlsx", "sheet4",i,++j);
	
	@Test
	public void UserRoleCreation() throws IOException
	{
		Library.print("ROLE PROFILE CREATION");
		try
		{
			Library.Interaction.wait_ElementToBeVisible(Xpath.dasboardLinks.dashboard);
			Library.Interaction.click(Xpath.dasboardLinks.dashboard);
			Library.print("CLicked on dashboard");
			
			Library.Interaction.wait_ElementToBeVisible(Xpath.RoleProfileCreation.roleProfile);
			Library.Interaction.click(Xpath.RoleProfileCreation.roleProfile);
			Library.print("CLicked on RoleProfile");
			
			Library.Interaction.wait_ElementToBeVisible(Xpath.RoleProfileCreation.newButton);
			Library.Interaction.click(Xpath.RoleProfileCreation.newButton);
			Library.print("CLicked on RoleProfile");
			
			Library.Interaction.sendkeys(Xpath.RoleProfileCreation.enterRoleName, RoleName);
			Library.print("Entered RoleName");

			Library.Interaction.sendkeys(Xpath.RoleProfileCreation.enterRoleDesc, RoleDesc);
			Library.print("Entered RoleDesc");
			
			Library.Interaction.SelectDropDownValues(Xpath.RoleProfileCreation.selectAppName, AppName);
			Library.print("Selected PSR");
			
			Library.Interaction.click(Xpath.RoleProfileCreation.saveButton);
			Library.print("CLicked on Save");
			
			Library.Interaction.ToasterMessage();
			

			Library.Interaction.sendkeys(Xpath.RoleProfileCreation.searchRoleName, RoleName);
			Library.print("Searched Role name");
			Thread.sleep(2000);
			Library.Interaction.click(Xpath.RoleProfileCreation.clickSearch);
			Library.print("CLicked on Searched button");

			Thread.sleep(2000);
			String jqxid = Library.Interaction.GetJqxID(Xpath.RoleProfileCreation.roleDetailsid);
			Library.Interaction.GetListAndCheckValue(Xpath.RoleProfileCreation.allAvailableRoleNames(jqxid), RoleName);
			Library.print("Role Created Successfully");
			
			

		}catch(Exception e)
		{
			 AssertJUnit.assertFalse(false);
			  Library.Interaction.captureScreen(driver, "UserRoleCreation");
	          System.out.println(e.getMessage());
	          e.printStackTrace(); 
	          System.out.println(e); 
		}
	}
}
