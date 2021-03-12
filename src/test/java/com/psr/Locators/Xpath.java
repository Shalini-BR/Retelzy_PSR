package com.psr.Locators;

import com.psr.PageObject.Library;
/*
 * This Xpath Class contains all Xpaths related to every module 
 * Seperate classes are created for each and every module
 */
public class Xpath 
{
	public static class loginPage
	{
		public static String userName="//input[@name='username']";
		public static String passWord="//input[@name='password']";
		public static String logIn="//button[@id='login']";
		
		
	}
	
	
	public static class ShowAndHide1
	{
		
	}
	public static class LocationCreation
	{
		public static String merchandising="//a[contains(text(),'Merchandising')]/..";
		public static String create="//li[@class='ng-scope dropdown-submenu']//a[contains(text(),'Create')]";
		public static String location="//a[contains(text(),'Combo')]//../..//a[contains(text(),'Location')]";
		public static String locationID="//label[contains(text(),'Location Id')]/..//input[@class='form-control ng-pristine ng-invalid ng-invalid-required']";
		public static String verifyLocationPage="//h2[contains(text(),'Create Location')]";
		public static String fascia="//list[@name='fascia']//div[@class='list-arrow']";
		public static String fasciaSerch="//list[@name='fascia']//input[@class='ng-pristine ng-valid']";
		public static String fasciaName="//div[contains(text(),'Courts')][position()=1]";
		public static String StoreType="//list[@name='storeType']//div[@class='list-arrow']";
		public static String StoreTypeSearch="//list[@name='storeType']//input[@class='ng-pristine ng-valid']";
		public static String StoteName="//div[contains(text(),'All Courts')][position()=1]";
		
	}
	
	public static class dasboardLinks
	{
		public static String dashboard  = "//a[@class='dshbrd']";
		public static String admin      ="//li[@class='cntrLi hasSubmenuList']//span[contains(text(),'Admin')] ";

	}
	
	public static class EventModelCreation
	{
		public static String eventModelLink="//li[@class='cntrLi hasSubmenuList']//span[contains(text(),'Events Model')] ";
		public static String newButton= "//button[contains(text(),'New')]";
		public static String enterModelName= "//input[@name='modelName']";
		public static String eventModeljqxid="//div[@class ='jqx-grid-main-container']//div";
		public static String selectMatchCase(String jqxid)          {return "//*[@id='row0"+jqxid+"']/div[1]";}
		public static String enterMatchCaseValue (String jqxid)        {return "//*[@id='contenttable"+jqxid+"']/div/div/div";}
		public static String selectDropDownValues (String jqxid) {return "//div[@id='listBoxContentinnerListBoxdropdownlisteditor"+jqxid+"matchCase']/div/child::*";}
		public static String clickMatchingValue(String jqxid)    {return "//div[@id='row0"+jqxid+"']/div[@columnindex='1']";}
		public static String enterMatchingValue(String jqxid)    {return "//div[@id='row0"+jqxid+"']/div[@columnindex='1']/input";}
		public static String buildButton="//Button[contains(text(), Build)]";
		public static String addNewRowButton="//div[@id='addNewRowBtn']";
		public static String eventModelDetailsid ="//div[@class='form-group']/jqxgrid/div";
		public static String eventCode(String jqxid)                 {return "//div[@id='row0"+jqxid+"']/div[@columnindex='0']/div[1]";}
		public static String eventCode1(String jqxid)                 {return "//div[@id='row0"+jqxid+"']/div[@columnindex='0']/div[1]/input";}
		public static String eventdesc="";
		public static String saveButton="//i[contains(text(),' save ')]";
		public static String modelNameSearchBox="//select[@name='modeloperator']/following-sibling::input";
		public static String clickSearchButton="//button[contains(text(),'Search')]";
		public static String allAvailableModels(String jqxid)    {return"//div[@id='contenttable"+jqxid+"']/child::*";}
		public static String modelNamesid="//div[@class='jqx-grid-main-container']/jqxgrid/div";
	}	
	
	public static class DeleteEventModel
	{
		public static String modelName(String jqxid){ return "//div[@id='contenttable"+jqxid+"']/div/div/div/a";}
		public static String DeleteEModel="//button[@class='icn-btn']/i[contains(text(),'delete')]";
	}
	
	
	public static class EventCodeCreation
	{
		public static String eventCodeLink="//li[@class='cntrLi hasSubmenuList']//span[contains(text(),'Events Code')]";
		public static String newButton= "//button[@class='event-code-new']";
		public static String eventCode= "//input[@name='eventcode']";
		public static String eventDesc= "//input[@name='desc']";
		public static String eventCat= "//input[@name='eventcategory']";
		public static String saveButton="//button[contains(text(),'Save')]";
		public static String eCodeSearch="//div[@class='inpt-flds-cmn-cntnr']/select/following-sibling::input";
		public static String eCodeSearchBTN="//button[contains(text(),'Search')]";
		public static String eCodejqxid="//div[@class='jqx-grid-main-container']/jqxgrid/div";
		public static String eCodeDelete="//button[contains(text(), 'Delete')]";
		public static String getXpathOfJqxGridId(String jqxid){return "//*[@id='content" + jqxid + "']/div[2]/div/div/div/div/child::*";}
	}
		
	public static class DeleteEventCode
	{
		public static String deleteEcode="//button[contains(text(),'Delete')]";
	}
	
	public static class RoleProfileCreation
		{
		//public static String ClickDashboard="//a[@class='dshbrd']";
		public static String roleProfile="//a[@routerlink='/role-profile']";
		public static String newButton= "//button[@routerlink='/create-role']";		
		public static String enterRoleName="//input[@name='roleName']";
		public static String enterRoleDesc="//textarea[@name='description']";
		public static String selectAppName="//select[@name='appName']";
		public static String saveButton="//button[contains(text(),'SAVE')]";
		public static String searchRoleName="//select[@name='roleOperator']/following-sibling::input";
		public static String clickSearch="//button[contains(text(),'Search')]";
		public static String roleDetailsid="//div[@class='jqx-grid-main-container']/jqxgrid/div";
		public static String allAvailableRoleNames(String jqxid){ return "//div[@id='row0"+jqxid+"']/div[@columnindex='2']";}
		
	   }
	public static class DeleteRoles
	{
		public static String deleteRole ="//button[contains(text(),' DELETE')]";
		public static String selectRole(String jqxid){return "//div[@id='row0"+jqxid+"']/div/div/div";}
		
	}
	
	
	public static class UserProfileCreation
	{
	//public static String ClickDashboard="//a[@class='dshbrd']";
	public static String userProfile="//a[@routerlink='/user-profile']";
	public static String newButton= "//button[@routerlink='/create-user']";		
	public static String enterUserid="//input[@name='userid']";
	public static String enterUserName="//input[@name='username']";
	public static String enterPassword="//input[@name='password']";
	public static String enterConfirmPassword="//input[@name='confirmPassword']";
	public static String enterEmail="//input[@name='useremail']";
	public static String clickRoleDropdown="//input[@placeholder='Select Role']";
	public static String selectRoleNameValues="//ng-select[@placeholder='Select Role']/div/ul/child::*";
	public static String clickCountryDropDown="//ng-select[@placeholder='Select Country']/div";
	public static String selectCountryNames="//ng-select[@placeholder='Select Country']/div/ul/child::*";
	public static String clickQueryNameDropDown="//ng-select[@placeholder='Select Query Name']/div";
	public static String selectQueryNameValues="//ng-select[@placeholder='Select Query Name']/div/ul/child::*";
	public static String enterBRUserid="//input[@name='brUserID']";
	public static String selectBRUserid="//label[@for='brUserShow'] ";
	public static String selectPasswordNeverExpires="//label[@for='passwordNeverExpire'] ";
	public static String clickSave="//button[contains(text(),'SAVE')]";
	public static String searchUserName="//select[@name='Useroperator']/following-sibling::input ";
	public static String clickSearch="//button[contains(text(),'Search')]";
	public static String userDetailsid="//div[@class='jqx-grid-main-container']/jqxgrid/div";
	public static String allAvailableRoleNames(String jqxid){ return "//div[@id='row0"+jqxid+"']/div[@columnindex='1']";}
	public static String deleteuser="//button[contains(text(),' DELETE')]";
	
}
	
	
	public static class DeleteUsers
	{
	public static String selectUser(String jqxid){ return "//div[@id='row0"+jqxid+"']/div/div/div";}
	public static String deleteButton="//button[contains(text(),' DELETE')]";
	
}
	
	public static class ShowAndHide
	{
	public static String clickSearch="//*[@id='search-fields-from']";
	public static String clickShowHide="//button[contains(text(),'Show Hide Column')]";
	public static String showHideUniqid="//div[@class='jq-listbox-parent']/div/jqxlistbox/div";
	public static String enterColumnName(String jqxid){ return "//div[@id='filter"+jqxid+"']/input";}
	public static String selectValue(String jqxid)    { return "//div[@id='listitem0"+jqxid+"']/div/div/div/span";}
	public static String selectValueClick(String jqxid)    { return "//div[@id='listitem0"+jqxid+"']";}
	public static String enterQueryName="//input[@name='queryName']";
	public static String clickSave="//button[contains(text(),'Save As')]";
	
	public static String SelectValues(String jqxid, int i){ return
			 "//div[@id='listBoxContent"+jqxid+"']/div/div[@id='listitem"+i+""+jqxid+"']";}
	
	
}
	public static class GridValidations
	{
		public static String SearchBar="//span[contains(text(),'Search Fields')]";
		public static String ClickSearch="//button[@id='search-fields-from']";
		public static String GridId= "//*[@id='container']/div/app-staticpsr/div[3]/jqxgrid/div";
	}

	public static class ReCalc
	{
		public static String ReCalc="//span[@class='btn-cntnr-srch-flds']//button[5]";
		public static String SelectRow="//div[@class='jqx-grid-content jqx-grid-content-white jqx-widget-content jqx-widget-content-white']//div[@style='width: 15px; height: 15px;']";
		public static String AutoResize="//button[text()='Auto Resize Columns']";
	}


public static class ChangeTrack
{
	public static String ChangeTrackB ="//button[text()='Change Tracking']";
	public static String Clickfirstoption(String date){ return "//span[text()='PSR Grid modified on "+date+" ']";}	
	public static String data="//div[@role='region']//div//table[1]//td ";
}


public static class loading
{
	public static String loadingIcon ="//span[text()='Loading...']";

}



}
