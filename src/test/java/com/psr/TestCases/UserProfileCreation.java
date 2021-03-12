package com.psr.TestCases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.psr.Locators.Xpath;
import com.psr.PageObject.BaseClass;
import com.psr.PageObject.Library;

/**
* The UserProfileCreation program will create different users based on the given input
* 
* @author  Shalini
* @version PSR 
*/

public class UserProfileCreation extends BaseClass {
	
	int i =3;int j=0;
	String UserId = Excel.getXLData("./data/EventModelAndCode.xlsx", "sheet3",i,j);
	String UserName = Excel.getXLData("./data/EventModelAndCode.xlsx", "sheet3",i,++j);
	String Password = Excel.getXLData("./data/EventModelAndCode.xlsx", "sheet3",i,++j);
	String CFMPassword = Excel.getXLData("./data/EventModelAndCode.xlsx", "sheet3",i,++j);
	String Email=Excel.getXLData("./data/EventModelAndCode.xlsx", "sheet3",i,++j);
	String Role=Excel.getXLData("./data/EventModelAndCode.xlsx", "sheet3",i,++j);
	String Country=Excel.getXLData("./data/EventModelAndCode.xlsx", "sheet3",i,++j);
	String QueryName= Excel.getXLData("./data/EventModelAndCode.xlsx", "sheet3",i,++j);
    String BRUserID=Excel.getXLData("./data/EventModelAndCode.xlsx", "sheet3",i,++j);
	
   
   
	@Test
	public void CreateUserProfile() throws IOException {
		try {
			Library.print("USER PROFILE CREATION STARTED");
			
			Library.Interaction.wait_ElementToBeVisible(Xpath.dasboardLinks.dashboard);
			Library.Interaction.click(Xpath.dasboardLinks.dashboard);
			Library.print("CLicked on dashboard");
			
			Library.Interaction.wait_ElementToBeVisible(Xpath.UserProfileCreation.userProfile);
			Library.Interaction.click(Xpath.UserProfileCreation.userProfile);
			Library.print("CLicked on RoleProfile");
			
			Library.Interaction.wait_ElementToBeVisible(Xpath.UserProfileCreation.newButton);
			Library.Interaction.click(Xpath.UserProfileCreation.newButton);
			Library.print("CLicked on ClickNew");
			
			Library.Interaction.sendkeys(Xpath.UserProfileCreation.enterUserid, UserId);
			Library.print("EnterUserid");
			
			Library.Interaction.sendkeys(Xpath.UserProfileCreation.enterUserName, UserName);
			Library.print("EnterUserName");
			
			Library.Interaction.sendkeys(Xpath.UserProfileCreation.enterPassword, Password);
			Library.print("EnterPassword");
			
			Library.Interaction.sendkeys(Xpath.UserProfileCreation.enterConfirmPassword, CFMPassword);
			Library.print("EnterConfirmPassword ");
			
			Library.Interaction.sendkeys(Xpath.UserProfileCreation.enterEmail, Email);
			Library.print("EnterEmail");
			
			Library.Interaction.sendkeys(Xpath.UserProfileCreation.clickRoleDropdown,Role);
			Thread.sleep(2000);
			Library.Interaction.GetListAndSelectValue(Xpath.UserProfileCreation.selectRoleNameValues,Role);
			Library.print("ClickRoleDropdown");
			
			Library.Interaction.sendkeys(Xpath.UserProfileCreation.clickCountryDropDown,"BRAZIL");
			Thread.sleep(2000);
			Library.Interaction.GetListAndSelectValue(Xpath.UserProfileCreation.selectCountryNames,Country);
			Library.print("ClickCountryDropDown");
			
			Library.Interaction.sendkeys(Xpath.UserProfileCreation.clickQueryNameDropDown,QueryName);
			Thread.sleep(2000);
			Library.Interaction.GetListAndSelectValue(Xpath.UserProfileCreation.selectQueryNameValues,QueryName);
			Library.print("ClickQueryNameDropDown");
			
			Thread.sleep(2000);
			Library.Interaction.MoveToEle_AndEnterData(Xpath.UserProfileCreation.enterBRUserid, BRUserID);
			//Library.Interaction.sendkeys(Xpath.UserProfileCreation.EnterBRUserid, "V-Bogart");
			Library.print("EnterBRUserid");
			
			Library.Interaction.click(Xpath.UserProfileCreation.selectBRUserid);
			Library.print("SelectBRUserid");
			
			Library.Interaction.click(Xpath.UserProfileCreation.selectPasswordNeverExpires);
			Library.print("SelectPasswordNeverExpires");
		   
			Library.Interaction.click(Xpath.dasboardLinks.dashboard);
			Library.print("ClickDashboard");
			Library.Interaction.click(Xpath.UserProfileCreation.clickSave);
			Library.print("ClickSave");
			
			String Msg = Library.Interaction.ToasterMessage();
			Library.print(Msg);
			
			Library.Interaction.sendkeys(Xpath.UserProfileCreation.searchUserName, UserName);
			Library.print("SearchUserName");
			
			Thread.sleep(2000);
			Library.Interaction.click(Xpath.UserProfileCreation.clickSearch);
			Library.print("ClickSearch");
			
			String jqxid = Library.Interaction.GetJqxID(Xpath.UserProfileCreation.userDetailsid);
			Library.print("UserDetailsid");	
			
			Thread.sleep(2000);
			Library.Interaction.GetListAndCheckValue(Xpath.UserProfileCreation.allAvailableRoleNames(jqxid),UserName);
			Library.print("ClickSearch");
			
			
			
		} catch (Exception e) {
		  AssertJUnit.assertFalse(false);
		  Library.Interaction.captureScreen(driver, "CreateUserProfile");
          System.out.println(e.getMessage());
          e.printStackTrace(); 
          System.out.println(e); 
          
		}
	}
}
