package com.psr.TestCases;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.psr.Utilities.ReadConfig;
import com.psr.Utilities.XLUtils;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import junit.framework.Assert;

public class ReportsTest {
	public static ReadConfig readconfig = new ReadConfig();
	public static XLUtils Excel = new XLUtils();
	public String url = readconfig.getURL();
	public String userName = readconfig.getUsername();
	public String passWord = readconfig.getPassword();

	public static WebDriver driver;
	protected static Logger log;
	public ExtentReports extent;
	public ExtentTest extentTest;
	
	@BeforeTest
	public void before()
	{
	extent = new ExtentReports("test-output/TESTReportPSR.html", true);
	}
	
	@Test
	public void ExportExcel()
	{
		 extentTest = extent.startTest("ExportExcel");
		// extentTest.log(LogStatus.INFO, "testing ExportExcel");
		 Assert.assertTrue(true);
		 extentTest.log(LogStatus.PASS, "ExportExcel TestCase PASS");
	}
	@Test
	public void ImportExcel()
	{
		 extentTest = extent.startTest("ImportExcel");
		// extentTest.log(LogStatus.INFO, "testng ImportExcel");
		 Assert.assertTrue(true);
		 extentTest.log(LogStatus.PASS, "ImportExcel TestCase PASS");
		 
	}

	@Test
	public void Save()
	{
		 extentTest = extent.startTest("Save");
		 extentTest.log(LogStatus.INFO, "Failed Test Case");
		 Assert.assertTrue(false);
		 
	}

	@Test
	public void ShowAndHide()
	{
		 extentTest = extent.startTest("ShowAndHide");
		 extentTest.log(LogStatus.INFO, "Skipped TestCase");
		 extentTest.log(LogStatus.SKIP, "ImportExcel TestCase PASS");
	}

	
	@AfterMethod
	public void After(ITestResult result)
	{
		if(result.getStatus()==ITestResult.FAILURE)
		{
			extentTest.log(LogStatus.FAIL, result.getThrowable());
		}
		extent.endTest(extentTest);
		
		if(result.getStatus()==ITestResult.SKIP)
		{
			extentTest.log(LogStatus.SKIP, result.getThrowable());
		}
		extent.endTest(extentTest);
		 
		
	}
	
	@AfterTest
	public void teardown()
	{
		 extent.flush();
		 extent.close();
	}
}
