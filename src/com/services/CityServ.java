package com.services;

import java.util.List;
import com.model.CityBean;

public interface CityServ {

	public List<CityBean> getAllCity();
	public List<CityBean> getCityByState(int state_id);
}
