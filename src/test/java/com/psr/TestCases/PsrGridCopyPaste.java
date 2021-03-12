package com.psr.TestCases;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import java.awt.AWTException;
import java.awt.Robot;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.psr.PageObject.BaseClass;
import com.psr.PageObject.Library;
import com.psr.Utilities.XLUtils;

/**
* The PsrGridCopyPaste program will enter data and do multiple copy paste for all editable fields
* or on the specified editable fields
* 
* @author  Shalini
* @version PSR 
*/

public class PsrGridCopyPaste extends BaseClass {

	@Test
	public void CopyPaste() throws InterruptedException, IOException, AWTException {

		WebDriverWait wait = new WebDriverWait(driver, 100);
		SoftAssert sa = new SoftAssert();
		try {
			Library.print("COPY PASTE ON GRID");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='search-fields-from']"))).click();
			

			WebElement scrillInside = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='container']/div/app-staticpsr/div[3]/jqxgrid/div")));
			String jqxGridId = scrillInside.getAttribute("id");

			FileInputStream fis = new FileInputStream("./data/Values.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet =workbook.getSheet("Sheet5");
			DataFormatter format = new DataFormatter();
			for (Row myrow : sheet) {
				int i = 0;
				Thread.sleep(1000);

				WebElement element = driver.findElement(By.xpath("//*[@id='dropdownscroll']/div/div[2]/span"));
				element.click();
				List<WebElement> suggestion = driver.findElements(
						By.xpath("//ul[@role = 'menu']//li//descendant::div[@class='ui-select-choices-row']"));

				String firstCellValue = format.formatCellValue(myrow.getCell(i));

				for (WebElement suggest : suggestion) {
					if (suggest.getText().trim().equalsIgnoreCase(format.formatCellValue(myrow.getCell(i)))) {
						System.out.println(suggest.getText() + " equals " + myrow.getCell(i).getStringCellValue());
						Thread.sleep(1000);
						suggest.click();
						break;
					}
				}
				Thread.sleep(1000);
				System.out.println("1="+format.formatCellValue(myrow.getCell(++i))+"2="+format.formatCellValue(myrow.getCell(++i)));       
				
				/*runCopyPaste(driver, jqxGridId, format.formatCellValue(myrow.getCell(++i)),
						format.formatCellValue(myrow.getCell(++i)), firstCellValue);*/
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	private static void runCopyPaste(WebDriver driver, String jqxGridId, String string, Object object, String header)
			throws InterruptedException, IOException, AWTException {
		Robot robot = new Robot();
		String row = XLUtils.jsonRead("records", "rowCount");
		String copyFrom = XLUtils.jsonRead("records", "copyFrom");
		String copyTo = XLUtils.jsonRead("records", "copyTo");
		String pasteFrom = XLUtils.jsonRead("records", "pasteFrom");
		String pasteTo = XLUtils.jsonRead("records", "pasteTo");
		WebDriverWait wait = new WebDriverWait(driver, 100);
		List<String> sendKeysList = new ArrayList();
		int rowCount = Integer.parseInt(row);
		int copyFromInt = Integer.parseInt(copyFrom);
		int copyToInt = Integer.parseInt(copyTo);
		int pasteFromInt = Integer.parseInt(pasteFrom);
		int pasteToInt = Integer.parseInt(pasteTo);
		try {
			for (int i = 0; i < rowCount; i++) { // int i = 0; i < 3; i++
				Actions a = new Actions(driver);
				WebElement ndc = wait.until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath("//*[@id='row" + i + jqxGridId + "']/div[" + string + "]")));
				a.doubleClick(ndc);
				if        (checkHeader(header, "Tech Pack Received Date")       || checkHeader(header, "packaging ready date")
						|| checkHeader(header, "fabric pp date")                || checkHeader(header, "fabric pp date")
						|| checkHeader(header, "cpo acc date by vendor")        || checkHeader(header, "sample merch eta")
						|| checkHeader(header, "sample floor set eta")          || checkHeader(header, "sample dcom eta")
						|| checkHeader(header, "sample mailer eta")             || checkHeader(header, "Photo/Merchant Sample Send date")
						|| checkHeader(header, "Photo/Merchant Sample ETA date")|| checkHeader(header, "Photo/Merchant Sample Send date")
						|| checkHeader(header, "Marketing Sample Send date")    || checkHeader(header, "Marketing Sample ETA date")
						|| checkHeader(header, "Visual Sample Send date")       || checkHeader(header, "Visual Sample ETA date")
						|| checkHeader(header, "Copyright Sample Send date")    || checkHeader(header, "Copyright Sample ETA date")
						|| checkHeader(header, "Additional Bulk Lot Approve")   || checkHeader(header, "TOP sample ETA Date"))

				{
					a.click(ndc).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL);
					a.sendKeys(ndc, object.toString());
				} else {
					a.click(ndc).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL);
					if(object != null)
					{
						a.sendKeys(ndc, object.toString() + i + "1");
					}
					
				}
				a.release();
				a.perform();
				sendKeysList.add(object.toString());
				Thread.sleep(1000);
			}
			
			//driver.findElement(By.xpath("//*[@id='save-changes-psr']")).click();

			//Thread.sleep(10000);

			Actions b = new Actions(driver);
			WebElement from = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='row"+copyFromInt + jqxGridId + "']/div[" + string + "]")));

			WebElement to = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='row"+copyToInt + jqxGridId + "']/div[" + string + "]")));
			Action drag = b.clickAndHold(from).moveToElement(to).release(to).build();
			drag.perform();
			//b.keyDown(from,Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).perform(); 
		    b.keyDown(Keys.CONTROL).sendKeys("c").keyUp(to,Keys.CONTROL).perform();

			Actions c = new Actions(driver);
			WebElement from1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='row"+pasteFromInt + jqxGridId + "']/div[" + string + "]")));
			WebElement to1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='row"+pasteToInt + jqxGridId + "']/div[" + string + "]")));
			Action drag1 = c.clickAndHold(from1).moveToElement(to1).release(to1).build();
			drag1.perform();
			robot.delay(2000);
			//c.keyDown(from1,Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).perform(); 
		    c.keyDown(Keys.CONTROL).sendKeys("v").keyUp(from1,Keys.CONTROL).perform();
			/*robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
*/
			robot.delay(1000);
			driver.findElement(By.xpath("//*[@id='save-changes-psr']")).click();
			Thread.sleep(10000);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static boolean checkHeader(String header, String value) {
		return header.trim().equalsIgnoreCase(value);
	}
}
