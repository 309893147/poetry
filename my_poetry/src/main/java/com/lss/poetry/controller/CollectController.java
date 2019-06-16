package com.lss.poetry.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lss.poetry.pojo.Collect;

import com.lss.poetry.service.CollectService;

import com.lss.poetry.utils.JsonResult;

@Controller
public class CollectController {
	// 自动注入
	@Autowired
	CollectService collectService;

	@ResponseBody
	@RequestMapping(value = "/addCollect", method = RequestMethod.POST) // ajax方法
	public JsonResult addCollect(String userId,Integer mpId) {

		Date date = new Date();
		DateFormat df2 = DateFormat.getDateTimeInstance();// 可以精确到时分秒
//		System.out.println(df2.format(date));

		Collect collect=new Collect();
//		System.out.println(collect);
		collect.setDatetime(df2.format(date));
		collect.setMpId(mpId);
		collect.setUserId(userId);
		collectService.addCollect(collect);

		return JsonResult.ok(collect);
	}

	@ResponseBody
	@RequestMapping(value = "/getMyCollect{uId}", method = RequestMethod.GET) // ajax方法)
	public JsonResult getMyCollect(String uId) throws Exception {

		List<Collect> list = collectService.getMyCollect(uId);

//		System.out.println(list);
		return JsonResult.ok(list);
	}


	@ResponseBody
	@RequestMapping(value = "/rmCollect{id}", method = RequestMethod.DELETE)
	public JsonResult rmCollect(Integer id) {// id是从路径value 取出

		collectService.rmCollect(id);

		return JsonResult.ok();

	}
}
