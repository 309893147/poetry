package com.lss.poetry.service;

import java.util.List;

import javax.validation.Valid;

import com.lss.poetry.pojo.Collect;



public interface CollectService {
	
	public int addCollect(@Valid Collect collect);

	public List<Collect> getMyCollect(String uId);

	public void rmCollect(Integer id);

}
