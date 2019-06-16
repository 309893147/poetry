package com.lss.poetry.pojo;

import javax.persistence.*;

public class Poet {
    @Id
    @Column(name = "poet_id")
    private Integer poetId;

    @Column(name = "poet_name")
    private String poetName;

    @Column(name = "poet_img")
    private String poetImg;

    private ModernPoetry modernPoetry;

    public ModernPoetry getModernPoetry() {
        return modernPoetry;
    }

    public void setModernPoetry(ModernPoetry modernPoetry) {
        this.modernPoetry = modernPoetry;
    }

    /**
     * @return poet_id
     */
    public Integer getPoetId() {
        return poetId;
    }

    /**
     * @param poetId
     */
    public void setPoetId(Integer poetId) {
        this.poetId = poetId;
    }

    /**
     * @return poet_name
     */
    public String getPoetName() {
        return poetName;
    }

    /**
     * @param poetName
     */
    public void setPoetName(String poetName) {
        this.poetName = poetName;
    }

    /**
     * @return poet_img
     */
    public String getPoetImg() {
        return poetImg;
    }

    /**
     * @param poetImg
     */
    public void setPoetImg(String poetImg) {
        this.poetImg = poetImg;
    }

    @Override
    public String toString() {
        return "Poet{" +
                "poetId=" + poetId +
                ", poetName='" + poetName + '\'' +
                ", poetImg='" + poetImg + '\'' +
                ", modernPoetry=" + modernPoetry +
                '}';
    }
}