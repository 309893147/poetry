package com.lss.poetry.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lss.poetry.mapper.CommentMapper;
import com.lss.poetry.pojo.Comment;
import com.lss.poetry.service.CommentService;
import com.lss.poetry.utils.PagedResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentMapper commentMapper;
	
	
	@Override
	public PagedResult getComments(Integer mpId, Integer pageNum) {
		PageHelper.startPage(pageNum, 6);
		List<Comment> list =commentMapper.getComments(mpId);
		PageInfo<Comment> pageList = new PageInfo<>(list);

		PagedResult pagedResult = new PagedResult();
		pagedResult.setPage(pageNum);
		pagedResult.setTotal(pageList.getPages());
		pagedResult.setRows(list);
		pagedResult.setRecords(pageList.getTotal());

		return pagedResult;
	}


	@Override
	public void addComment(Comment comment) {
		// TODO Auto-generated method stub
		commentMapper.insert(comment);
	}
	
	

}
