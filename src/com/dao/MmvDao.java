package com.dao;
import java.util.*;

import com.model.MmvBean;

public interface MmvDao {
	
public List<MmvBean> getMakeYear();
public List<MmvBean> getMakeModelByYear(int year);
public List<MmvBean> getModelByMake(String make);


}
