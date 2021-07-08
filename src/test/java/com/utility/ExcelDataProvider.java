/* *********************************************************
 ##### Utility class to get Data from Excel ########
 Author         : Vishal Mathur
 Created Date   :7-July-2021
 */
package com.utility;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;

public class ExcelDataProvider {
    XSSFWorkbook wb;
    public ExcelDataProvider(){
        File src = new File("./TestData/TestDataSheet.xlsx");
        try {
            FileInputStream fis = new FileInputStream(src);
            wb = new XSSFWorkbook(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String getData(String sheetName, int row, int col){
        return wb.getSheet(sheetName).getRow(row).getCell(col).getStringCellValue();
    }
    public int getRowCount (String sheetName){
        return wb.getSheet(sheetName).getLastRowNum();
    }
}
