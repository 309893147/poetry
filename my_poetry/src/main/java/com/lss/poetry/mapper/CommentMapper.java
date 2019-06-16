package com.lss.poetry.mapper;

import com.lss.poetry.pojo.Comment;
import com.lss.poetry.utils.MyMapper;

import java.util.List;

public interface CommentMapper extends MyMapper<Comment> {
	List<Comment>  getComments(Integer mpId);
}