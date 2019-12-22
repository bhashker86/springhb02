package com.dao;

import com.model.*;
import java.util.*;
public interface ServiceRequestDao {
	
public boolean addContact(ContactDo contact);
public boolean addService(ServiceBookingDo serv_req);
public boolean addTDR(TestDriveBean tdr_data);
public boolean createSellRequest(ContactDO  cust_detail);


}
