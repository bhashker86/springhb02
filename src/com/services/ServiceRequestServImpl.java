package com.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.ServiceRequestDao;
import com.model.ContactDO;
import com.model.ContactDo;
import com.model.ServiceBookingDo;
import com.model.TestDriveBean;

@Service
public class ServiceRequestServImpl implements ServiceRequestServ {

	@Autowired
	private ServiceRequestDao common_service;
	
	
	
	public boolean addContact(ContactDo contact){
	   return 	common_service.addContact(contact);
	}
	
	public boolean addService(ServiceBookingDo serv_req){
	 return common_service.addService(serv_req);
	}
	
	public boolean addTDR(TestDriveBean tdr_data){
		return common_service.addTDR(tdr_data);
	}

	public boolean createSellRequest(ContactDO  cust_detail){
		return common_service.createSellRequest(cust_detail);
	}

}
