package com.psr.TestCases;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class ImportExcelForAllColumns {
	static WebDriver driver = new ChromeDriver();	
	static   WebDriverWait wait = new WebDriverWait(driver,100); 
	 static String  ViewName=null;
	   static String filepath="PSR_2020-9-30_2_32_13_987.xlsm";
	   static String firstCellValue= null;
	   static String header =null;
       static String value = null;
       static String gridvalue = null;
       static String gridheader= null;
       static WebElement scrillInside = null;
       static String jqxGridId = null;
       
       static LinkedHashMap<String, String> excel_data = new LinkedHashMap<String, String>();
       static LinkedHashMap<String, String> grid_data = new LinkedHashMap<String, String>();
       @Test(priority=1)
	public static void ImportExcel()	 throws IOException, GeneralSecurityException, InterruptedException, AWTException
	{
		
    	System.out.println("Started import excel execution");
		driver.get("http://psrwin.infogloballink.com:89/psrtest/psr");
		driver.manage().window().maximize();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='username']")).sendKeys("Randhir_ptl_admin");
		driver.findElement(By.xpath("//*[@id='password']")).sendKeys("Igt@1234");
		driver.findElement(By.xpath("//*[@id='login']")).click();
		Thread.sleep(30000);
		
		WebElement vpo =	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='vpo']")));
		vpo.sendKeys("123");
		
		driver.findElement(By.xpath("//button[@id= 'search-fields-from']")).click();
		ViewName =  driver.findElement(By.xpath("(//div[@class='ui-select-match ng-star-inserted']//span)[2]")).getText();
		System.out.println("VIEW NAME := " + ViewName);
		
		 scrillInside = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='container']/div/app-staticpsr/div[3]/jqxgrid/div")));
		 jqxGridId = scrillInside.getAttribute("id");
		
		 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Excel Operation')]"))).click();
  		driver.findElement(By.xpath("//a[contains(text(),'Import to PSR')]")).click();
  		Thread.sleep(4000);
  		
  		 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Choose File')]"))).click();
  		Thread.sleep(3000);
  		
  		
  		 StringSelection s = new StringSelection("PSR_2020-9-30_2_32_13_987");
  	    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(s, null);
  	    Robot robot = new Robot();
  	    robot.keyPress(java.awt.event.KeyEvent.VK_ENTER);
  	    robot.keyRelease(java.awt.event.KeyEvent.VK_ENTER);
  	    robot.keyPress(java.awt.event.KeyEvent.VK_CONTROL);
  	    robot.keyPress(java.awt.event.KeyEvent.VK_V);
  	    robot.keyRelease(java.awt.event.KeyEvent.VK_CONTROL);
  	    Thread.sleep(3000);
  	    robot.keyPress(java.awt.event.KeyEvent.VK_ENTER);
  		
  		Thread.sleep(3000);
  		driver.findElement(By.xpath("//button[contains(text(),'Upload Excel')]")).click();
  		Thread.sleep(3000);

  		

  		

  		
  		
		
		// Get excel values
	FileInputStream fs = new FileInputStream("C:\\Users\\Shalini\\PSR_2020-9-30_2_32_13_987.xlsm");
	// Creating a workbook
	@SuppressWarnings("resource")
	XSSFWorkbook workbook3 = new XSSFWorkbook(fs);
	XSSFSheet sheet3 = workbook3.getSheetAt(0);

	FileInputStream fis1 = new FileInputStream("./data/3records.xlsx"); //Importexcel.xlsx
	XSSFWorkbook workbook1 = new XSSFWorkbook(fis1);
	XSSFSheet sheet1 = workbook1.getSheetAt(0);
	DataFormatter format1 = new DataFormatter();

	int totalRows = sheet1.getPhysicalNumberOfRows();

	for(int j = 0;j<totalRows;j++)
	{

		String firstCellValue = format1.formatCellValue(sheet1.getRow(j).getCell(1));

		// System.out.println(" first Cell Value"+firstCellValue);

		 if(firstCellValue!=null && !firstCellValue.equalsIgnoreCase("")){
	    	  
	    	  header = format1.formatCellValue(sheet3.getRow(0).getCell(Integer.parseInt(firstCellValue)));
	    	  value = format1.formatCellValue(sheet3.getRow(1).getCell(Integer.parseInt(firstCellValue)));
	    	// System.out.println("Export Excel value: " + header + " = " + value);
	    	
		 	excel_data.put( header,value.trim());
			
		}
		
	}
	
	
	runTest();
	
	System.out.println("VALUES COMPARED FROM EXCEL AND GRID : " + excel_data.equals(grid_data));  //true
 	
 	System.out.println("COMPARISION :"+ new ArrayList<String>( excel_data.values()).equals(new ArrayList<String>( grid_data.values() )) ); 
 	
 	//System.out.println("excel data"+excel_data);
 //	System.out.println("grid  data"+grid_data);
 	driver.quit();

}
	
	
	public static void runTest() throws IOException, InterruptedException, AWTException
	{
		
		
		FileInputStream fis = new FileInputStream("./data/3records.xlsx"); // Importexcel.xlsx
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("Sheet5");
		DataFormatter format = new DataFormatter();
		for (Row myrow : sheet) {
			int i = 0;
			Thread.sleep(3000);

			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='dropdownscroll']/div/div[2]/span")));
			element.click();
			List<WebElement> suggestion = driver.findElements(
					By.xpath("//ul[@role = 'menu']//li//descendant::div[@class='ui-select-choices-row']"));

			 firstCellValue = format.formatCellValue(myrow.getCell(i));

			for (WebElement suggest : suggestion) {
				gridheader = suggest.getText();
				if (gridheader.trim().equalsIgnoreCase(format.formatCellValue(myrow.getCell(i)))) {
					//System.out.println(suggest.getText() + " equals " + myrow.getCell(i).getStringCellValue());
					Thread.sleep(1000);
					
					suggest.click();
					break;
				}
			}
			Thread.sleep(1000);
					
					runcopy(driver, jqxGridId, format.formatCellValue(myrow.getCell(++i)),
							format.formatCellValue(myrow.getCell(++i)), firstCellValue); 
		
				}
		//System.out.println("finished reading values ");
			}
		


		
	public static boolean checkHeader(String header, String value) {
		return header.trim().equalsIgnoreCase(value);
	}

	private static void runcopy(WebDriver driver, String jqxGridId, String string, Object object, String header)
			throws InterruptedException, IOException, AWTException {
		Robot robot = new Robot();
		WebDriverWait wait = new WebDriverWait(driver, 100);
		List<String> sendKeysList = new ArrayList();

		try {
			
			for (int i = 0; i < 1; i++) { // int i = 0; i < 3; i++
				Actions a = new Actions(driver);
				WebElement ndc = wait.until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath("//*[@id='row" + i + jqxGridId + "']/div[" + string + "]")));
				
				String gridvalue = ndc.getText();
				// System.out.println("Grid Data: " + gridheader + " = " + gridvalue);
				//System.out.println("gridvalue is " + gridvalue);
				grid_data.put(gridheader,gridvalue.trim());
				//System.out.println(grid_data);
				
			}
			
			
		}
		
			catch (Exception e) {
				System.out.println(e.getMessage());
			}	
			
	}		

}
