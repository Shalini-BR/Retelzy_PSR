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
* The DeleteUsers program will delete the specified users
* 
* @author  Shalini
* @version PSR 
*/

public class DeleteUsers extends BaseClass{
	
	int i =3;int j=0;
	String UserName = Excel.getXLData("./data/EventModelAndCode.xlsx", "sheet3",i,++j);
	
	@Test
	public void DeleteUser()
	{
		
	try{
		Library.print("DELETE USER");
		
	Library.Interaction.wait_ElementToBeVisible(Xpath.dasboardLinks.dashboard);
	Library.Interaction.click(Xpath.dasboardLinks.dashboard);
	Library.print("CLicked on dashboard");
	
	Library.Interaction.wait_ElementToBeVisible(Xpath.UserProfileCreation.userProfile);
	Library.Interaction.click(Xpath.UserProfileCreation.userProfile);
	Library.print("CLicked on RoleProfile");
	
	Library.Interaction.sendkeys(Xpath.UserProfileCreation.searchUserName, UserName);
	Library.print("SearchUserName");
	
	Thread.sleep(2000);
	Library.Interaction.click(Xpath.UserProfileCreation.clickSearch);
	Library.print("ClickSearch");
	
	String jqxid = Library.Interaction.GetJqxID(Xpath.UserProfileCreation.userDetailsid);
	Library.print("UserDetailsid");	
	Library.print(jqxid);
	
	Thread.sleep(2000);
	Library.Interaction.click(Xpath.DeleteUsers.selectUser(jqxid));
	
	Library.Interaction.click(Xpath.DeleteUsers.deleteButton);
	Thread.sleep(1000);
	
	Alert alert = driver.switchTo().alert();
    alert.accept();
	
    String Actual= "User Deleted Successfully";
	String Expected = Library.Interaction.ToasterMessage();
	System.out.println("Actual Toast Message :"+Expected);
	System.out.println("Actual Message : "+Actual);
	AssertJUnit.assertEquals(Expected, Actual);
	
	}catch(Exception e)
	{
		e.getMessage();
	}
}
}