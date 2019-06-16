package com.lss.poetry.mapper;

import java.util.List;

import com.lss.poetry.pojo.ModernPoetry;
import com.lss.poetry.utils.MyMapper;

public interface ModernPoetryMapper extends MyMapper<ModernPoetry> {

	List<ModernPoetry> getMpDesc();
	List<ModernPoetry> randomQueryMp();

	List<ModernPoetry> getMpByPinYin( String pinYin);

	void updateViews(Integer newMpViews,Integer mpId);
}