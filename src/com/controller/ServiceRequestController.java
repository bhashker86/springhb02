package com.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.el.MethodNotFoundException;
import javax.jws.WebService;
import javax.validation.Valid;
import javax.xml.ws.WebServiceException;

import org.codehaus.jackson.node.ObjectNode;

import org.hibernate.Query;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.*;
import com.services.CityServ;
import com.services.MmvServ;
import com.services.ServiceRequestServ;
import com.services.StateServ;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;  


//@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@Controller
@RequestMapping("services")
public class ServiceRequestController {
	
@Autowired	
private StateServ state_serivce;	
@Autowired
private CityServ city_service;
@Autowired
private MmvServ mmv_services;

@Autowired
private ServiceRequestServ common_services;

@ResponseBody
@RequestMapping(value="getStates", method=RequestMethod.GET)
 public List<StateVO> getStates(){
	return this.getStateVo(state_serivce.getStates());
 }


@ResponseBody
@RequestMapping(  value="getCities",
		          method=RequestMethod.POST,
		          consumes="application/json",
		          produces="application/json")

public List<CityVO> getCityByState(@RequestBody Map<String,String> map){
	
	int state_id=Integer.parseInt(map.get("state_id"));
	return this.getCityVo(city_service.getCityByState(state_id));
}


@ResponseBody
@RequestMapping(value="getAllCity",method=RequestMethod.GET)
public List<CityVO> getCityByState(){
	return this.getCityVo(city_service.getAllCity());
}

//Get all Make Year.
@ResponseBody
@RequestMapping(value="get-mm-years", method=RequestMethod.GET)
public List<MmvBean> getMakeYears(){
	return mmv_services.getMakeYear();
}

@ResponseBody
@RequestMapping(value="get-make-model",method=RequestMethod.POST)
private List<MmvBean> getMakeModel(@RequestBody Map<String,String> map){
	int year=Integer.parseInt(map.get("year"));
	return mmv_services.getMakeModelByYear(year);
}

@ResponseBody
@RequestMapping(value="get-model",method=RequestMethod.POST)
private List<MmvBean> getModel(@RequestBody Map<String,String> map){
	String make=map.get("make");
	return mmv_services.getModelByMake(make);
}



//Contact us service.
@ResponseBody
@RequestMapping(value="create-contact",method=RequestMethod.POST)
private Map<String,String> createContact(@RequestBody Map<String,String> contact_data){

	String  name;
	String name_error;
	long mobile=0;
	String mobile_error;
	int state_id=0;
	String state_error;
	int city_id=0;
	String city_error;
	String comment;
	String comment_error;
	Map <String,String> result=new HashMap<String,String>();
	
	
	boolean status=false;
	boolean validation_status=true;
	
	name=contact_data.get("full_name");
	if(!contact_data.get("mobile_number").isEmpty()){
		mobile=Long.parseLong(contact_data.get("mobile_number"));
	
	}else{
		mobile_error="Mobile field is empty";
	}
	
	
	if(!contact_data.get("state").isEmpty()){
		state_id=Integer.parseInt(contact_data.get("state"));
	}
	else{
		state_error="State filed is empty";
	}
	
	if(!contact_data.get("city").isEmpty()){
		city_id=Integer.parseInt(contact_data.get("city"));
	}
	else{
		city_error="City filed is empty";
	}
	
	comment=contact_data.get("comment");
	
	
	
	if(name=="" || "".equals(name)){
		name_error="Name field is required";
		result.put("name_error", name_error);
		validation_status=false;
	}
	if(mobile<=0){
		mobile_error="Mobile field is empty";
		result.put("mobile_error", mobile_error);
		validation_status=false;
	}
	if(state_id<=0){
		state_error="State filed is required";
		result.put("state_error", state_error);
		validation_status=false;
	}
	if(city_id<=0){
		city_error="City filed is required";
		result.put("city_error", city_error);
		validation_status=false;
		
	}
	if(comment=="" || "".equals(comment)){
		comment_error="Comment filed is required";
		result.put("comment_error", comment_error);
		validation_status=false;
	}
	
		
	ContactDo contact=new ContactDo();
	
	contact.setName(name);
	contact.setMobile(mobile);
	contact.setState_id(state_id);
	contact.setCity_id(city_id);
	contact.setComment(comment);
	
	
	if(validation_status){
	 status=common_services.addContact(contact);
	}
	
	if(status){
		 result.put("status", "true");
	 }
  else{
	     result.put("status", "false");	
	}
	return result;

}

//Service Request
@ResponseBody
@RequestMapping(value="create-service-request",method=RequestMethod.POST)
private Map<String,String> addServiceReq(@RequestBody Map<String,String> service_data){
	
	String  name;
	String  name_error;
	String  email;
	String  email_error;
	long mobile=0;
	String mobile_error;
	int state_id=0;
	String state_error;
	int city_id=0;
	String city_error;
	
	String make;
	String make_error;
	String model;
	String model_error;
	boolean dts=false;
	
	
	
	Map <String,String> result=new HashMap<String,String>();
	
	
	boolean status=false;
	boolean validation_status=true;
	
	name=service_data.get("full_name");
	email=service_data.get("email");
	
	if(!service_data.get("mobile_number").isEmpty()){
		mobile=Long.parseLong(service_data.get("mobile_number"));
	
	}else{
		mobile_error="Mobile field is empty";
	}
	
	
	if(!service_data.get("state").isEmpty()){
		state_id=Integer.parseInt(service_data.get("state"));
	}
	else{
		state_error="State filed is empty";
	}
	
	if(!service_data.get("city").isEmpty()){
		city_id=Integer.parseInt(service_data.get("city"));
	}
	else{
		city_error="City filed is empty";
	}
	
	make=service_data.get("make");
	model=service_data.get("model");
	dts=Boolean.parseBoolean(service_data.get("dts"));
	
	
	
	
	if(name=="" || "".equals(name)){
		name_error="Name field is required";
		result.put("name_error", name_error);
		validation_status=false;
	}
	if(mobile<=0){
		mobile_error="Mobile field is empty";
		result.put("mobile_error", mobile_error);
		validation_status=false;
	}
	if(email=="" || "".equals(email)){
		email_error="Email field is empty";
		result.put("email_error", email_error);
		validation_status=false;
	}
	
	if(state_id<=0){
		state_error="State filed is required";
		result.put("state_error", state_error);
		validation_status=false;
	}
	if(city_id<=0){
		city_error="City filed is required";
		result.put("city_error", city_error);
		validation_status=false;
		
	}
	if(make=="" || "".equals(make)){
		make_error="Comment filed is required";
		result.put("make_error", make_error);
		validation_status=false;
	}
	
	if(model=="" || "".equals(model)){
		model_error="Model field is required";
		result.put("model_error", model_error);
		validation_status=false;
	}
	
		ServiceBookingDo ser_req=new ServiceBookingDo();
		ser_req.setCustomer_name(name);
		ser_req.setEmail(email);
		ser_req.setMobile_number(mobile);
		ser_req.setState_id(state_id);
		ser_req.setCity_id(city_id);
		ser_req.setMake(make);
		ser_req.setModel(model);
		ser_req.setDts(dts);
		
	  
		if(validation_status){
		 status=common_services.addService(ser_req);
		}
	
		if(status){
			 result.put("status", "true");
		 }
	    else{
		     result.put("status", "false");	
	    }
		return result;
	
}


//Test drive request
@ResponseBody
@RequestMapping(value="test-drive",method=RequestMethod.POST)
private  ResponseEntity testDriveRequest(@RequestBody TestdriveVO test_drive_data ){

	HttpHeaders header=new HttpHeaders();
	ObjectMapper mapper=new ObjectMapper();
	header.setContentType(MediaType.APPLICATION_JSON);
	boolean status=false;
	System.out.println(test_drive_data);
	
	com.fasterxml.jackson.databind.node.ObjectNode response_node=mapper.createObjectNode();
	status=common_services.addTDR(getTDRBean(test_drive_data));
	
	
	if(status){
				response_node.put("code", HttpStatus.CREATED.toString());
				response_node.put("status", status);
				response_node.put("message","Test Drive Request Created Successfully");
				return new ResponseEntity<>(response_node,header,HttpStatus.CREATED);
	}
	else{
				response_node.put("code", HttpStatus.OK.toString());
				response_node.put("status", status);
				response_node.put("message","Test Drive Request Not Created");
				return new ResponseEntity<>(response_node,header,HttpStatus.OK);
	}
	
}

//Sell Request.

@ResponseBody
@RequestMapping(value="create-sell-request",method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
private ResponseEntity createSellRequest(@RequestBody Map<String,String> req_data){
	
	boolean status=false;
	HttpHeaders header=new HttpHeaders();
	ObjectMapper mapper=new ObjectMapper();
	header.setContentType(MediaType.APPLICATION_JSON);
	com.fasterxml.jackson.databind.node.ObjectNode response_node=mapper.createObjectNode();
	
	
	ContactVO contact_data=new ContactVO();
	contact_data.setCity_id(req_data.get("city_name"));
	contact_data.setPincode(req_data.get("pincode"));
	contact_data.setCust_name(req_data.get("user_name"));
	contact_data.setMobile(req_data.get("mobile"));
	contact_data.setEmail(req_data.get("email"));
	
	CardetailVO car_data=new CardetailVO();
	car_data.setMake_year(req_data.get("make_year"));
	car_data.setMake_month(req_data.get("make_month"));
	car_data.setMake(req_data.get("make"));
	car_data.setModel(req_data.get("model"));
	car_data.setVariant(req_data.get("version"));
	car_data.setColor(req_data.get("color"));
	car_data.setFuel_type(req_data.get("fuel"));
	car_data.setnumber_of_ownor(req_data.get("owner"));
	car_data.setKilometer_used(req_data.get("kilometer_used"));
	car_data.setExpected_price(req_data.get("exp_price"));
	
	ContactDO contact_bean=getContactBean(contact_data);
	CardetailDO cardetail_bean =getCardetailBean(car_data);
	
	contact_bean.setCardetail(cardetail_bean);
	status=common_services.createSellRequest(contact_bean);
	
	if(status){
		response_node.put("code", HttpStatus.CREATED.toString());
		response_node.put("status", status);
		response_node.put("message", "Sell Request Created Successfully");
		return new ResponseEntity<>(response_node,header,HttpStatus.CREATED);
	}
	response_node.put("code", HttpStatus.BAD_REQUEST.toString());
	response_node.put("status", status);
	response_node.put("message", "Unable To Create Sell Request");
	return new ResponseEntity<>(response_node,header,HttpStatus.BAD_REQUEST);
	
}


//Method to handle validation error
@ExceptionHandler(MethodArgumentNotValidException.class)
@ResponseStatus(HttpStatus.BAD_REQUEST)
@ResponseBody
private ValidationErrorDTO processValidationError(MethodArgumentNotValidException errors){
	 BindingResult result = errors.getBindingResult();
	 List<FieldError> fieldErrors = result.getFieldErrors();
	 return processFieldErrors(fieldErrors);
}

private ValidationErrorDTO processFieldErrors(List<FieldError> fieldErrors) {
    ValidationErrorDTO dto = new ValidationErrorDTO();

    for (FieldError fieldError: fieldErrors) {
        String error_msg = fieldError.getDefaultMessage();
        dto.addFieldError(fieldError.getField(), error_msg);
    }

    return dto;
}


 private List<StateVO> getStateVo(List<StateBean> states){

	List<StateVO> state_vo_list= new ArrayList<StateVO>();
	
	if(!states.isEmpty())
	 {
		  for(StateBean state:states)	
		   { 
			     StateVO state_vo=new StateVO();
			     state_vo.setId(state.getState_id());
			     state_vo.setName(state.getState_name());
						 
			     state_vo_list.add(state_vo);
		  
		   }
		
	 }
	return state_vo_list;
	
}
private List<CityVO> getCityVo(List<CityBean> cities){

	List<CityVO> city_vo_list= new ArrayList<CityVO>();
	
	if(!cities.isEmpty())
	 {
		  for(CityBean city:cities)	
		   { 
			     CityVO city_vo=new CityVO();
			     city_vo.setId(city.getCity_id());
			     city_vo.setName(city.getCity_name());
						 
			     city_vo_list.add(city_vo);
		  
		   }
		
	 }
	return city_vo_list;
	
}

private  TestDriveBean getTDRBean(TestdriveVO tdr_data){

	
	TestDriveBean tdr_bean=new TestDriveBean();
	
	tdr_bean.setCust_name(tdr_data.getName());
	tdr_bean.setCust_email(tdr_data.getEmail());
	tdr_bean.setCust_mobile(Long.parseLong(tdr_data.getMobile()));
	tdr_bean.setState_id(tdr_data.getState_id());
	
	tdr_bean.setCity_id(tdr_data.getCity_id());
	tdr_bean.setDealership(tdr_data.getDealership());
	tdr_bean.setMake_year(tdr_data.getMake_year());
	tdr_bean.setMake(tdr_data.getMake());
	tdr_bean.setModel(tdr_data.getMake());
	tdr_bean.setSchedule_time(tdr_data.getScedule_time());
	
	return tdr_bean;
	
}

private ContactDO getContactBean(ContactVO contact){
	
	ContactDO contact_data=new ContactDO();
	
	contact_data.setCity_id(Integer.parseInt(contact.getCity_id()));
	contact_data.setPincode(Integer.parseInt(contact.getPincode()));
	contact_data.setCust_name(contact.getCust_name());
	contact_data.setMobile(Long.parseLong(contact.getMobile()));
	contact_data.setEmail(contact.getEmail());
	//contact_data.setCardetail(contact.getCardetail());
	return contact_data;
}

private CardetailDO getCardetailBean(CardetailVO car_data){
	
	CardetailDO car_req_data=new CardetailDO();
	
	car_req_data.setMake_year(car_data.getMake_year());
	car_req_data.setMake_month(car_data.getMake_month());
	car_req_data.setMake(car_data.getMake());
	car_req_data.setModel(car_data.getModel());
	car_req_data.setVariant(car_data.getVariant());
	car_req_data.setColor(car_data.getColor());
	car_req_data.setFuel_type(car_data.getFuel_type());
	car_req_data.setNumber_of_owner(Integer.parseInt(car_data.getnumber_of_ownor()));
	car_req_data.setKilometer_used(Integer.parseInt(car_data.getKilometer_used()));
	car_req_data.setExpected_price(Long.parseLong(car_data.getExpected_price()));
	return car_req_data;
	
 }


}


