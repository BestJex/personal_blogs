package com.chengcheng.cases.plug.excelpoi;

import io.swagger.annotations.Api;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.IOException;

@Api("POI解析Excel文件内容")
public class PoiReadExcel {

	public static void main(String[] args) {

		// 需要解析的Excel文件
		File file = new File("C:/Users/lenovo/Desktop/poi_test.xls");
		try {
			// 创建Excel, 读取文件内容
			HSSFWorkbook workbook = new HSSFWorkbook(FileUtils.openInputStream(file));
			// 第一种方式: 获取第一个工作表 Sheet0为第一个工作表的名称
//			HSSFSheet sheet = workbook.getSheet("Sheet0");
			// 第二种方式: 读取默认第一个工作表 0 为工作表的索引
			HSSFSheet sheet = workbook.getSheetAt(0);
			int firstRowNum = 0;
			// 获取sheet中最后一行行号
			int lastRowNum = sheet.getLastRowNum();
			for (int i= firstRowNum; i <= lastRowNum; i ++) {
				HSSFRow row = sheet.getRow(i);
				// 获取当前行最后单元格列号
				int lastCellNum = row.getLastCellNum();
				// 该行的列操作
				for (int j = 0; j < lastCellNum; j ++) {
					HSSFCell cell = row.getCell(j);
					// 不同的类型不同的方法
					String value = cell.getStringCellValue();
					System.out.print(value + " ");
				}
				System.out.println();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}


	}
}
