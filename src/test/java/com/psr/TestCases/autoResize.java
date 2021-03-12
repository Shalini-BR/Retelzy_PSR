package com.psr.TestCases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.awt.AWTException;
import java.io.IOException;

import org.testng.annotations.Test;

import com.psr.Locators.Xpath;
import com.psr.PageObject.BaseClass;
import com.psr.PageObject.Library;
import com.psr.Utilities.XLUtils;

/**
* The autoResize program will reSize all 
* PSR columns according to their respective headers values
* 
* @author  Shalini
* @version PSR 
*/

public class autoResize extends BaseClass{
	@Test
	public void autoResizeColumns() throws InterruptedException, IOException, AWTException {
		try
		{ 
			    Library.print("AUTO RESIZE START");
			
				Library.Interaction.wait_ElementToBeVisible(Xpath.ShowAndHide.clickSearch);
				Library.Interaction.click(Xpath.ShowAndHide.clickSearch);
				Library.print("ClickSearch");
				
				Library.Interaction.wait_ElementToBeInVisible(Xpath.loading.loadingIcon);
				
				Library.Interaction.click(Xpath.ReCalc.AutoResize);
				Library.print("ClickAutoResize");
				
				String Expected = XLUtils.jsonRead("Toaster", "AutoResize");
				String Actual = Library.Interaction.ToasterMessage();
				
				if (Library.checkNullOrEmpty(Expected) && Library.checkNullOrEmpty(Actual) && Actual.equalsIgnoreCase(Expected)) {
					AssertJUnit.assertTrue("Success", Actual.equalsIgnoreCase(Expected));
				} else {
					Library.Interaction.captureScreen(driver, "ReCalc");
					AssertJUnit.fail();
				}
				Library.print("AUTO RESIZE END");
				
		}catch(Exception e)
		{
				System.out.println("Error occured...." + e.getMessage());
				Library.Interaction.captureScreen(driver, "ReCalc");
				AssertJUnit.fail();
		}
}
}