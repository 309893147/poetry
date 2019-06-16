package com.lss.poetry.service.impl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lss.poetry.mapper.CollectMapper;
import com.lss.poetry.mapper.CollectMapperCustom;
import com.lss.poetry.pojo.Collect;

import com.lss.poetry.service.CollectService;
@Service
public class CollectServiceImpl implements CollectService{
	@Autowired
	private CollectMapper collectMapper;
	
	@Autowired
	private CollectMapperCustom collectMapperCustom;
	@Override
	public int addCollect(@Valid Collect collect) {
		return collectMapper.insertSelective(collect);
		
	}

	@Override
	public List<Collect> getMyCollect(String uId) {
		return collectMapperCustom.getMyCollect(uId);
	}

	@Override
	public void rmCollect(Integer id) {
		collectMapper.deleteByPrimaryKey(id);
	}

	

}
