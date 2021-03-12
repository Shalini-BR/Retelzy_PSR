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
* The DeleteRole program will delete the specified Roles
* 
* @author  Shalini
* @version PSR 
*/

public class DeleteRole extends BaseClass{
	
	int i =3;int j=0;
	String RoleName = Excel.getXLData("./data/EventModelAndCode.xlsx", "sheet4",i,j);
	
	@Test
	public void deleteRoles()
	{
		try
		{
			Library.print("DELETE ROLE");
			
			Library.Interaction.wait_ElementToBeVisible(Xpath.dasboardLinks.dashboard);
			Library.Interaction.click(Xpath.dasboardLinks.dashboard);
			Library.print("CLicked on dashboard");
			
			Library.Interaction.wait_ElementToBeVisible(Xpath.RoleProfileCreation.roleProfile);
			Library.Interaction.click(Xpath.RoleProfileCreation.roleProfile);
			Library.print("CLicked on RoleProfile");
			
			Library.Interaction.sendkeys(Xpath.RoleProfileCreation.searchRoleName, RoleName);
			Library.print("Searched Role name");
			Thread.sleep(2000);
			Library.Interaction.click(Xpath.RoleProfileCreation.clickSearch);
			Library.print("CLicked on Searched button");

			Thread.sleep(2000);
			String jqxid = Library.Interaction.GetJqxID(Xpath.RoleProfileCreation.roleDetailsid);
			
			Library.Interaction.click(Xpath.DeleteRoles.selectRole(jqxid));
			Thread.sleep(2000);
			Library.Interaction.click(Xpath.DeleteRoles.deleteRole);
			Thread.sleep(1000);
			
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