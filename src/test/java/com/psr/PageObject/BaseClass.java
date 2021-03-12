package com.psr.PageObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.psr.Locators.Xpath;
import com.psr.Utilities.ReadConfig;
import com.psr.Utilities.XLUtils;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

/**
 * Every Testcase starts with Base Class It contains logic for opening and
 * closing browser for each testcase
 * 
 * @author Shalini
 * @version PSR
 */

public class BaseClass {
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
	public void setExtent() {
		extent = new ExtentReports("test-output/ShaliniTest.html", true);
		extent.addSystemInfo("Host Name", "Windows");
		extent.addSystemInfo("User Name", "Shalini BR");
		extent.addSystemInfo("Environment", "OVS Automation Testing");
	}

	/*
	 * @AfterTest public void endReport() { extent.flush(); extent.close(); }
	 */

	@BeforeClass
	@Parameters("browser")
	public void launchBrowser(String brouse) throws InterruptedException, IOException {
		log = Logger.getLogger("PSR");
		PropertyConfigurator.configure("log4j.properties");
		if (brouse.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", readconfig.getChromePath());
			driver = new ChromeDriver();
		}

		else if (brouse.equalsIgnoreCase("ie")) {
			System.setProperty("webdriver.ie.driver", readconfig.getIePath());
			driver = new InternetExplorerDriver();
		}

		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.findElement(By.xpath(Xpath.loginPage.userName)).sendKeys(userName);
		driver.findElement(By.xpath(Xpath.loginPage.passWord)).sendKeys(passWord);
		driver.findElement(By.xpath(Xpath.loginPage.logIn)).click();

	}
	
	
	

	@AfterTest
	public void close(ITestResult result) throws IOException {
		/*try {
			if (result.getStatus() == ITestResult.FAILURE) {
				// To add testcase name in extent report
				System.out.println(result.getTestName());
				 extentTest.log(LogStatus.FAIL, "Test Case failed is" +
				 result.getName());
				// To add error/exception in extent report
				 extentTest.log(LogStatus.FAIL, "Test Case failed is" +
				 result.getThrowable());
				extentTest.log(LogStatus.FAIL, result.getName());
				String ScreenshotPath = Library.Interaction.captureScreen(driver, result.getName());
				// To add screenshots in extent report
				extentTest.log(LogStatus.FAIL, extentTest.addScreencast(ScreenshotPath));
			} else if (result.getStatus() == ITestResult.SKIP) {
				extentTest.log(LogStatus.SKIP, "Test Case Skipped is" + result.getName());
			}
			// Ending test and ends the current test and prepare to create html
			// report
		} catch (Exception e) {
			String ScreenshotPath = Library.Interaction.captureScreen(driver, result.getName());
			// To add screenshots in extent report
			extentTest.addScreencast(ScreenshotPath);
			extent.endTest(extentTest);
			extent.flush();
			extent.close();*/
			driver.quit();
		}
	}

