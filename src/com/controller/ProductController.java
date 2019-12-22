package com.controller;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

import javax.validation.Valid;

import com.model.CategoryBean;
import com.model.CategoryVO;
import com.model.ProductBean;
import com.model.ProductVO;
import com.services.CategoryServ;
import com.services.ProductServ;

@Controller
@RequestMapping("product")
public class ProductController {
	
	@Autowired	
	private CategoryServ category_serv;
	@Autowired
	private ProductServ product_serv;


	
@RequestMapping(value="create-new", method=RequestMethod.GET)	
public ModelAndView addProductView(){
			Map map=new HashMap();
			map.put("page_title","- Add Prodcut");
			map.put("page_heading", "Add Prodcut");
			map.put("category_list", getCategoryVO(category_serv.getCategoryies()));
			
				
			ModelAndView mav=new ModelAndView("product/add_product_view","product",new ProductVO());	
			mav.addAllObjects(map);	
			return mav;
}
	
	
	
	
	
@RequestMapping(value="create-prodcut-sbmt", method=RequestMethod.POST)
public ModelAndView addProductSbmt(@Valid @ModelAttribute("product") ProductVO product,BindingResult result){
	     
	
	      Map map=new HashMap();
	      ModelAndView mav;
	      
	      if(result.hasErrors())
	      {
	    	  map.put("page_title","- Add Prodcut");
	      	  map.put("page_heading", "Add Prodcut");
	      	  map.put("category_list", getCategoryVO(category_serv.getCategoryies()));
	      	  mav=new ModelAndView("product/add_product_view","product",product);
	    	  return mav; 
	      }
	      else
	      {
	    	
	    	  if(product_serv.addProduct(getProductBean(product)))
	    	  {
	    		  return  new ModelAndView("redirect:get-product-list");  
		      
	    	  }
	    	  map.put("page_title","- Add Prodcut");
	      	  map.put("page_heading", "Add Prodcut");
	      	  map.put("category_list", getCategoryVO(category_serv.getCategoryies()));
	      	  mav=new ModelAndView("product/add_product_view","product",product);
	    	  return mav;
	      }	

}
	
	
@RequestMapping(value="get-product-list", method=RequestMethod.GET)
public ModelAndView getProductList(){
		 Map map=new HashMap();
	     ModelAndView mav;
		
		
		map.put("page_title","- Product List");
	    map.put("page_heading", "Product List");
	    map.put("product_list",  getProductList( product_serv.getProducts()));
    	mav=new ModelAndView("product/product_list_view");
    	mav.addAllObjects(map);
  	    return mav;	
		
		
	}

@RequestMapping(value="get-product-download",method=RequestMethod.GET)
public ModelAndView getProductListExcel(){
	 return new ModelAndView("productReportView","product_list", getProductList( product_serv.getProducts()));	
}

@RequestMapping(value="product-bulk-create",method=RequestMethod.GET)
public ModelAndView productExcelUp(){
		Map <String,String> map=new HashMap<String,String>();
		map.put("page_title", "Create Bulk Upload Product");
		map.put("page_heading", "Create Bulk Upload Products");
		ModelAndView mav=new ModelAndView("product/product_bulk_view");
		mav.addAllObjects(map);
		return mav;
	
}

@RequestMapping(value="product-upload-sbmt",method=RequestMethod.POST)
public ModelAndView productUpload(@RequestParam("product_sheet") MultipartFile file) throws Exception {
		
	   Map<String,String> map=new HashMap();
	   
	   HSSFWorkbook hssf_book=null;
	   XSSFWorkbook xssf_book=null;
	   Sheet sheet=null;
	   
	   InputStream is=file.getInputStream();
	   
		  if(getFileExtention(file.getOriginalFilename()).equalsIgnoreCase("xls")){
			  hssf_book=new HSSFWorkbook(is) ;
			  sheet=hssf_book.getSheetAt(0);
			 
		  }
		  else if(getFileExtention(file.getOriginalFilename()).equalsIgnoreCase("xlsx"))
		  {
			  xssf_book=new XSSFWorkbook(is); 
			  sheet=xssf_book.getSheetAt(0);
		  }
		  else{
			 System.out.println("Invalid File Type Exception"); 
			
		  }
		
		
		boolean is_file_row=true;
		for (Row row:sheet)
		     {
			        ProductVO product=new ProductVO();
					
					if(!is_file_row)
					     {
						
								try{
										Cell cell1=row.getCell(1);
										Cell cell2=row.getCell(2);
										Cell cell3=row.getCell(3);
										
										Cell cell4=row.getCell(4);
										Cell cell5=row.getCell(5);
										Cell cell6=row.getCell(6);
										
										Cell cell7=row.getCell(7);
										Cell cell8=row.getCell(8);
										Cell cell9=row.getCell(9);
										
										
										if(cell1!=null)
											cell1.setCellType(CellType.STRING);
										if(cell2!=null)
											cell2.setCellType(CellType.STRING);
										if(cell3!=null)
											cell3.setCellType(CellType.STRING);
										if(cell4!=null)
											cell4.setCellType(CellType.STRING);
										if(cell5!=null)
											cell5.setCellType(CellType.STRING);
										if(cell6!=null)
											cell6.setCellType(CellType.STRING);
										if(cell7!=null)
											cell7.setCellType(CellType.STRING);
										if(cell8!=null)
											cell8.setCellType(CellType.STRING);
										if(cell9!=null)
											cell9.setCellType(CellType.STRING);
										
										
										product.setName(cell1.getStringCellValue());
										product.setTitle(cell2.getStringCellValue());
										product.setPrice(cell3.getStringCellValue());
										
										product.setColor(cell4.getStringCellValue());
										product.setModel_number(cell5.getStringCellValue());
										product.setModel_name(cell6.getStringCellValue());
										
										product.setCategory_id(cell7.getStringCellValue());
										product.setSub_category_id(cell8.getStringCellValue());
										product.setProduct_url(cell9.getStringCellValue());
											
										 if(product_serv.addProduct(getProductBean(product))){
											 
											 map.put("msg", "Sucessfull Uploaded"); 
										 }
										 else{
											 
											 map.put("msg", "Some Error Occured"); 
										 }
									
								  }
								catch(Exception e)
								  {
								 	 e.printStackTrace();
								     e.getMessage();
								     map.put("msg", "Some Exception Occured"); 
								 }
								System.out.println(product);
					    }
			
		     		is_file_row=false;
		       }
		
		is.close();
		ModelAndView mav=new ModelAndView("product/product_bulk_view"); 
		mav.addAllObjects(map);
	    return mav;
}







@RequestMapping(value="get-product-list-by-category", method=RequestMethod.GET)
public ModelAndView getProductListByCategory(){
		 Map map=new HashMap();
	     ModelAndView mav;
		 Map  product_in_each_cat=new HashMap();
		
		map.put("page_title","- Product List By Category");
	    map.put("page_heading", "Product List By Category");
	    map.put("product_list_by_category", product_serv.getProductByCategory() );
	    
	   
    	mav=new ModelAndView("product/product_list_by_category_view");
    	mav.addAllObjects(map);
  	    return mav;	
		
		
	}
//@ResponseBody
@RequestMapping(value="get-product-by-category", method=RequestMethod.POST)
public String getAllProdcutOfCate(@RequestParam("cate_id") int cate_id, ModelMap map){
	     
	    map.put("page_heading", "asdkfhaskjd fhjadsfh");
	    String product_section="<div class="+"row"+">"+ "skdfhaskj; dfjksdfhj"+"</div>";
	    map.put("product_list", getProductList(product_serv.getProductsByCate(cate_id)));
	    return "product/product_list_ajax_view";	
		
	}

private List<CategoryVO> getCategoryVO(List<CategoryBean> category_set){
	List<CategoryVO> category_vo_set=new ArrayList<CategoryVO>();
	
	if(!category_set.isEmpty())
	{
		for(CategoryBean category_b:category_set)
		{
		  CategoryVO category_vo=new CategoryVO();
		  category_vo.setId(category_b.getCategory_id());
		  category_vo.setCategory(category_b.getCategory());
		  category_vo.setPreference(  String.valueOf(category_b.getPreference()));
		  
		  category_vo_set.add(category_vo);
	
		}
		
	}
	
	return category_vo_set;
}

private ProductBean getProductBean(ProductVO product){
	
ProductBean product_bean= new ProductBean();

product_bean.setName(product.getName());
product_bean.setTitle(product.getTitle());
product_bean.setPrice(Integer.parseInt(product.getPrice()));
product_bean.setColor(product.getColor());
product_bean.setModel_number(product.getModel_number());
product_bean.setModel_name(product.getModel_name());
product_bean.setCategory_id(Integer.parseInt(product.getCategory_id()));
//product_bean.setSub_category_id(Integer.parseInt(product.getSub_category_id()));
product_bean.setProduct_url(product.getProduct_url());

return product_bean;

} 


private List<ProductVO> getProductList(List<ProductBean> product_bean_list){
	
	List<ProductVO> product_vo_list= new ArrayList<ProductVO>();
	
	if(!product_bean_list.isEmpty())
	 {
		  for(ProductBean product:product_bean_list)	
		   {
				 ProductVO product_vo=new ProductVO();
				 
				 product_vo.setId(product.getId());
				 product_vo.setName(product.getName());
				 product_vo.setTitle(product.getTitle());
				 product_vo.setPrice(Integer.toString(product.getPrice())   );
				 product_vo.setColor(product.getColor());
				 product_vo.setModel_number(product.getModel_number());
				 product_vo.setModel_name(product.getModel_name());
				 product_vo.setCategory_id(Integer.toString(product.getCategory_id()));
				 product_vo.setSub_category_id( Integer.toString(product.getSub_category_id()));
				 product_vo.setProduct_url(product.getProduct_url());
				 
				 product_vo_list.add(product_vo);
		  
		   }
		
	 }
	return product_vo_list;
}


private CategoryBean getCategoryBean(CategoryVO category){
	
	CategoryBean category_bean= new CategoryBean();
	category_bean.setCategory_id(category.getId());
	category_bean.setCategory(category.getCategory());
	category_bean.setPreference(Integer.parseInt(category.getPreference()));
	return category_bean;
	
}



private String getFileExtention(String file_name){
	
		String f_name=file_name;
		String file_ac_name=null;
		String file_ext=null;
		
		int dot_post=f_name.lastIndexOf('.');
		file_ac_name=f_name.substring(0, dot_post);
		
		file_ext=f_name.substring(dot_post+1, f_name.length());	
		return 	file_ext;
	
}

}
