package com.psr.TestCases;

import org.testng.annotations.Test;

import com.psr.PageObject.BaseClass;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import java.awt.AWTException;
import java.awt.Robot;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class ExportExcelForAllColumns	{

	static WebDriver driver = new ChromeDriver();
	static String filepath = null;
	static String ViewName = null;
	static String header = null;
	static String value = null;
	static String gridvalue = null;
	static String gridheader = null;
	static String firstCellValue = null;
	static WebDriverWait wait = new WebDriverWait(driver, 100);
	static LinkedHashMap<String, String> excel_data = new LinkedHashMap<String, String>();
	static LinkedHashMap<String, String> grid_data = new LinkedHashMap<String, String>();

	static String jqxGridId = null;
	static WebElement scrillInside = null;

	// String ViewName = null;
//	@SuppressWarnings({ "unchecked", "rawtypes", "unused", "static-access" })
	@Test(priority=1)
	public static void ExportExcel() throws IOException, GeneralSecurityException, InterruptedException, AWTException {

		System.out.println("Export Excel execution started");
		driver.get("http://psrwin.infogloballink.com:89/psrtest/psr");  //http://psrwin.infogloballink.com:89/psrtest/psr http://tssdev01web01/psrdev/psr
		driver.manage().window().maximize();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='username']")).sendKeys("Randhir_ptl_admin"); //Randhir_ptl_admin roho
		driver.findElement(By.xpath("//*[@id='password']")).sendKeys("Igt@1234"); //Igt@1234 Welcome@123
		driver.findElement(By.xpath("//*[@id='login']")).click();
		Thread.sleep(30000);

		WebElement vpo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='vpo']")));
		vpo.sendKeys("154");

		driver.findElement(By.xpath("//button[@id= 'search-fields-from']")).click();
		ViewName = driver.findElement(By.xpath("(//div[@class='ui-select-match ng-star-inserted']//span)[2]"))
				.getText();
		System.out.println("VIEW NAME := " + ViewName);

		// copy paste

		scrillInside = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[@id='container']/div/app-staticpsr/div[3]/jqxgrid/div")));
		jqxGridId = scrillInside.getAttribute("id");

		FileInputStream fis = new FileInputStream("./data/3records.xlsx"); // Values.xlsx
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("Sheet5");
		DataFormatter format = new DataFormatter();
		for (Row myrow : sheet) {
			int i = 0;
			Thread.sleep(1000);

			WebElement element = driver.findElement(By.xpath("//*[@id='dropdownscroll']/div/div[2]/span"));
			element.click();
			List<WebElement> suggestion = driver.findElements(
					By.xpath("//ul[@role = 'menu']//li//descendant::div[@class='ui-select-choices-row']"));

			firstCellValue = format.formatCellValue(myrow.getCell(i));

			for (WebElement suggest : suggestion) {
				gridheader = suggest.getText();
				if (gridheader.trim().equalsIgnoreCase(format.formatCellValue(myrow.getCell(i)))) {
					// System.out.println(suggest.getText() + " equals " +
					// myrow.getCell(i).getStringCellValue());
					Thread.sleep(1000);
					suggest.click();

					//System.out.println("Grid Header = " + gridheader);
					break;
				}
			}
			Thread.sleep(1000);

			runCopyPaste(driver, jqxGridId, format.formatCellValue(myrow.getCell(++i)),
					format.formatCellValue(myrow.getCell(++i)), firstCellValue);

		}
		//System.out.println("finished values entry");

		// save code ................................................................................................................
		// driver.findElement(By.xpath("//*[@id='save-changes-psr']")).click();

		WebElement toastmessage = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='toastr-container']/div/div/span")));
		//System.out.println("toastmessage is " + toastmessage.getText());

		Thread.sleep(4000);

		String Gridvaluetest = wait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='row0" + jqxGridId + "']/div[5]/div")))
				.getText();
		//System.out.println(Gridvaluetest);

		runTest();
		Thread.sleep(2000);

		if (ViewName.equalsIgnoreCase("Show All columns (Public)")) {

			wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Excel Operation')]")))
					.click();
			driver.findElement(By.xpath("//a[contains(text(),'Export to Excel')]")).click();
			Thread.sleep(5000);
		} else {
			//System.out.println("Please Check the View Name!");
			//System.out.println("By Default Show All columns (Public view) should be set");
			//System.out.println("Current view is " + ViewName);
		}

		// code to get all editable field values from excel sheet

		File dir = new File("C:\\Users\\Shalini\\Downloads");
		File[] files = dir.listFiles();
		Arrays.sort(files, new Comparator() {
			public int compare(Object o1, Object o2) {
				return compare((File) o1, (File) o2);
			}

			private int compare(File f1, File f2) {
				long result = f2.lastModified() - f1.lastModified();
				if (result > 0) {
					return 1;
				} else if (result < 0) {
					return -1;
				} else {
					return 0;
				}
			}
		});

		List<File> filename = Arrays.asList(files[0]);

		Iterator<File> itr = filename.iterator();
		while (itr.hasNext()) {
			filepath = itr.next().toString();
			System.out.println("FILE NAME := " + filepath);
		}

		FileInputStream fs = new FileInputStream(filepath);
		// Creating a workbook
		@SuppressWarnings("resource")
		XSSFWorkbook workbook3 = new XSSFWorkbook(fs);
		XSSFSheet sheet3 = workbook3.getSheet("hiddendata");

		FileInputStream fis1 = new FileInputStream("./data/3records.xlsx"); // Values.xlsx
		XSSFWorkbook workbook1 = new XSSFWorkbook(fis1);
		XSSFSheet sheet1 = workbook1.getSheetAt(0);
		DataFormatter format1 = new DataFormatter();

		int totalRows = sheet1.getPhysicalNumberOfRows();

		for (int j = 0; j < totalRows; j++) {

			String firstCellValue = format1.formatCellValue(sheet1.getRow(j).getCell(1));

			// System.out.println(" first Cell Value"+firstCellValue);

			if (firstCellValue != null && !firstCellValue.equalsIgnoreCase("")) {

				header = format1.formatCellValue(sheet3.getRow(0).getCell(Integer.parseInt(firstCellValue)));
				value = format1.formatCellValue(sheet3.getRow(1).getCell(Integer.parseInt(firstCellValue)));
				//System.out.println("Export Excel value: " + header + " = " + value);

				excel_data.put(header, value);

			}

		}

		//System.out.println("ecel data from hashmap " + excel_data);
		//System.out.println("grid data from haspmap " + grid_data);
		// compare hashmaps

		System.out.println("VALUES COMPARED FROM EXCEL AND GRID : " + excel_data.equals(grid_data)); // true

		System.out.println("COMPARISION :"
				+ new ArrayList<String>(excel_data.values()).equals(new ArrayList<String>(grid_data.values())));

		driver.quit();
	}

	@SuppressWarnings("unchecked")
	private static void runCopyPaste(WebDriver driver, String jqxGridId, String string, Object object, String header)
			throws InterruptedException, IOException, AWTException {
		Robot robot = new Robot();
		WebDriverWait wait = new WebDriverWait(driver, 100);
		List<String> sendKeysList = new ArrayList();

		try {

			for (int i = 0; i < 1; i++) { // int i = 0; i < 3; i++
				Actions a = new Actions(driver);
				WebElement ndc = wait.until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath("//*[@id='row" + i + jqxGridId + "']/div[" + string + "]")));

				if (checkHeader(header, "Tech Pack Received Date") || checkHeader(header, "packaging ready date")
						|| checkHeader(header, "fabric pp date") || checkHeader(header, "fabric pp date")
						|| checkHeader(header, "cpo acc date by vendor") || checkHeader(header, "sample merch eta")
						|| checkHeader(header, "sample floor set eta") || checkHeader(header, "sample dcom eta")
						|| checkHeader(header, "sample mailer eta")
						|| checkHeader(header, "Photo/Merchant Sample Send date")
						|| checkHeader(header, "Photo/Merchant Sample ETA date")
						|| checkHeader(header, "Photo/Merchant Sample Send date")
						|| checkHeader(header, "Marketing Sample Send date")
						|| checkHeader(header, "Marketing Sample ETA date")
						|| checkHeader(header, "Visual Sample Send date")
						|| checkHeader(header, "Visual Sample ETA date")
						|| checkHeader(header, "Copyright Sample Send date")
						|| checkHeader(header, "Copyright Sample ETA date")
						|| checkHeader(header, "Additional Bulk Lot Approve")
						|| checkHeader(header, "TOP sample ETA Date"))

				{
					a.doubleClick(ndc); // .keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).build().perform();
					Thread.sleep(1000);
					a.sendKeys(ndc, object.toString());
				} else {
					a.doubleClick(ndc); // .keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).build().perform();
					Thread.sleep(1000);
					if (object != null) {
						a.sendKeys(ndc, object.toString());
					}

				}
				
				a.release();
				a.perform();
				sendKeysList.add(object.toString());
				Thread.sleep(1000);

			}
			driver.findElement(By.xpath("//*[@id='save-changes-psr']")).click();
			Thread.sleep(6000);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	//
	@SuppressWarnings("unchecked")
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
				//System.out.println("gridvalue is " + gridvalue);
				grid_data.put(gridheader, gridvalue);
				// System.out.println(grid_data);

			}

		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public static void runTest() throws IOException, InterruptedException, AWTException {

		FileInputStream fis = new FileInputStream("./data/3records.xlsx"); // Values.xlsx
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("Sheet5");
		DataFormatter format = new DataFormatter();
		for (Row myrow : sheet) {
			int i = 0;
			Thread.sleep(1000);

			WebElement element = driver.findElement(By.xpath("//*[@id='dropdownscroll']/div/div[2]/span"));
			element.click();
			List<WebElement> suggestion = driver.findElements(
					By.xpath("//ul[@role = 'menu']//li//descendant::div[@class='ui-select-choices-row']"));

			firstCellValue = format.formatCellValue(myrow.getCell(i));

			for (WebElement suggest : suggestion) {
				gridheader = suggest.getText();
				if (gridheader.trim().equalsIgnoreCase(format.formatCellValue(myrow.getCell(i)))) {
					// System.out.println(suggest.getText() + " equals " +
					// myrow.getCell(i).getStringCellValue());
					Thread.sleep(1000);

					suggest.click();
					break;
				}
			}
			Thread.sleep(1000);

			runcopy(driver, jqxGridId, format.formatCellValue(myrow.getCell(++i)),
					format.formatCellValue(myrow.getCell(++i)), firstCellValue);

			/*
			 * System.out.println("getcall-= "+format.formatCellValue(myrow.
			 * getCell(++i)));
			 * 
			 * WebElement eleText =
			 * wait.until(ExpectedConditions.visibilityOfElementLocated(
			 * By.xpath("//*[@id='row" + i + jqxGridId + "']/div[12]")));
			 * 
			 * String gridvalue = eleText.getText();
			 * System.out.println("gridvalue is = " + gridvalue);
			 * grid_data.put(gridheader,gridvalue);
			 * System.out.println("-> "+grid_data);
			 */

		}
		//System.out.println("finished reading values ");
	}

	public static boolean checkHeader(String header, String value) {
		return header.trim().equalsIgnoreCase(value);
	}
}