package com.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.CityDao;
import com.model.CityBean;

@Service
public class CityServImpl implements CityServ{
	
	@Autowired
	private CityDao city_data;
	
	public List<CityBean> getAllCity(){
		return city_data.getAllCity();
	}
	
	public List<CityBean> getCityByState(int state_id){
		return city_data.getCityByState(state_id);
	}

}
