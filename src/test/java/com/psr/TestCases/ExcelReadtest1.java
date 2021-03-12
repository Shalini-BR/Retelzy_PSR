package com.psr.TestCases;
import java.awt.AWTException;
import java.awt.Robot;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashMap;
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

public class ExcelReadtest1 {
	
	static WebDriver driver = new ChromeDriver();	
    static String filepath=null;
    static String  ViewName=null;
    static String header =null;
    static String value = null;
    static String gridvalue = null;
    static String gridheader= null;
    static String vposearchvalue= "1234";
    static WebDriverWait wait = new WebDriverWait(driver, 100);
    static HashMap<String, String> excel_data = new HashMap<String, String>();
    static HashMap<String, String> grid_data = new HashMap<String, String>();
   // String ViewName = null;
	@SuppressWarnings({ "unchecked", "rawtypes", "unused", "static-access" })
	public static void main(String[] Args) throws IOException, GeneralSecurityException, InterruptedException, AWTException
	{
		
		
			driver.get("http://psrwin.infogloballink.com:89/psrtest/psr");
		driver.manage().window().maximize();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='username']")).sendKeys("Randhir_ptl_admin");
		driver.findElement(By.xpath("//*[@id='password']")).sendKeys("Igt@1234");
		driver.findElement(By.xpath("//*[@id='login']")).click();
		Thread.sleep(30000);
		
		
		//WebDriverWait wait = new WebDriverWait(driver,100); 
	WebElement vpo =	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='vpo']")));
	vpo.sendKeys(vposearchvalue);
	driver.findElement(By.xpath("//button[@id= 'search-fields-from']")).click();
		
	
	ViewName =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='ui-select-match ng-star-inserted']//span)[2]"))).getText();
	System.out.println("VIEW NAME := " + ViewName);
	
	
	// copy paste
	
	
	WebElement scrillInside = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='container']/div/app-staticpsr/div[3]/jqxgrid/div")));
	String jqxGridId = scrillInside.getAttribute("id");
	
	FileInputStream fis = new FileInputStream("./data/Values.xlsx");
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

		String firstCellValue = format.formatCellValue(myrow.getCell(i));

		for (WebElement suggest : suggestion) {
			gridheader = suggest.getText();
			if (gridheader.trim().equalsIgnoreCase(format.formatCellValue(myrow.getCell(i)))) {
				//System.out.println(suggest.getText() + " equals " + myrow.getCell(i).getStringCellValue());
				Thread.sleep(1000);
				suggest.click();
				
				
				System.out.println("Grid Header = " + gridheader);
				break;
			}
		}
		Thread.sleep(1000);
                runCopyPaste(driver, jqxGridId, format.formatCellValue(myrow.getCell(++i)),
				format.formatCellValue(myrow.getCell(++i)), firstCellValue);
                
	}          
	}
	@SuppressWarnings("unchecked")
	private static void runCopyPaste(WebDriver driver, String jqxGridId, String string, Object object, String header)
			throws InterruptedException, IOException, AWTException {
		Robot robot = new Robot();
		
		List<String> sendKeysList = new ArrayList();

		try {
			
			for (int i = 0; i < 1; i++) { // int i = 0; i < 3; i++
				Actions a = new Actions(driver);
				WebElement ndc = wait.until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath("//*[@id='row" + i + jqxGridId + "']/div[" + string + "]")));
				
				
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
					a.doubleClick(ndc).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).build().perform();
					Thread.sleep(1000);
					a.sendKeys(ndc, object.toString());
				} else {
					a.doubleClick(ndc).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).build().perform();
					Thread.sleep(1000);
					if(object != null)
					{
						a.sendKeys(ndc, object.toString() + i + "1");
					}
					
				}
				a.release();
				a.perform();
				sendKeysList.add(object.toString());
				Thread.sleep(1000);
			
			
			driver.findElement(By.xpath("//*[@id='save-changes-psr']")).click();

			Thread.sleep(10000);

			WebElement Gridvalue = wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//*[@id='row" + i + jqxGridId + "']/div[" + string + "]")));
			 gridvalue = Gridvalue.getText();
			System.out.println("Gridvalue = "+ gridvalue);
			
			
		 	grid_data.put(gridvalue,gridheader);
		 	
			
			}
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}	}	
		 	  
	public static boolean checkHeader(String header, String value) {
		return header.trim().equalsIgnoreCase(value);
	}

	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	
	
	
	
	//static WebDriver driver = new ChromeDriver();	
        static String filepath=null;
       // String ViewName = null;
		@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
		public static void main(String[] Args) throws IOException, GeneralSecurityException, InterruptedException, AWTException
		{
			
			File dir = new File("C:\\Users\\Shalini\\Downloads"); 
	        File [] files  = dir.listFiles();
	        Arrays.sort(files, new Comparator(){
	            public int compare(Object o1, Object o2) {
	                return compare( (File)o1, (File)o2);
	            }	
	            private int compare( File f1, File f2){
	                long result = f2.lastModified() - f1.lastModified();
	                if( result > 0 ){
	                    return 1;
	                } else if( result < 0 ){
	                    return -1;
	                } else {
	                    return 0;
	                }
	            }
	        });
	        
	     List<File> filename = Arrays.asList(files[0]);
	   
	     Iterator<File> itr = filename.iterator();
	     while(itr.hasNext())
	     {
	    	 filepath = itr.next().toString();
	    	 System.out.println(filepath);	 
	     }
	     
	     readExcel(filepath, "hiddensheet", 13, 13);
	     
	      
	      FileInputStream fs = new FileInputStream(filepath);
	      //Creating a workbook
	      @SuppressWarnings("resource")
		XSSFWorkbook workbook = new XSSFWorkbook(fs);
	      XSSFSheet sheet = workbook.getSheet("hiddendata");
	      Row row12 = sheet.getRow(0);
	      Cell cell = row12.getCell(0);
	      System.out.println(sheet.getRow(0).getCell(13));
	      Row row1 = sheet.getRow(1);
	      Cell cell1 = row1.getCell(1);
	     // System.out.println(sheet.getRow(0).getCell(1));
	      Row row2 = sheet.getRow(1);
	      Cell cell2 = row2.getCell(1);
	      System.out.println(sheet.getRow(1).getCell(13));
	      Row row3 = sheet.getRow(1);
	      
	      System.out.println("No of records is equal to "+sheet.getLastRowNum());
	    }



public static int  readExcel(String filepath, String sheetname, int Row, int Cell ) throws IOException
{
	 FileInputStream fs = new FileInputStream(filepath);
     //Creating a workbook
     @SuppressWarnings("resource")
	XSSFWorkbook workbook = new XSSFWorkbook(fs);
     XSSFSheet sheet = workbook.getSheet(sheetname);
     Cell columnHeader = sheet.getRow(0).getCell(Row);
     Cell columnValue = sheet.getRow(1).getCell(Cell);
     System.out.println(columnHeader);
     System.out.println(columnValue);
	return Cell;
	
}*/





