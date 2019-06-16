package com.lss.poetry.service.impl;

import com.lss.poetry.dao.MpSearchDao;
import com.lss.poetry.pojo.Mp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MpService {

    @Autowired
    private MpSearchDao mpSearchDao;

    public void save(Mp mp){
        mpSearchDao.save(mp);
    }



    public List<Mp> findByKey(String key) {
        return   mpSearchDao.findByTitleOrAuthorOrContentLike(key,key,key);
    }
}
