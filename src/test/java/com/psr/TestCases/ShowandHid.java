package com.psr.TestCases;

import java.awt.AWTException;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class ShowandHid {
	static WebDriver driver = new ChromeDriver();
	static WebDriverWait wait = new WebDriverWait(driver, 5000);
	@Test(priority=3)
	public static void ShowAndHide()  throws IOException, GeneralSecurityException, InterruptedException, AWTException {

		driver.get("http://tssdev01web01/psrdev/psr");  //http://psrwin.infogloballink.com:89/psrtest/psr
		driver.manage().window().maximize();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='username']")).sendKeys("RGoswami"); //Randhir_ptl_admin
		driver.findElement(By.xpath("//*[@id='password']")).sendKeys("Welcome@123"); //Igt@1234
		driver.findElement(By.xpath("//*[@id='login']")).click();
		//Thread.sleep(30000);

		WebElement vpo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='vpo']")));
		vpo.sendKeys("154");

	  driver.findElement(By.xpath("//button[@id= 'search-fields-from']")).click();
		
	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='jqx-checkbox-default jqx-checkbox-default-white jqx-fill-state-normal jqx-fill-state-normal-white jqx-rc-all jqx-rc-all-white'])[1]")));
	
	 //System.out.println(" 1 clicked on show and hide");
	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Show Hide Column']"))).click();
	//System.out.println("clicked on show and hide");
	
	FileInputStream fis1 = new FileInputStream("./data/ShowandHide.xlsx"); // Values.xlsx
	XSSFWorkbook workbook1 = new XSSFWorkbook(fis1);
	XSSFSheet sheet1 = workbook1.getSheetAt(0);
	DataFormatter format1 = new DataFormatter();
	int totalRows = sheet1.getPhysicalNumberOfRows();
	for (int j = 0; j < totalRows; j++) {

		String firstCellValue = format1.formatCellValue(sheet1.getRow(j).getCell(0));
		String secondCellValue = format1.formatCellValue(sheet1.getRow(j).getCell(1));
		

	
	Thread.sleep(4000);
	WebElement Columnvaluesearch = driver.findElement(By.xpath("//input[@placeholder='Looking for']"));
	Columnvaluesearch.clear();
	Columnvaluesearch.sendKeys(firstCellValue);
	Thread.sleep(1000);
	
	
	WebElement ColumnValue = driver.findElement(By.xpath("(//div[@role='option']//span[text()='"+secondCellValue+"']/..//SPAN)[1]"));
	
	//System.out.println(ColumnValue);
	String flag = ColumnValue.getAttribute("class");
	
	System.out.println(flag);
	
	Thread.sleep(2000);
	if(flag.contains("jqx-checkbox-check-checked"))
	{
		Thread.sleep(2000);
		//System.out.println("Before");
		driver.findElement(By.xpath("//div[@role='option']//span[text()='"+secondCellValue+"']")).click();
		Thread.sleep(2000);
		//System.out.println("Clcked on the element");
	}
	
	
		
	}	
	
	driver.findElement(By.xpath("//button[contains(text(), 'Update')]")).click();
	System.out.println("No column is hidden");
	driver.quit();
}
}