package com.lss.poetry.controller;


import com.lss.poetry.pojo.Poet;
import com.lss.poetry.service.PoetService;
import com.lss.poetry.utils.JsonResult;
import com.lss.poetry.utils.PagedResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/wx")
public class WxPoetController extends BasicController {
	// 自动注入
	@Autowired
	PoetService poetService;
	@Value("${file-upload-path}")
	private String fileUploadPath;

	@ResponseBody
	@PostMapping(value = "/selectAuthor") // ajax方法
	public JsonResult getPoetWithJson(Integer poetPage, Integer pageSize) throws Exception {

		if (poetPage == null) {
			poetPage = 1;
		}

		if (pageSize == null) {
			pageSize = POET_PAGE_SIZE;
		}else {
			pageSize = POET_PAGE_SIZE;
		}
//		System.out.println("" + poetPage);
		PagedResult result = poetService.getPoet(poetPage, pageSize);
		//List<Poet> poetList = poetService.getPoet();

		return JsonResult.ok(result);
	}


	@ResponseBody
	@PostMapping(value = "/getPoetAllMp") // ajax方法
	public JsonResult getPoetAllMpByPageName(String poetName)  {

//		if (poetName == null) {
//			poetName = "海子";
//		}


//		System.out.println("" + poetName);
		List<Poet> poetList = poetService.getPoetAllMpByPageName(poetName);


		return JsonResult.ok(poetList);
	}





}
