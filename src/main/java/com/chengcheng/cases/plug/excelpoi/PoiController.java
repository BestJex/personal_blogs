package com.chengcheng.cases.plug.excelpoi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Api("报表服务测试")
@RestController
@RequestMapping(value = "testPoi")
public class PoiController {

	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(PoiController.class);

	@ApiOperation("报表请求模拟")
	@RequestMapping(value = "getReport", method = RequestMethod.GET)
	public ArrayList<ArrayList<String>> getReport(String start, String end) {
		ArrayList<ArrayList<String>> arr = new ArrayList<ArrayList<String>>();
		ArrayList<String> arr2 = new ArrayList<String>();

		arr2.add("济南");
		arr2.add("545291");
		arr2.add("545291");
		arr2.add("545291");
		arr2.add("545291");
		arr2.add("545291");
		arr2.add("545291");
		arr2.add("545291");
		arr2.add("545291");
		arr2.add("545291");
		arr2.add("545291");
		arr2.add("545291");
		arr.add(arr2);
		arr.add(arr2);
		arr.add(arr2);
		arr.add(arr2);
		arr.add(arr2);
		arr.add(arr2);
		arr.add(arr2);
		arr.add(arr2);
		arr.add(arr2);
		arr.add(arr2);
		arr.add(arr2);
		arr.add(arr2);
		arr.add(arr2);
		arr.add(arr2);
		arr.add(arr2);
		arr.add(arr2);

		return arr;
	}

	@ApiOperation("导出报表样式")
	@RequestMapping(value = "exportTest", method = RequestMethod.GET)
	public ResponseEntity<byte[]> exportReport() {
		ArrayList<ArrayList<String>> arr = this.getReport("0", "1");
		HSSFWorkbook workbook = new HSSFWorkbook();  // 工作簿
		HSSFCellStyle cellStyle = workbook.createCellStyle();  // 样式
		HSSFFont font = workbook.createFont();  // 字体
		HSSFSheet sheet = workbook.createSheet();  // 页
		HSSFRow row = sheet.createRow(0);  // 行
		HSSFCell one = row.createCell(0);  // 单元格
		one.setCellValue("山东省未登记流动人口分析情况表");

		// 设置列宽
		for (int i = 1; i < 12; i++) {
			sheet.setColumnWidth(i, 100 * 40);
		}

		// 设置合并单元格
		CellRangeAddress region1 = new CellRangeAddress(0, 0, (short) 0, (short) 11); // 参数1：起始行 参数2：终止行 参数3：起始列 参数4：终止列
		sheet.addMergedRegion(region1);
		CellRangeAddress region2 = new CellRangeAddress(1, 1, (short) 1, (short) 4);
		sheet.addMergedRegion(region2);
		CellRangeAddress region3 = new CellRangeAddress(1, 1, (short) 5, (short) 8);
		sheet.addMergedRegion(region3);
		CellRangeAddress region4 = new CellRangeAddress(1, 1, (short) 9, (short) 11);
		sheet.addMergedRegion(region4);
		CellRangeAddress region5 = new CellRangeAddress(1, 2, (short) 0, (short) 0);
		sheet.addMergedRegion(region5);

		// 设置居中
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		one.setCellStyle(cellStyle);

		// 字体格式
		font.setFontName("仿宋_GB2312");
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
		font.setFontHeightInPoints((short) 12);  //字体大小
		cellStyle.setFont(font);
		one.setCellStyle(cellStyle);

		// 第二行
		HSSFRow row2 = sheet.createRow(1);
		HSSFCell two = row2.createCell(1);
		HSSFCell two2 = row2.createCell(5);
		HSSFCell two3 = row2.createCell(9);
		two.setCellValue("铁路");
		two2.setCellValue("民航");
		two3.setCellValue("从业人员");

		// 设置居中
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		two.setCellStyle(cellStyle);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		two2.setCellStyle(cellStyle);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		two3.setCellStyle(cellStyle);

		// 第三行
		HSSFRow row3 = sheet.createRow(2);
		HSSFCell three = row2.createCell(0);
		three.setCellValue("地方");
		cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		three.setCellStyle(cellStyle);

		// 副标题
		String[] title = {
				"到达本市停留大于三天人员",
				"到达本市非常口人员",
				"到达本市未在流口系统登记人员",
				"到达本市未住旅店人员",
				"到达本市停留大于三天人员",
				"到达本市非常口人员",
				"到达本市未在流口系统登记人员",
				"到达本市未住旅店人员",
				"本市登记从业人员总数",
				"到达本市非常口人员",
				"到达本市未在流口系统登记人员"
		};
		for (int i = 0; i < 11; i++) {
			HSSFCell name = row3.createCell(i + 1);
			name.setCellValue(title[i]);
			cellStyle = workbook.createCellStyle();  // 设置自动换行
			cellStyle.setWrapText(true);
			name.setCellStyle(cellStyle);
		}

		// 数据
		HSSFCell cell = null;
		for (int i = 0; i < arr.size(); i++) {
			HSSFRow nextrow = sheet.createRow(i + 3);
			for (int j = 0; j < arr.get(i).size(); j++) {
				HSSFCell cell2 = nextrow.createCell(j);
				cell2.setCellValue(arr.get(i).get(j));
			}
		}

		// 制表单位和日期
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String dateNow = sf.format(new Date());

		int rowNum = sheet.getLastRowNum();  // 获取已有行数
		CellRangeAddress hb = new CellRangeAddress(rowNum + 1, rowNum + 1, (short) 0, (short) 5);
		sheet.addMergedRegion(hb);
		CellRangeAddress hb2 = new CellRangeAddress(rowNum + 1, rowNum + 1, (short) 6, (short) 11);
		sheet.addMergedRegion(hb2);
		HSSFRow lastNowRow = sheet.createRow(rowNum + 1);
		HSSFCell lastNowOne = lastNowRow.createCell(0);
		lastNowOne.setCellValue("制表单位: 信息通信处");
		HSSFCell lastNowtwo = lastNowRow.createCell(6);
		lastNowtwo.setCellValue("制表日期: " + dateNow);

		// 备注
		int lastRowNum = sheet.getLastRowNum();
		String[] remarks = {
				"备注:",
				"1. 到达本市停留大于三天人员：去除已离开本市人员，本月到达本市人员大于三天;",
				"2. 到达本市非常口人员：本市去除常口人员后剩余人员;",
				"3. 到达本市未在流口系统登记人员：本市去除已在流口系统中登记后剩余人员;",
				"4. 到达本市未住旅店人员：去除在本市登记旅店后剩余人员。",
		};
		for (int i = 0; i < 5; i++) {
			CellRangeAddress lastBz = new CellRangeAddress(lastRowNum + 1 + i, lastRowNum + 1 + i, (short) 0, (short) 11);
			sheet.addMergedRegion(lastBz);
			HSSFRow lastRow = sheet.createRow(lastRowNum + 1 + i);
			HSSFCell lastNow = lastRow.createCell(0);
			lastNow.setCellValue(remarks[i]);
		}
		// 输出文件
//		File file = new File("C:/Users/lenovo/Desktop/report.xls");
//		try {
//			file.createNewFile();
//			FileOutputStream stream = FileUtils.openOutputStream(file);
//			workbook.write(stream);  // 写入
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			workbook.write(out);
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			httpHeaders.setContentDispositionFormData("attachement", "reportForm.xls");
			return new ResponseEntity<>(out.toByteArray(), httpHeaders, HttpStatus.CREATED);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
