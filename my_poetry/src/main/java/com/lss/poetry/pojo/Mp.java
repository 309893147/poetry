package com.lss.poetry.pojo;

import org.springframework.data.elasticsearch.annotations.Field;

import javax.persistence.Id;
import org.springframework.data.elasticsearch.annotations.Document;

//elasticsearch
@Document(indexName = "modertn_poetry",type ="mp")
public class Mp {

    @Id
    private  Integer id;

    //是否索引,就是看该域是否能被搜索
    //是否分词,就表示搜索的时候是整体匹配还是单词匹配
   //是否存储,就是是否在页面上显示
    @Field(index=true,analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String title;
    @Field(index=true,analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String author;
    @Field(index=true,analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
