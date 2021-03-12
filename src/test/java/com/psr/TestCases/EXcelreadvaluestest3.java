package com.psr.TestCases;

import java.awt.AWTException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class EXcelreadvaluestest3 {
	static String filepath = null;
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
	 System.out.println("FILE NAME := " + filepath);	 
 }
 
// C:\Users\Shalini\Downloads
  FileInputStream fs = new FileInputStream(filepath);
  //Creating a workbook
  @SuppressWarnings("resource")
  XSSFWorkbook workbook3 = new XSSFWorkbook(fs);
  XSSFSheet sheet3 = workbook3.getSheet("hiddendata");
  
    FileInputStream fis1 = new FileInputStream("./data/Values.xlsx");
	XSSFWorkbook workbook1 = new XSSFWorkbook(fis1);
	XSSFSheet sheet1 = workbook1.getSheetAt(0);
	DataFormatter format1 = new DataFormatter();
  

	    int totalRows = sheet1.getPhysicalNumberOfRows();
	
	  for (int j=0;j<totalRows;j++) 
	  {

	 
	  String firstCellValue = format1.formatCellValue(sheet1.getRow(j).getCell(1));

	     // System.out.println(" first Cell Value"+firstCellValue);
	
	      if(firstCellValue!=null && !firstCellValue.equalsIgnoreCase("")){
	    	  
	    String	  header = format1.formatCellValue(sheet3.getRow(0).getCell(Integer.parseInt(firstCellValue)));
	    XSSFCell	  value = sheet3.getRow(1).getCell(Integer.parseInt(firstCellValue));
	    	 System.out.println("Export Excel value: " + header + " = " + value);
	    	
	 	//excel_data.put( header,value);
		
	    	 
	      }
	    
	   }
	}
}


