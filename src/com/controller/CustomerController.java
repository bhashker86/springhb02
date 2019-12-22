package com.controller;
import java.io.File;
import java.io.FileInputStream;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.hibernate.Query;

import com.dao.CustomerDao;
import com.model.*;
import com.services.*;
import com.util.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView; 


@Controller
@RequestMapping("customer")
public class CustomerController {

	
	@Autowired
	private CustomerServ customerserv;
	@Autowired
	private CountryServ countryserv;
	
	//@Autowired
	//private MyMailImpl mail_impl;
	
	@Autowired
	private ServletContext context; 


@RequestMapping(value="add-customer",method=RequestMethod.GET)	
public ModelAndView addCustomer(ModelMap model){
		
	 ModelAndView mv=new ModelAndView("customer/add_customer_view");
	 Map map=new HashMap();
	 map.put("page_title","- Add Customer");
	 map.put("page_heading", "Add Customer");
	 mv.addAllObjects(map);
	return mv;
		
}

@RequestMapping(value="add",method=RequestMethod.GET)
public ModelAndView addNewCustomer(){

	ModelAndView mav=new ModelAndView("customer/add_new_customer","customer",new CustomerVO());
    Map map=new HashMap();
   
    map.put("page_title", "Add New Customer");
    map.put("page_heading", "Add New Customer");
    map.put("customer", new CustomerVO());
    map.put("country_list", getCountryVOList(countryserv.getCountryList()));
       
    mav.addAllObjects(map);
    return mav;

    
}


@RequestMapping(value="add-new-customer",method=RequestMethod.POST)
public ModelAndView addNewCustSbmt(@Valid @ModelAttribute("customer") CustomerVO customer,BindingResult result){
	Map map=new HashMap();
	
	
  if(result.hasErrors())
  {
	ModelAndView mav=new ModelAndView("customer/add_new_customer","customer",customer) ; 
	map.put("page_title", "Add New Customer");
	map.put("page_heading", "Add New Customer");
	map.put("country_list", getCountryVOList(countryserv.getCountryList()));
	mav.addAllObjects(map);
	return mav;
	  
  }
  else
  {
	  if(customerserv.addCustomer(customer)){
			map.put("status", "Sucessfully Added");
			
			//mail_impl.sendEMail("bhashkarkumar86@gmail.com", "bhashkerkumar@outlook.com", "asdf", "asdfas");
			
			
		}else{
			map.put("status", "Not Sucessfully Added");
		}
		
	  ModelAndView mav= new ModelAndView("redirect:customer-list");
	  mav.addAllObjects(map);
	   return mav;
  }

}

@RequestMapping(value="edit-customer/{id}",method=RequestMethod.GET)
public ModelAndView editCustomerView(@PathVariable("id") int id){
	CustomerVO customer=null;
	customer=customerserv.getCustomer(id);
	
	Map map=new HashMap();
	map.put("page_title", "Changes "+customer.getFirst_name()+" Data");
	map.put("page_heading", "Welcome Back "+customer.getFirst_name());
	map.put("customer", customer);
	map.put("country_list", getCountryVOList(countryserv.getCountryList()));
	
	ModelAndView mav=new ModelAndView("customer/edit_new_customer_view","customer" ,customer);
	mav.addAllObjects(map);
	return mav;
}


@RequestMapping(value="edit-customer-sbmt", method=RequestMethod.POST)
public ModelAndView editCustomerSbmt(@Valid @ModelAttribute("customer") CustomerVO customer,BindingResult result){
	Map map=new HashMap();
	
	if(result.hasErrors()){
	    ModelAndView mav=new ModelAndView("customer/edit_new_customer_view","customer" ,customer);
	  
	    map.put("page_heading", "Welcome Back "+customer.getFirst_name());
		map.put("customer", customer);
		map.put("country_list", getCountryVOList(countryserv.getCountryList()));
		mav.addAllObjects(map);
	  return mav;	
	}
	else{
		customerserv.updateCustomer(customer);
		ModelAndView mav=new ModelAndView("redirect:customer-list");
		 return mav;
		
	}
 
	
}





@RequestMapping(value="add-customer", method=RequestMethod.POST)
public String addCustomerSbmt(@ModelAttribute("customervo") CustomerVO customervo){
	Map map=new HashMap();

	if(customerserv.addCustomer(customervo)){
		map.put("status", "Sucessfully Added");
	}else{
		map.put("status", "Not Sucessfully Added");
	}
	
return "redirect:customer-list"	;

	
}


@RequestMapping(value="customer-list", method=RequestMethod.GET)
public ModelAndView getCustomers(){
	 Map map= new HashMap();
	 ModelAndView mv= new ModelAndView("customer/customer_list_view");
	 
	 if(!customerserv.getCustomers().isEmpty())
	 {
		 map.put("customers_list", getCustVoList(customerserv.getCustomers()) );
	 }
	 
	 mv.addAllObjects(map);
	  return mv;
	 
 }

@RequestMapping(value="delete-customer/{id}", method=RequestMethod.GET)
public ModelAndView deleteCustomer(ModelMap model ,@PathVariable("id")    int id,HttpServletRequest req){
	Map map=new HashMap();
	String ret_stirng=null;
//	String req_url=req.getRequestURI();
//	String req_host=req.getServletContext();
	
	ModelAndView mav=new ModelAndView("redirect:http://localhost:8080/springhb02/customer/customer-list");
	
	
	if(customerserv.deleteCustomer(id)){
		    ret_stirng="sucessfully Deleted";	
	    }
	else{
		    ret_stirng="Not deleted";	
	    }
	
	map.put("msg", ret_stirng);
	mav.addAllObjects(map);
	return mav;
	
}


@RequestMapping(value="change-customer/{id}",method=RequestMethod.GET)
public ModelAndView getCustomer(@PathVariable("id") int id){
	ModelAndView mv=new ModelAndView("customer/edit_customer_view");
	Map map =new HashMap();
	CustomerVO customer= new CustomerVO();
	customer=customerserv.getCustomer(id);
	
	
	map.put("page_title", "Change Customer Data");
	map.put("page_heading", "Changes Customer Data");
	map.put("customer", customer);
	mv.addAllObjects(map);
	return mv;
	
}


@RequestMapping(value="change-customer",method=RequestMethod.POST)
public String editCustomer(@ModelAttribute("customer") CustomerVO customer){
customerserv.updateCustomer(customer);
return "redirect:/customer/customer-list";
}
	



@RequestMapping(value="customer-list-download",method=RequestMethod.GET)
public ModelAndView getCustExcelList(){
						
		List<CustomerVO> customer_list=getCustVoList(customerserv.getCustomers());
	    return new ModelAndView("customerReportView","customer_list",customer_list);
			
}

@RequestMapping(value="bulk-customer-create",method=RequestMethod.GET)
private ModelAndView custBulkUploadView(){
	Map<String,String> map =new HashMap<String,String>();
	map.put("page_title", "Upload Customer");
	map.put("page_heading", "Upload Customer");
	ModelAndView mav=new ModelAndView("customer/bulk_create_view");
	mav.addAllObjects(map);
	return mav;
	
}


@RequestMapping(value="cust-upload-sample",method=RequestMethod.GET)
private ModelAndView custCreateSample() throws Exception{
	ModelAndView mav;
	List<CustomerVO> customer_vo_list=new ArrayList<CustomerVO>();
	String cust_sample_path=context.getRealPath("/sample/customer/customer_sample.xls");
	FileInputStream fis=new FileInputStream(new File(cust_sample_path));
	HSSFWorkbook workbook=new HSSFWorkbook(fis);
	
	HSSFSheet sheet=workbook.getSheetAt(0);
	boolean is_skip_heading=true;
	
	for(Row row : sheet){
		
		 if(!is_skip_heading){
		           CustomerVO customer=new CustomerVO();
		          
				    try{  
							  Cell cell1=row.getCell(1);   //First name
							  Cell cell2=row.getCell(2);   //Last Name
							  Cell cell3=row.getCell(3);   //Age
							  Cell cell4=row.getCell(4);   //Gender
							  Cell cell5=row.getCell(5);   //Email	
							  Cell cell6=row.getCell(6);   //City
							  Cell cell7=row.getCell(7);   //Country
							  
							  Cell cell8=row.getCell(8);   //Mobile
							  Cell cell9=row.getCell(9);   //Postal Code
							  Cell cell10=row.getCell(10); //Region
							  Cell cell11=row.getCell(11); //Created At
							  Cell cell12=row.getCell(12); //Description
					  
							  cell1.setCellType(CellType.STRING);
							  cell2.setCellType(CellType.STRING);
							  cell3.setCellType(CellType.STRING);
							  cell4.setCellType(CellType.STRING);
							  cell5.setCellType(CellType.STRING);
							  cell6.setCellType(CellType.STRING);
							  
							  cell7.setCellType(CellType.STRING);
							  cell8.setCellType(CellType.STRING);
							  cell9.setCellType(CellType.STRING);
							  cell10.setCellType(CellType.STRING);
							  
							  if(cell11!=null)
							   cell11.setCellType(CellType.STRING);
							  
							   cell12.setCellType(CellType.STRING);
					  
					  
					  
					  
							  customer.setFirst_name(cell1.getStringCellValue());
							  customer.setLast_name(cell2.getStringCellValue());
							  customer.setAge(Integer.parseInt(cell3.getStringCellValue()));
							  customer.setGender(cell4.getStringCellValue());
									  
							  customer.setEmail(cell5.getStringCellValue());
							  customer.setCity(cell6.getStringCellValue());
							  customer.setCountry(cell7.getStringCellValue());
							  customer.setMobile(cell8.getStringCellValue());
										  
							  customer.setPostal_code(cell9.getStringCellValue());
							  customer.setRegion(cell10.getStringCellValue());
							  customer.setCreated_at(cell11.getStringCellValue());
							  customer.setDescription(cell12.getStringCellValue());
							  
							  customer_vo_list.add(customer);
							  
				    }
				    catch(Exception e){
				    	e.getMessage();
				    } 	
		    }
		 is_skip_heading=false;
		
	}
	
	mav=new ModelAndView("customerReportView","customer_list",customer_vo_list);
	return mav;
	
	
}


@RequestMapping(value="bulk-customer-create-sbmt",method=RequestMethod.POST)
private ModelAndView bulkCustCreateSbmt(@RequestParam("customer_file") MultipartFile cutomer_file) throws Exception {
  
	 Map<String,String> map=new HashMap<String,String>();
	 ModelAndView mav=new ModelAndView("customer/bulk_create_view");
	 
	 HSSFWorkbook workbook=new HSSFWorkbook(cutomer_file.getInputStream());
	 HSSFSheet sheet=workbook.getSheetAt(0);
	 boolean is_skip_heading=true;

	 for(Row row:sheet){
		 
		  if(!is_skip_heading){
				      CustomerVO customer=new CustomerVO();
				    
				      
				    try{  
							  Cell cell1=row.getCell(1);   //First name
							  Cell cell2=row.getCell(2);   //Last Name
							  Cell cell3=row.getCell(3);   //Age
							  Cell cell4=row.getCell(4);   //Gender
							  Cell cell5=row.getCell(5);   //Email	
							  Cell cell6=row.getCell(6);   //City
							  Cell cell7=row.getCell(7);   //Country
							  
							  Cell cell8=row.getCell(8);   //Mobile
							  Cell cell9=row.getCell(9);   //Postal Code
							  Cell cell10=row.getCell(10); //Region
							  Cell cell11=row.getCell(11); //Created At
							  Cell cell12=row.getCell(12); //Description
					  
							  cell1.setCellType(CellType.STRING);
							  cell2.setCellType(CellType.STRING);
							  cell3.setCellType(CellType.STRING);
							  cell4.setCellType(CellType.STRING);
							  cell5.setCellType(CellType.STRING);
							  cell6.setCellType(CellType.STRING);
							  
							  cell7.setCellType(CellType.STRING);
							  cell8.setCellType(CellType.STRING);
							  cell9.setCellType(CellType.STRING);
							  cell10.setCellType(CellType.STRING);
							  
							  if(cell11!=null)
							   cell11.setCellType(CellType.STRING);
							  
							  cell12.setCellType(CellType.STRING);
					  
					  
					  
					  
							  customer.setFirst_name(cell1.getStringCellValue());
							  customer.setLast_name(cell2.getStringCellValue());
							  customer.setAge(Integer.parseInt(cell3.getStringCellValue()));
							  customer.setGender(cell4.getStringCellValue());
									  
							  customer.setEmail(cell5.getStringCellValue());
							  customer.setCity(cell6.getStringCellValue());
							  customer.setCountry(cell7.getStringCellValue());
							  customer.setMobile(cell8.getStringCellValue());
										  
							  customer.setPostal_code(cell9.getStringCellValue());
							  customer.setRegion(cell10.getStringCellValue());
							  customer.setCreated_at(cell11.getStringCellValue());
							  customer.setDescription(cell12.getStringCellValue());
					  
							  customerserv.addCustomer(customer);
												  
							  map.put("page_title", "Upload Customer");
							  map.put("page_heading", "Upload Customer");  
							  map.put("msg_sucess", "User Created successfully"); 
				         }
				        catch(Exception e){
				      	      map.put("msg_err", "Some Exception Occured While Creating Customer"); 	
				         }
					  
				  }
		  
		  is_skip_heading=false;
	 }
	 
	 mav.addAllObjects(map);
	 return mav;
	
}





/************Convert the country data object to country value object******************/
private List<CountryVO> getCountryVOList(List<CountryDO> country_list){
	List<CountryVO> country_vo_list=new ArrayList<CountryVO>();
	
	if(!country_list.isEmpty()){
		
	
	for(CountryDO country : country_list){
		CountryVO country_vo=new CountryVO();	
		
		country_vo.setId(country.getId());
		country_vo.setCode(country.getCountry_code());
		country_vo.setName(country.getCountry_name());
		
		country_vo_list.add(country_vo);
	}
	
	
	}
	return country_vo_list;
}


/*******************Convert the customerdo to customer do*************************/
private CustomerVO prepareCustVo(CustomerDo customer){
	CustomerVO csutomer_vo=new CustomerVO();
	
	csutomer_vo.setId(customer.getId());
	csutomer_vo.setAge(customer.getAge());
	csutomer_vo.setCountry(customer.getCountry());
	csutomer_vo.setCity(customer.getCity());
	
	csutomer_vo.setFirst_name(customer.getFirst_name());
	csutomer_vo.setLast_name(customer.getLast_name());
	csutomer_vo.setGender(customer.getGender());
	csutomer_vo.setEmail(customer.getEmail());
	
	csutomer_vo.setMobile(customer.getMobile());
	csutomer_vo.setPostal_code(customer.getPostal_code());
	csutomer_vo.setRegion(customer.getRegion());
	csutomer_vo.setDescription(customer.getDescription());
	csutomer_vo.setCreated_at(customer.getCreated_at());
	
	return csutomer_vo;
}

private List<CustomerVO> getCustVoList(List<CustomerDo> customer_list){
	
	List<CustomerVO> customer_vo_list=new ArrayList<CustomerVO>();
	
	if(!customer_list.isEmpty()){
	 for(CustomerDo cust:customer_list){
		
		 CustomerVO customer_vo=new CustomerVO();
		 customer_vo.setId(cust.getId());
		 customer_vo.setEmail(cust.getEmail());
		 customer_vo.setFirst_name(cust.getFirst_name());
		 customer_vo.setLast_name(cust.getLast_name());
		 customer_vo.setGender(cust.getGender());
		 customer_vo.setAge(cust.getAge());
		 customer_vo.setMobile(cust.getMobile());
		 customer_vo.setDescription(cust.getDescription());
		 customer_vo.setCity(cust.getCity());
		 customer_vo.setCountry(cust.getCountry());
		 customer_vo.setPostal_code(cust.getPostal_code());
		 customer_vo.setRegion(cust.getRegion());
		 customer_vo_list.add(customer_vo);
	 }	
		
	}
	
 return customer_vo_list;	
}










	
	
	

}
