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


import org.springframework.beans.factory.annotation.Autowired;

import com.services.CategoryServ;
import com.services.ProductServ;


@Controller
@RequestMapping("product")
public class ProductApiController {
	@Autowired	
	private CategoryServ category_serv;
	@Autowired
	private ProductServ product_serv;
	
	
	
	@ResponseBody
	@RequestMapping(value="product-list",method=RequestMethod.GET)
	public List<ProductVO> getProducts(){
				return this.getProductList(product_serv.getProducts());
	}
	
	@ResponseBody
	@RequestMapping(value="product/{id}",method=RequestMethod.GET)
	public ProductVO getProduct(@PathVariable("id") int id){
			return this.getProductVo(product_serv.getProduct(id));
	}
	
	@ResponseBody
	@RequestMapping(value="get-product-by-cat", method=RequestMethod.POST)
	public List<ProductVO> getProductByCat(@RequestBody Map<String,String> map){
		
		int cat_id=Integer.parseInt(map.get(0));
		String model_name=map.get(1);
		return getProductList(product_serv.getProductByCat(cat_id, model_name));
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

	private ProductVO getProductVo(ProductBean prod){
		
		ProductVO product= new ProductVO();

		product.setId(prod.getId());
		product.setName(prod.getName());
		product.setTitle(prod.getTitle());
		product.setPrice(Integer.toString(prod.getPrice()));
		product.setColor(prod.getColor());
		product.setModel_number(prod.getModel_number());
		product.setModel_name(prod.getModel_name());
		product.setCategory_id(Integer.toString(prod.getCategory_id()));
		//product_bean.setSub_category_id(Integer.parseInt(product.getSub_category_id()));
		product.setProduct_url(prod.getProduct_url());

		return product;

		} 

}
