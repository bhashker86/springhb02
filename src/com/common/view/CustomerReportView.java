package com.common.view;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.model.CustomerVO;

import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class CustomerReportView extends AbstractExcelView{
	
	
	
	@Override
	protected void buildExcelDocument(Map model, HSSFWorkbook workbook,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HSSFSheet excelSheet = workbook.createSheet("Customer List");
		setExcelHeader(excelSheet);
		
		List<CustomerVO> customer_list = (List<CustomerVO>) model.get("customer_list");
		setExcelRows(excelSheet,customer_list);
		
	}

	public void setExcelHeader(HSSFSheet excelSheet) {
		HSSFRow excelHeader = excelSheet.createRow(0);
		
		excelHeader.createCell(0).setCellValue("S.No");
		excelHeader.createCell(1).setCellValue("First Name");
		excelHeader.createCell(2).setCellValue("Last Name");
		excelHeader.createCell(3).setCellValue("Age");
		excelHeader.createCell(4).setCellValue("Gender");
		
		excelHeader.createCell(5).setCellValue("Email");
		excelHeader.createCell(6).setCellValue("City");
		excelHeader.createCell(7).setCellValue("Country");
		excelHeader.createCell(8).setCellValue("Mobile");
		excelHeader.createCell(9).setCellValue("Postel Code");
		
		excelHeader.createCell(10).setCellValue("Region");
		excelHeader.createCell(11).setCellValue("Created At");
		excelHeader.createCell(12).setCellValue("Description");
		
	}
	
	public void setExcelRows(HSSFSheet excelSheet, List<CustomerVO> customer_list){
		int record = 1;
		int sno=1;
		for (CustomerVO customer : customer_list) {
			HSSFRow excelRow = excelSheet.createRow(record++);
			
			excelRow.createCell(0).setCellValue(sno);
			excelRow.createCell(1).setCellValue(customer.getFirst_name() );
			excelRow.createCell(2).setCellValue(customer.getLast_name());
			excelRow.createCell(3).setCellValue(customer.getAge());
			excelRow.createCell(4).setCellValue(customer.getGender());
			
			excelRow.createCell(5).setCellValue(customer.getEmail());
			excelRow.createCell(6).setCellValue(customer.getCity());
			excelRow.createCell(7).setCellValue(customer.getCountry());
			excelRow.createCell(8).setCellValue(customer.getMobile());
			excelRow.createCell(9).setCellValue(customer.getPostal_code());
			
			excelRow.createCell(10).setCellValue(customer.getRegion());
			excelRow.createCell(11).setCellValue(customer.getCreated_at());
			
			excelRow.createCell(12).setCellValue(customer.getDescription());
			
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
