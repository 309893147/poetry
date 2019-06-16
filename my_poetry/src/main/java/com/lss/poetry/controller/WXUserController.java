package com.lss.poetry.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.lss.poetry.pojo.User;

import com.lss.poetry.pojo.WXSessionModel;
import com.lss.poetry.service.UserService;
import com.lss.poetry.utils.HttpClientUtil;
import com.lss.poetry.utils.JsonResult;
import com.lss.poetry.utils.JsonUtils;

@Controller
public class WXUserController {
	@Autowired
	UserService userService;

	@ResponseBody
	@PostMapping("/wxLogin")
	public JsonResult wxLogin(String code) {
		// 微信get地址
		// https://api.weixin.qq.com/sns/
		// jscode2session?
		// appid=APPID&
		// secret=SECRET&
		// js_code=JSCODE&g
		// rant_type=authorization_code

//		System.out.println("" + code);
		String url = "https://api.weixin.qq.com/sns/jscode2session";
		Map<String, String> param = new HashMap<String, String>();
		param.put("appid", "wxc49e51168e1768aa");
		param.put("secret", "cf3e414b70c5381de8db68306c1aa2f6");
		param.put("js_code", code);
		param.put("grant_type", "authorization_code");

		String wxResult = HttpClientUtil.doGet(url, param);
		JSONObject wxJsonResult = JSONObject.parseObject(wxResult);

//		System.out.println(wxResult);
		// WXSessionModel model = JsonUtils.jsonToPojo(wxResult, WXSessionModel.class);
		// 作为session到
		return JsonResult.ok(wxJsonResult);

	}

	@ResponseBody
	@PostMapping("/wxSaveUser")
	public JsonResult wxSaveUser(User userInfo,String openId) {
//		 System.out.println(userInfo);
		userInfo.setId(openId);
		userService.addWXUser(userInfo);

		 System.out.println(userInfo);

		return JsonResult.ok(userInfo);
	}
}
