package com.lss.poetry.pojo;

import javax.persistence.*;

public class Collect {
    @Id
    @Column(name = "collect_id")
    private Integer collectId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "mp_id")
    private Integer mpId;

    private String datetime;
    
    private ModernPoetry modernPoetry;
    
    private User user;
    
    

    public ModernPoetry getModernPoetry() {
		return modernPoetry;
	}

	public void setModernPoetry(ModernPoetry modernPoetry) {
		this.modernPoetry = modernPoetry;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	/**
     * @return collect_id
     */
    public Integer getCollectId() {
        return collectId;
    }

    /**
     * @param collectId
     */
    public void setCollectId(Integer collectId) {
        this.collectId = collectId;
    }

    /**
     * @return user_id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return mp_id
     */
    public Integer getMpId() {
        return mpId;
    }

    /**
     * @param mpId
     */
    public void setMpId(Integer mpId) {
        this.mpId = mpId;
    }

    /**
     * @return datetime
     */
    public String getDatetime() {
        return datetime;
    }

    /**
     * @param datetime
     */
    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

	@Override
	public String toString() {
		return "Collect [collectId=" + collectId + ", userId=" + userId + ", mpId=" + mpId + ", datetime=" + datetime
				+ ", modernPoetry=" + modernPoetry + ", user=" + user + "]";
	}
    
    
    
}