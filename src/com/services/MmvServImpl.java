package com.services;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dao.*;
import com.model.*;

@Service
public class MmvServImpl implements MmvServ{

@Autowired	
private MmvDao mmv_dao;
	
	public List<MmvBean> getMakeYear(){
	   return 	mmv_dao.getMakeYear();
	}
	
	public List<MmvBean> getMakeModelByYear(int year){
		return mmv_dao.getMakeModelByYear(year);
	}
	
	public List<MmvBean> getModelByMake(String make){
		return mmv_dao.getModelByMake(make);
	}
}
