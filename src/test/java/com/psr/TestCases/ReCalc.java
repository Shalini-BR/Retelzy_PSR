package com.psr.TestCases;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Date;

import org.testng.annotations.Test;

import com.psr.Locators.Xpath;
import com.psr.PageObject.BaseClass;
import com.psr.PageObject.Library;
import com.psr.Utilities.XLUtils;

import junit.framework.Assert;

/**
 * The ReCalc program will Recalculate all the milestones dates based on
 * triggered days
 * 
 * @author Shalini
 * @version PSR
 */

public class ReCalc extends BaseClass {

	@Test
	public void ReCalc() throws InterruptedException, IOException, AWTException {
		try {
			Library.print("RE-CALC START");

			Library.Interaction.wait_ElementToBeVisible(Xpath.ShowAndHide.clickSearch);
			Library.Interaction.click(Xpath.ShowAndHide.clickSearch);
			Library.print("ClickSearch");

			Library.Interaction.wait_ElementToBeInVisible(Xpath.loading.loadingIcon);
			// Library.Interaction.click(Xpath.ReCalc.SelectRow);
			

			Library.Interaction.click(Xpath.ReCalc.ReCalc);
			Library.print("ClickReCalc");
			//Thread.sleep(1000);

			String Expected = XLUtils.jsonRead("Toaster", "SavedSucceess");
			String Actual = Library.Interaction.ToasterMessage();
			
			if (Library.checkNullOrEmpty(Expected) && Library.checkNullOrEmpty(Actual) && Actual.equalsIgnoreCase(Expected)) {
				Assert.assertTrue("Success", Actual.equalsIgnoreCase(Expected));
			} else {
				Library.Interaction.captureScreen(driver, "ReCalc");
				Assert.fail();
			}

			Library.print("RE-CALC END");

		} catch (Exception e) {
			System.out.println("Error occured...." + e.getMessage());
			Library.Interaction.captureScreen(driver, "ReCalc");
			Assert.fail();
		}
	}

}
