package com.jx.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.jx.common.config.PageData;

/**
 * 导入到EXCEL 类名称：ObjectExcelView.java 类描述：
 * @author FH 作者单位： 联系方式：
 * @version 1.0
 */
public class ObjectExcelView extends AbstractXlsView {

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		Date date = new Date();
		String filename = Tools.date2Str(date, "yyyyMMddHHmmss");
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename=" + filename + ".xls");
		Sheet sheet = workbook.createSheet("sheet1");

		List<String> titles = (List<String>) model.get("titles");
		int len = titles.size();
		CellStyle headerStyle = workbook.createCellStyle(); // 标题样式
		headerStyle.setAlignment(CellStyle.ALIGN_CENTER);
		headerStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		Font headerFont = workbook.createFont(); // 标题字体
		headerFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		headerFont.setFontHeightInPoints((short) 11);
		headerStyle.setFont(headerFont);
		short width = 20, height = 25 * 20;
		sheet.setDefaultColumnWidth(width);
		// create header row
        Row header = sheet.createRow(0);
		for (int i = 0; i < len; i++) { // 设置标题
			header.createCell(i).setCellValue(titles.get(i));
		}
		sheet.getRow(0).setHeight(height);

		CellStyle contentStyle = workbook.createCellStyle(); // 内容样式
		contentStyle.setAlignment(CellStyle.ALIGN_CENTER);
		List<PageData> varList = (List<PageData>) model.get("varList");
		if(varList != null && varList.size() !=0){
			for (int i = 0; i < varList.size(); i++) {
				PageData vpd = varList.get(i);
				Row row = sheet.createRow(i+1);
				for (int j = 0; j < len; j++) {
					Cell cell = row.createCell(j);
					cell.setCellValue(vpd.getString("var" + j) != null ? vpd.getString("var" + j) : "");
					cell.setCellStyle(contentStyle);
					cell.setCellType(1);
				}
				
			}
		}

	}

	
	/**
	 * @param filepath //文件路径
	 * @param filename //文件名
	 * @param startrow //开始行号
	 * @param startcol //开始列号
	 * @param sheetnum //sheet
	 * @return list
	 */
	@SuppressWarnings("resource")
	public static List<Object> readExcel(String filepath, String filename, 
			int startrow, int startcol, int sheetnum) {
		List<Object> varList = new ArrayList<Object>();

		try {
			File target = new File(filepath, filename);
			FileInputStream fi = new FileInputStream(target);
			
			Workbook wb = null;  
			try {  
				wb = new HSSFWorkbook(fi);  
			} catch (Exception ex) {  
				wb = new XSSFWorkbook(target);  
			} 
			
			Sheet sheet = wb.getSheetAt(sheetnum); // sheet 从0开始
			int rowNum = sheet.getLastRowNum() + 1; // 取得最后一行的行号

			for (int i = startrow; i < rowNum; i++) { // 行循环开始

				PageData varpd = new PageData();
				Row row = sheet.getRow(i); // 行
				int cellNum = row.getLastCellNum(); // 每行的最后一个单元格位置
				for (int j = startcol; j < cellNum; j++) { // 列循环开始

					Cell cell = row.getCell(Short.parseShort(j + ""));
					String cellValue = null;
					if (null != cell) {
						switch (cell.getCellType()) { // 判断excel单元格内容的格式，并对其进行转换，以便插入数据库
							case Cell.CELL_TYPE_NUMERIC:
								cellValue = String.valueOf((long) cell.getNumericCellValue());
								break;
							case Cell.CELL_TYPE_STRING:
								cellValue = cell.getStringCellValue();
								break;
							case Cell.CELL_TYPE_FORMULA:
								cellValue = cell.getNumericCellValue() + "";
								// cellValue = String.valueOf(cell.getDateCellValue());
								break;
							case Cell.CELL_TYPE_BLANK:
								cellValue = "";
								break;
							case Cell.CELL_TYPE_BOOLEAN:
								cellValue = String.valueOf(cell.getBooleanCellValue());
								break;
							case Cell.CELL_TYPE_ERROR:
								cellValue = String.valueOf(cell.getErrorCellValue());
								break;
						}
					} else {
						cellValue = "";
					}
					if("".equals(cellValue)||cellValue==null){
						varpd=null;
						break;
					}
					varpd.put("var" + j, cellValue);
				}
				if(varpd==null){
					break;
				}
				varList.add(varpd);
			}

		} catch (Exception e) {
			System.out.println(e);
		}

		return varList;
	}
}





