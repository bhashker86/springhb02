package com.services;

import com.model.ContactDO;
import com.model.ContactDo;
import com.model.ServiceBookingDo;
import com.model.TestDriveBean;

public interface ServiceRequestServ {

	public boolean addContact(ContactDo contact);	
	public boolean addService(ServiceBookingDo serv_req);
	public boolean addTDR(TestDriveBean tdr_data);
	public boolean createSellRequest(ContactDO  cust_detail);

	
}
