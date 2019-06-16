package com.lss.poetry.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lss.poetry.pojo.ModernPoetry;
import com.lss.poetry.pojo.Msg;
import com.lss.poetry.pojo.Poet;
import com.lss.poetry.service.PoetService;
import com.lss.poetry.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class PoetController extends BasicController {
	// 自动注入
	@Autowired
	PoetService poetService;
	@Value("${file-upload-path}")
	private String fileUploadPath;

	@ResponseBody
	@RequestMapping(value = "/getPoet", method = RequestMethod.GET) // ajax方法
	public Msg getPoetByPage(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
		// 引入PageHelper分页插件
		// 查询前调用，传入页码和记录数
		PageHelper.startPage(pn, 10);
		// startPage紧跟着的这个查询就是一个分页查询
		List<Poet> poetList = poetService.getAllPoet();

		// PageInfo包装查询结果，封装了详细的分页信息和详细数据
		// 连续显示5页
		PageInfo pageInfo = new PageInfo(poetList, 10);
		return Msg.success().add("pageInfo", pageInfo);
	}

	@RequestMapping(value = "/addPoet", method = RequestMethod.POST) // ajax方法
	public String addPoet(@Valid Poet poet, HttpServletRequest request, Model model) {

		poetService.addPoet(poet);

		return "poet/poetList";
	}

	@ResponseBody
	@RequestMapping(value = "/deletePoet/{poetId}", method = RequestMethod.DELETE)
	public JsonResult deleteMp(@PathVariable("poetId") Integer poetId, @Valid  Poet poet) {// id是从路径value 取出

		poetService.deletePoet(poetId);
		return JsonResult.ok();
	}

	@ResponseBody
	@RequestMapping(value = "/updatePoet/{poetId}", method = RequestMethod.PUT)
	public String updateMp(@Valid Poet poet) {// id是从路径value 取出
		poetService.updatePoet(poet);
		return null;
	}

	// 编辑,根据id查询信息

	@RequestMapping(value = "/getPoets{id}", method = RequestMethod.GET)
	public String getPoetById(Integer id, Model model) {// id是从路径value 取出
		Poet poet = poetService.getPoetById(id);
		model.addAttribute(poet);
		return "poet/updatePoet";

	}


}
