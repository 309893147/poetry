package com.lss.poetry.dao;

import com.lss.poetry.pojo.Mp;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface MpSearchDao extends ElasticsearchRepository <Mp,String>{
    public List<Mp> findByTitleOrAuthorOrContentLike(String title,String author,String content);
}
