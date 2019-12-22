package com.services;

import java.util.List;

import com.model.MmvBean;

public interface MmvServ {
	public List<MmvBean> getMakeYear();
	public List<MmvBean> getMakeModelByYear(int year);
	public List<MmvBean> getModelByMake(String make);
}
