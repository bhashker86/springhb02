package com.common.view;

import java.awt.Label;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.model.CategoryVO;
import com.model.CustomerVO;

import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class CategoryReportView extends AbstractExcelView{
	
	
	
	@Override
	protected void buildExcelDocument(Map model, HSSFWorkbook workbook,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HSSFSheet excelSheet = workbook.createSheet("Category List");
		setExcelHeader(excelSheet);
		
		List<CategoryVO> category_list = (List<CategoryVO>) model.get("category_list");
		setExcelRows(excelSheet,category_list);
		
	}

	public void setExcelHeader(HSSFSheet excelSheet) {
		HSSFRow excelHeader = excelSheet.createRow(0);
		
		excelHeader.createCell(0).setCellValue("S.No");
		excelHeader.createCell(1).setCellValue("Category");
		excelHeader.createCell(2).setCellValue("Preference");
		
	}
	
	public void setExcelRows(HSSFSheet excelSheet, List<CategoryVO> category_list){
		int record = 1;
		int sno=1;
		for (CategoryVO category : category_list) {
			HSSFRow excelRow = excelSheet.createRow(record++);
			
			excelRow.createCell(0).setCellValue(sno);
			excelRow.createCell(1).setCellValue(category.getCategory() );
			excelRow.createCell(2).setCellValue(category.getPreference()  );
			sno++;
			
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
