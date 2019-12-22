package com.common.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;


import com.model.ProductVO;

public class ProductReportView extends AbstractExcelView{
	
	@Override
	protected void buildExcelDocument(Map model, HSSFWorkbook workbook,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HSSFSheet excelSheet = workbook.createSheet("Product List");
		setExcelHeader(excelSheet);
		
		List<ProductVO> product_list = (List<ProductVO>) model.get("product_list");
		setExcelRows(excelSheet,product_list);
		
	}

	public void setExcelHeader(HSSFSheet excelSheet) {
		HSSFRow excelHeader = excelSheet.createRow(0);
		
		excelHeader.createCell(0).setCellValue("S.No");
		excelHeader.createCell(1).setCellValue("Product Name");
		excelHeader.createCell(2).setCellValue("Product Title");
		excelHeader.createCell(3).setCellValue("Price");
		excelHeader.createCell(4).setCellValue("Color");
		
		excelHeader.createCell(5).setCellValue("Model Number");
		excelHeader.createCell(6).setCellValue("Model Name");
		excelHeader.createCell(7).setCellValue("Category Name");
		excelHeader.createCell(8).setCellValue("Sub Category Name");
		
		excelHeader.createCell(9).setCellValue("Product Url");
		
		
		
	}
	
	public void setExcelRows(HSSFSheet excelSheet, List<ProductVO> product_list){
		int record = 1;
		int sno=1;
		for (ProductVO product :product_list) {
			HSSFRow excelRow = excelSheet.createRow(record++);
			
			excelRow.createCell(0).setCellValue(sno++);
			excelRow.createCell(1).setCellValue(product.getName() );
			excelRow.createCell(2).setCellValue(product.getTitle());
			excelRow.createCell(3).setCellValue(product.getPrice() );
			excelRow.createCell(4).setCellValue(product.getColor());
			
			excelRow.createCell(5).setCellValue(product.getModel_number());
			excelRow.createCell(6).setCellValue(product.getModel_name());
			
			excelRow.createCell(7).setCellValue(product.getCategory_id());
			excelRow.createCell(8).setCellValue(product.getSub_category_id());
			excelRow.createCell(9).setCellValue(product.getProduct_url());
			
			
		}

 }
}
