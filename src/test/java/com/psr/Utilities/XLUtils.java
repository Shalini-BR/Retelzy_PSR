package com.psr.Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class XLUtils {
	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook wb;
	public static XSSFSheet ws;
	public static XSSFRow row;
	public static XSSFCell cell;

	public static int getRowCount(String xlfile, String xlsheet) throws IOException {
		fi = new FileInputStream(xlfile);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlsheet);
		int rowcount = ws.getLastRowNum();
		wb.close();
		fi.close();
		return rowcount;
	}

	public static int getCellCount(String xlfile, String xlsheet, int rownum) throws IOException {
		fi = new FileInputStream(xlfile);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlsheet);
		row = ws.getRow(rownum);
		int cellcount = row.getLastCellNum();
		wb.close();
		fi.close();
		return cellcount;
	}

	public static String getCellData(String xlfile, String xlsheet, int rownum, int colnum) throws IOException {
		fi = new FileInputStream(xlfile);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlsheet);
		row = ws.getRow(rownum);
		cell = row.getCell(colnum);
		String data;
		try {
			DataFormatter formatter = new DataFormatter();
			String cellData = formatter.formatCellValue(cell);
			return cellData;
		} catch (Exception e) {
			data = "";
		}
		wb.close();
		fi.close();
		return data;
	}

	public static void setCellData(String xlfile, String xlsheet, int rownum, int colnum, String data)
			throws IOException {
		fi = new FileInputStream(xlfile);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlsheet);
		row = ws.getRow(rownum);
		cell = row.createCell(colnum);
		cell.setCellValue(data);
		fo = new FileOutputStream(xlfile);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
	}

	// Generic get cell value
	public String getXLData(String path, String sheet, int r, int c) {
		String v = "";
		try {
			Workbook w = WorkbookFactory.create(new FileInputStream(path));
			v = w.getSheet(sheet).getRow(r).getCell(c).toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return v;
	}

	public static int getXLRowCount(String path, String sheet) {
		int count = 0;
		try {
			Workbook w = WorkbookFactory.create(new FileInputStream(path));
			count = w.getSheet(sheet).getLastRowNum();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	public static String jsonRead(String objectName, String key) throws FileNotFoundException {
		final ReadConfig readconfig = new ReadConfig();
		JSONParser jsonParser = new JSONParser();
		FileReader reader = new FileReader(readconfig.getJsonPath());
		String value = null;
		try {
			// Read JSON file
			Object obj = jsonParser.parse(reader);

			JSONArray listOfObjects = (JSONArray) obj;
			System.out.println(listOfObjects);

			for (Object object : listOfObjects) {
				value = parseEmployeeObject((JSONObject) object, objectName, key);
				
				if(value!=null && !value.equalsIgnoreCase(""))
				break;
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return value;
	}

	private static String parseEmployeeObject(JSONObject object, String objectName, String keyName) {
		// Get employee object within list
		JSONObject employeeObject = (JSONObject) object.get(objectName);

		// Get employee first name
		if (employeeObject != null) {
			String value = (String) employeeObject.get(keyName);
			System.out.println(value);
			return value;
		}
		return "";
	}
}
