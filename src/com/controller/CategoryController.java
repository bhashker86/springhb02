package com.controller;
import java.io.*;

import java.util.*;

import javax.servlet.ServletContext;

import javax.validation.Valid;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.model.CategoryBean;
import com.model.CategoryVO;
import com.model.FileModel;
import com.services.*;


@Controller
@RequestMapping("category")
public class CategoryController {

	@Autowired	
	private CategoryServ categoryServ;	
	@Autowired
	private ServletContext context; 
	
		

	
	
@RequestMapping(value="add",method=RequestMethod.GET)	
public ModelAndView addCategory(ModelMap model){
	
		Map map=new HashMap();
		map.put("page_title", "Add Category");
		map.put("page_heading", "Add Category");
		map.put("msg", "All Fields Are Required");
		ModelAndView mav=new ModelAndView("category/add_category_view","category_ent", new CategoryVO());
		mav.addAllObjects(map);
		return mav;
	
}	

@RequestMapping(value="add-sbmt",method=RequestMethod.POST)
public ModelAndView addCategory(@Valid @ModelAttribute("category_ent") CategoryVO category_ent,BindingResult result, ModelMap model){
		Map map=new HashMap();
		System.out.println(category_ent);
		
		if(result.hasErrors())
		{
			map.put("page_title", "Add Category");
			map.put("page_heading", "Add Category");
			map.put("msg", "All Fields Are Required");
			ModelAndView mav=new ModelAndView("category/add_category_view","category_ent", category_ent);
			mav.addAllObjects(map);
			return mav;
		}
		else
		{
			try{
		         categoryServ.addCategory(getCategoryBean(category_ent));
			   }
			catch(Exception ex)
			    {
					map.put("page_title", "Add Category");
					map.put("page_heading", "Add Category");
					map.put("err_msg", ex.getMessage());
					ModelAndView mav=new ModelAndView("category/add_category_view","category_ent", category_ent);
					mav.addAllObjects(map);
					return mav;
				
			    }
			
		    ModelAndView mav=new ModelAndView("redirect:category-list");
		    return mav;
		}

}

@RequestMapping(value="category-list",method=RequestMethod.GET)
public ModelAndView getCategoryList(){
		Map map=new HashMap();
		List<CategoryVO> category_set= new ArrayList<CategoryVO>();
		category_set=getCategoryVO(categoryServ.getCategoryies());
		
		map.put("page_title", "Category List");
		map.put("page_heading", "Category List");
		map.put("category_list", category_set);
		ModelAndView mav=new ModelAndView("category/category_list_view");
		mav.addAllObjects(map);
		return mav;
	
}

@RequestMapping(value="category-list-download",method=RequestMethod.GET)
public  ModelAndView getCatListDownload(){
	 return new ModelAndView("categoryReportView","category_list",getCategoryVO(categoryServ.getCategoryies()));	
}


@RequestMapping(value="create-category",method=RequestMethod.GET)
public ModelAndView categoryBulkCreate(){
	
		Map<String,String> map=new HashMap<String,String>();
		map.put("page_title", "Upload Category");
		map.put("page_heading", "Upload category");
		ModelAndView mav=new ModelAndView("category/bulk_category_view");
		mav.addAllObjects(map);
		return mav;
	
}

@RequestMapping(value="bulk-cat-sbmt", method=RequestMethod.POST)
public ModelAndView catBulCreateSbmt(@RequestParam("category_file") MultipartFile  category_file) throws Exception{
	
	HSSFWorkbook workbook=new HSSFWorkbook(category_file.getInputStream()); 
	HSSFSheet sheet=workbook.getSheetAt(0);
	
	Map<String,String> map=new HashMap<String,String>();
	map.put("page_title", "Upload Category");
	map.put("page_heading", "Upload category");
	ModelAndView mav=new ModelAndView("category/bulk_category_view");
	int row_numb=1;
	for(Row row : sheet)
	  {
		if(row_numb!=1)
		{
			 CategoryVO  category=new CategoryVO();
			 try{
					 Cell cell1= row.getCell(1);
					 Cell cell2=row.getCell(2);
					 if(cell1!=null)
					  cell1.setCellType(CellType.STRING);
					 if(cell2!=null)
						 cell2.setCellType(CellType.STRING);
					 
					 
					 category.setCategory(cell1.getStringCellValue());
					 category.setPreference(cell2.getStringCellValue());
					 
					 if(categoryServ.addCategory(getCategoryBean(category)))
					  {
						  map.put("msg_sucess", "Category Created Successfully");	 
					  }
					 else
					  {
						 map.put("msg_err", "Some Error Occured While Creating Category");	
					  }
					 
			    }
			 catch(org.hibernate.exception.ConstraintViolationException exp1)
			    {
			
				          map.put("msg_err",  "Duplicate Entry Found");	
			    } 
			 catch(Exception exp)
			    {
						  map.put("msg_err",  "Duplicate Entry Found");	
					 
			    }
		}
		 row_numb++;
	  }
	workbook.close();
	mav.addAllObjects(map);
	return mav;
}

@RequestMapping(value="cate-smpl-sheet",method=RequestMethod.GET)
public ModelAndView catSampleDownload() throws IOException{
	
	
	String file_wtih_path=context.getRealPath("/sample/category/category_sample.xls");;
	FileInputStream fis=new FileInputStream(new File(file_wtih_path));
	HSSFWorkbook workbook=new HSSFWorkbook(fis);
	List<CategoryVO> category_list=new ArrayList<CategoryVO>();
	
	HSSFSheet sheet= workbook.getSheetAt(0);
	
	int skip_heading=1;
	
	try{
	
			for(Row row:sheet){
				
			    CategoryVO category=new CategoryVO();
				if(skip_heading!=1){
				 
			 	  
				 	Cell cell1=row.getCell(0);
				 	Cell cell2=row.getCell(1);
				 	Cell cell3=row.getCell(2);
				 	
				 	cell1.setCellType(CellType.STRING);
					cell2.setCellType(CellType.STRING);
					cell3.setCellType(CellType.STRING);
				 	
				 	category.setId(Integer.parseInt(cell1.getStringCellValue()));
					category.setCategory(cell2.getStringCellValue());
				 	category.setPreference(cell3.getStringCellValue());
				 	
				 	
				 	category_list.add(category); 	
			 	
				}
				skip_heading++;
		    }
			 
	} 
	catch(Exception e){
		e.printStackTrace();
	}
	workbook.close();
	fis.close();
	return new ModelAndView("categoryReportView","category_list",category_list);
}



@RequestMapping(value="file-uploade", method=RequestMethod.GET)
public ModelAndView fileUpload(){
	
		FileModel file=new FileModel();	
		Map map=new HashMap();
		map.put("page_title", "File Upload");
		map.put("page_heading", "File Upload");
		
		ModelAndView mav=new ModelAndView("category/file-upload-view","file",file);
		mav.addAllObjects(map);
		return mav;
	
}


@RequestMapping(value="file-upload-sbmt",method=RequestMethod.POST)
public ModelAndView fileUploadSbmt(FileModel file , BindingResult result ) throws IOException 
{
	
		 if(result.hasErrors())
		  {
			 Map map=new HashMap();
			 map.put("page_title", "File Upload");
			 map.put("page_heading", "File Upload");
		
			 ModelAndView mav=new ModelAndView("category/file-upload-view","file",file);
			 mav.addAllObjects(map);
			 return mav; 
		 
		  }	
		 
		     MultipartFile multipart_file=file.getFile();	
		     String upload_path=context.getRealPath("")+ File.separator+"temp"+File.separator;
		     FileCopyUtils.copy(multipart_file.getBytes(), new File(upload_path + file.getFile().getOriginalFilename()));
		     Map ma=new HashMap();
		     ma.put("page_title", "File Upload");
		     ma.put("page_heading", "File Upload");
		     ma.put("msg", "File Uploaded SuccessFully");
		
			 ModelAndView mav=new ModelAndView("category/file-upload-view","file",file);
			 mav.addAllObjects(ma);
			 return mav; 
  
}






private CategoryBean getCategoryBean(CategoryVO category){
	
		CategoryBean category_bean= new CategoryBean();
		category_bean.setCategory_id(category.getId());
		category_bean.setCategory(category.getCategory());
		category_bean.setPreference(Integer.parseInt(category.getPreference()));
		return category_bean;
	
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





}
