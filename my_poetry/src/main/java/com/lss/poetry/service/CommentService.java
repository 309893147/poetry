package com.lss.poetry.service;

import com.lss.poetry.pojo.Comment;
import com.lss.poetry.utils.PagedResult;

public interface CommentService {
	//查询评论
	PagedResult getComments(Integer mpId, Integer pageNum);

	void addComment(Comment comment);

}
