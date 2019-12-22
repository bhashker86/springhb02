package com.dao;
import com.model.*;
import java.util.*;

public interface CityDao {
public List<CityBean> getAllCity();
public List<CityBean> getCityByState(int state_id);
	
}
