package com.lss.poetry.service;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;


import com.lss.poetry.pojo.ModernPoetry;
import com.lss.poetry.utils.PagedResult;




public interface ModernPoetryService {
	public List<ModernPoetry> getAllMp();
	public PagedResult getAllModernPoetry(Integer page, Integer pageSize);
	public int addMp(@Valid ModernPoetry modernPoetry);
	 // 按照MPID查询mp
    public ModernPoetry getModernPoetryById(Integer id);
    //随机查询mp
    public List<ModernPoetry> randomQueryMp();
	public void updateMp(@Valid ModernPoetry modernPoetry);
/*	//查询用户自己收藏得mp
	public List<ModernPoetry> queryOwnCollect(String uId);*/
	public List<ModernPoetry> getMpByPinYin(String pinYin);
	public void deleteMp(Integer mpId);
	public Integer addViews(Integer views,Integer mpId);

}
