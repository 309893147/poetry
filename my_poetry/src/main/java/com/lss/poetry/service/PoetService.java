package com.lss.poetry.service;

import com.lss.poetry.pojo.Poet;
import com.lss.poetry.utils.PagedResult;

import javax.validation.Valid;
import java.util.List;

public interface PoetService {
    public PagedResult getPoet(Integer poetPage, Integer pageSize);
    //后台
    public List<Poet> getAllPoet();
    public int addPoet(@Valid Poet poet);
    public List<Poet>  getPoetAllMpByPageName(String poetName);
    public void updatePoet(@Valid Poet poet);
    public void deletePoet(Integer poetId);
    // 按照MPID查询mp
    public Poet getPoetById(Integer id);
}
