package com.lss.poetry.pojo;

import javax.persistence.*;
import java.io.Serializable;


@Table(name = "modern_poetry")

public class ModernPoetry implements Serializable {
    /**
     * Id
     */
    @Id
    @Column(name = "mp_id")
    private Integer mpId;

    /**
     * 题目
     */
    @Column(name = "mp_title")
    private String mpTitle;

    /**
     * 部分内容
     */
    @Column(name = "mp_content")
    private String mpContent;

    /**
     * 图片
     */
    @Column(name = "mp_img")
    private String mpImg;

    /**
     * 作者
     */
    @Column(name = "mp_author")
    private String mpAuthor;

    /**
     * 时间
     */
    @Column(name = "mp_datetime")
    private String mpDatetime;


    @Column(name = "pin_yin")
    private String pinYin;

    /**
     * 全文
     */
    @Column(name = "mp_detail")
    private String mpDetail;

    @Column(name = "views")
    private Integer views;

    /**
     * 获取Id
     *
     * @return mp_id - Id
     */
    public Integer getMpId() {
        return mpId;
    }

    /**
     * 设置Id
     *
     * @param mpId Id
     */
    public void setMpId(Integer mpId) {
        this.mpId = mpId;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    /**
     * 获取题目
     *
     * @return mp_title - 题目
     */
    public String getMpTitle() {
        return mpTitle;
    }

    /**
     * 设置题目
     *
     * @param mpTitle 题目
     */
    public void setMpTitle(String mpTitle) {
        this.mpTitle = mpTitle;
    }

    /**
     * 获取部分内容
     *
     * @return mp_content - 部分内容
     */
    public String getMpContent() {
        return mpContent;
    }

    /**
     * 设置部分内容
     *
     * @param mpContent 部分内容
     */
    public void setMpContent(String mpContent) {
        this.mpContent = mpContent;
    }

    /**
     * 获取图片
     *
     * @return mp_img - 图片
     */
    public String getMpImg() {
        return mpImg;
    }

    /**
     * 设置图片
     *
     * @param mpImg 图片
     */
    public void setMpImg(String mpImg) {
        this.mpImg = mpImg;
    }

    /**
     * 获取作者
     *
     * @return mp_author - 作者
     */
    public String getMpAuthor() {
        return mpAuthor;
    }

    /**
     * 设置作者
     *
     * @param mpAuthor 作者
     */
    public void setMpAuthor(String mpAuthor) {
        this.mpAuthor = mpAuthor;
    }

    /**
     * 获取时间
     *
     * @return mp_datetime - 时间
     */
    public String getMpDatetime() {
        return mpDatetime;
    }

    /**
     * 设置时间
     *
     * @param mpDatetime 时间
     */
    public void setMpDatetime(String mpDatetime) {
        this.mpDatetime = mpDatetime;
    }



    /**
     * @return pin_yin
     */
    public String getPinYin() {
        return pinYin;
    }

    /**
     * @param pinYin
     */
    public void setPinYin(String pinYin) {
        this.pinYin = pinYin;
    }

    /**
     * 获取全文
     *
     * @return mp_detail - 全文
     */
    public String getMpDetail() {
        return mpDetail;
    }

    /**
     * 设置全文
     *
     * @param mpDetail 全文
     */
    public void setMpDetail(String mpDetail) {
        this.mpDetail = mpDetail;
    }



}