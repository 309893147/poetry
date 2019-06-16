package com.lss.poetry.pojo;

import javax.persistence.Column;
import javax.persistence.Id;

public class Comment {
    @Id
    @Column(name = "comment_id")
    private Integer commentId;

    @Column(name = "mp_id")
    private Integer mpId;

    private String content;

    @Column(name = "from_userid")
    private String fromUserid;

    private String time;
    
    //联和用户表查询
    private User user;
    

    public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	/**
     * @return comment_id
     */
    public Integer getCommentId() {
        return commentId;
    }

    /**
     * @param commentId
     */
    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
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
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return from_userid
     */
    public String getFromUserid() {
        return fromUserid;
    }

    /**
     * @param fromUserid
     */
    public void setFromUserid(String fromUserid) {
        this.fromUserid = fromUserid;
    }
}