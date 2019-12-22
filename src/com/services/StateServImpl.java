package com.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.dao.CategoryDao;
import com.model.StateBean;
import com.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.model.*;
import com.dao.*;

@Service
public class StateServImpl implements StateServ{
	
	@Autowired
	StateDao sate_dao;
	
	
	
	
	public List<StateBean>getStates(){
		return sate_dao.getStates();
		
	}

}
