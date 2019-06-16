package com.lss.poetry.mapper;

import com.lss.poetry.pojo.Poet;
import com.lss.poetry.utils.MyMapper;

import java.util.List;

public interface PoetMapper extends MyMapper<Poet> {
   List<Poet> selectPoet();
   List<Poet> getPoetAllMpByPageName(String pageName);
}