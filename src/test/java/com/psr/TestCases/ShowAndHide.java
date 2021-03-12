package com.psr.TestCases;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import java.awt.AWTException;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.psr.Locators.Xpath;
import com.psr.PageObject.BaseClass;
import com.psr.PageObject.Library;

/**
* The ShowAndHide program will hide the specified columns and create new views
* 
* @author  Shalini
* @version PSR 
*/

public class ShowAndHide extends BaseClass{
		@Test
		public void showhide() throws InterruptedException, IOException, AWTException {
			try
			{            Library.print("SHOW AND HIDE");
					Library.Interaction.wait_ElementToBeVisible(Xpath.ShowAndHide.clickSearch);
					Library.Interaction.click(Xpath.ShowAndHide.clickSearch);
					Library.print("ClickSearch");
					Thread.sleep(6000);
					
					Library.Interaction.wait_ElementToBeVisible(Xpath.ShowAndHide.clickShowHide);
					Library.Interaction.click(Xpath.ShowAndHide.clickShowHide);
					Library.print("ClickShowHide");
					
					String jqxid=Library.Interaction.GetJqxID(Xpath.ShowAndHide.showHideUniqid);
					
					for(int i=0;i<4;i++)
					{
						String data = Excel.getXLData("./data/AllColumnsOfPsrGrid.xlsx", "sheet2",i,0);
						System.out.println(data);
						
						Library.Interaction.ClearData(Xpath.ShowAndHide.enterColumnName(jqxid));
						Library.Interaction.sendkeys(Xpath.ShowAndHide.enterColumnName(jqxid), data);
					    Thread.sleep(3000);
					    
					    WebElement status = driver.findElement(By.xpath(Xpath.ShowAndHide.selectValue(jqxid)));
					    //Library.Interaction.print("clicked on Column Value");
					    String s = status.getAttribute("Class");
					   
					    if(!s.contains("jqx-checkbox-check-checked"))
					   Library.Interaction.click(Xpath.ShowAndHide.selectValue(jqxid));
					    Thread.sleep(1000);
					   

					Library.Interaction.ClearData(Xpath.ShowAndHide.enterQueryName);
					Library.Interaction.sendkeys(Xpath.ShowAndHide.enterQueryName, "AllShaliniHidecol");
					Library.print("EnterQueryName");
					Library.Interaction.click(Xpath.ShowAndHide.clickSave);
					Library.print("ClickSave");
					Library.Interaction.ToasterMessage();
				}
			
			}catch(Exception e)
			{
				e.printStackTrace();
		}
			
		}
}

			
