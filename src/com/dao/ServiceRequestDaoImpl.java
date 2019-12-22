package com.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.model.*;

@Repository
public class ServiceRequestDaoImpl implements ServiceRequestDao{
	
	@Resource(name="sessionFactory")
    protected SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
           this.sessionFactory = sessionFactory;
    }
    
    
    public boolean addContact(ContactDo contact){
    	Session session=sessionFactory.openSession();
    	int id=0;
    	id=(int)session.save(contact);
    	if(id>0){
    		return true;
    	}
    	return false;
    }
    
    public boolean addService(ServiceBookingDo serv_req){
    	Session session=sessionFactory.openSession();
    	
    	int id=0;
    	id=(int)session.save(serv_req);
    	
    	if(id>0){
    		return true;
    	}
    	else{
    		return false;
    	}
    	
    }
    
    
    public boolean addTDR(TestDriveBean tdr_data){
    	Session session=sessionFactory.openSession();
    	int id=0;
    	
    	id=(int)session.save(tdr_data);
    	if(id>0){
    		return true;
    	}
    	else{
    		return false;
    	}
    	
    }
    
    
    public boolean createSellRequest(ContactDO  cust_detail){
    	Session session=sessionFactory.openSession();
    	int id=0;
    	
    	id=(int)session.save(cust_detail);
    	if(id>0){
    		return true;
    	}
    	else{
    		return false;
    	}
    	
    }



    
	

}
