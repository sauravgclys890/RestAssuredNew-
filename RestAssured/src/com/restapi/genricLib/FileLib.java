package com.restapi.genricLib;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FileLib {

	public String getExcelTestData(String sheetName, int row, int col) throws Exception {

		// ".\\TestData\\RestAPITestData.xlsx"
		
		String fileName=System.getProperty("user.dir")+File.separator+"TestData"+File.separator+"RestAPITestData.xlsx";
		
		System.out.println(fileName);

		FileInputStream inputStream = new FileInputStream(new File(fileName));
		
		System.out.println(inputStream);

		Workbook wb = null;
		
		String fileExtensionName = fileName.substring(fileName.indexOf("."));
		
		if(fileExtensionName.equals(".xlsx")){


		    wb = new XSSFWorkbook(inputStream);

		    }


		    else if(fileExtensionName.equals(".xls")){


		        wb = new HSSFWorkbook(inputStream);

		    }

		Sheet sh = wb.getSheet(sheetName);
		
		System.out.println(sh);

		Row rw = sh.getRow(row);
		
		System.out.println(rw);
		
		System.out.println(col);
		
		String data = rw.getCell(col).getStringCellValue();
		
		System.out.println(data);

		wb.close();
		return data;

	}

	public void setExcelTestData(String sheetName, int row, int col, String data) throws Exception {

		FileInputStream filename = new FileInputStream(new File(".\\TestData\\RestAPITestData.xlsx"));

		Workbook wb = WorkbookFactory.create(filename);

		Sheet sh = wb.getSheet(sheetName);

		Row rw = sh.getRow(row);

		Cell cel = rw.createCell(col);

		cel.setCellValue(data);

		FileOutputStream fout = new FileOutputStream(new File(".\\TestData\\RestAPITestData.xlsx"));

		wb.write(fout);

		wb.close();

	}

	public String getPropertiesData(String key) throws Exception {
		FileInputStream filename = new FileInputStream(new File(".\\TestData\\RestAPITestData.properties"));

		Properties pobj = new Properties();

		pobj.load(filename);

		String data = pobj.getProperty(key);

		return data;

	}

}
