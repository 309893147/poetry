package com.lss.poetry.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lss.poetry.mapper.PoetMapper;
import com.lss.poetry.pojo.Poet;
import com.lss.poetry.service.PoetService;

import com.lss.poetry.utils.PagedResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
@Service
public class PoetServiceImpl implements PoetService
{
    @Autowired
    private PoetMapper poetMapper;
    @Override
    public PagedResult getPoet(Integer poetPage, Integer pageSize) {
        PageHelper.startPage(poetPage, pageSize);
        List<Poet> poetList = poetMapper.selectByExample(null);

        PageInfo<Poet> pageList = new PageInfo<>(poetList);

        PagedResult pagedResult = new PagedResult();
        pagedResult.setPage(poetPage);
        pagedResult.setTotal(pageList.getPages());
        pagedResult.setRows(poetList);
        pagedResult.setRecords(pageList.getTotal());

        return pagedResult;
//        List<Poet> poets=poetMapper.selectPoet();
//        return poets;
    }

    @Override
    public List<Poet> getAllPoet() {
        return poetMapper.selectByExample(null);
    }

    @Override
    public int addPoet(@Valid Poet poet) {

        return poetMapper.insertSelective(poet);
    }

    @Override
    public List<Poet> getPoetAllMpByPageName(String poetName) {
        List<Poet> poetList=   poetMapper.getPoetAllMpByPageName(poetName);
        return poetList;
    }


    @Override
    public void updatePoet(@Valid Poet poet) {
        poetMapper.updateByPrimaryKeySelective(poet);
    }


    @Override
    public void deletePoet(Integer poetId) {
        poetMapper.deleteByPrimaryKey(poetId);
    }

    @Override
    public Poet getPoetById(Integer id) {
        Poet poet = poetMapper.selectByPrimaryKey(id);
        return poet;
    }
}
