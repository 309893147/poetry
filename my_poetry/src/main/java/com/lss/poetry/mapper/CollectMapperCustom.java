package com.lss.poetry.mapper;

import java.util.List;


import com.lss.poetry.pojo.Collect;


public interface CollectMapperCustom  {


	List<Collect> getMyCollect(String uId);
}