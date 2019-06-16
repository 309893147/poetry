package com.lss.poetry.controller;

import java.net.URLEncoder;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.baidu.ai.aip.AuthService;
import com.baidu.ai.aip.Base64Util;
import com.baidu.ai.aip.FileUtil;
import com.baidu.ai.aip.HttpUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lss.poetry.pojo.Collect;
import com.lss.poetry.pojo.ModernPoetry;
import com.lss.poetry.pojo.Msg;
import com.lss.poetry.service.ModernPoetryService;
import com.lss.poetry.utils.JsonResult;
import com.lss.poetry.utils.PagedResult;

@Controller
@RequestMapping("/wx")
public class WxModernPoetryController extends BasicController {
	// 自动注入
	@Autowired
	ModernPoetryService modernPoetryService;
	@Value("${file-upload-path}")
	private String fileUploadPath;



	@ResponseBody
	@RequestMapping(value = "/getModernPoetry", method = RequestMethod.GET) // ajax方法
	public Msg getModernPoetryWithJson(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
		// 引入PageHelper分页插件
		// 查询前调用，传入页码和记录数
		PageHelper.startPage(pn, 3);
		// startPage紧跟着的这个查询就是一个分页查询
		List<ModernPoetry> modernPoetrysList = modernPoetryService.getAllMp();

		// PageInfo包装查询结果，封装了详细的分页信息和详细数据
		// 连续显示5页
		PageInfo pageInfo = new PageInfo(modernPoetrysList, 3);
		return Msg.success().add("pageInfo", pageInfo);
	}

	/**
	 * 
	 * @Description: 分页和搜索查询视频列表 isSaveRecord：1 - 需要保存 0 - 不需要保存 ，或者为空的时候
	 */
	@ResponseBody
	@PostMapping(value = "/getMp")
	public JsonResult showAll(Integer page, Integer pageSize) throws Exception {

		if (page == null) {
			page = (int) Math.floor(Math.random() * 5);
		}

		if (pageSize == null) {
			pageSize = PAGE_SIZE;
		}

		PagedResult result = modernPoetryService.getAllModernPoetry(page, pageSize);
		return JsonResult.ok(result);
	}
	
	@ResponseBody
	@PostMapping(value = "/randomQueryMp")
	public JsonResult randomQuery() throws Exception {


		List<ModernPoetry> list = modernPoetryService.randomQueryMp();
	
		return JsonResult.ok(list);
	}




	// 如果直接发送ajax put 请求 封装的对象 全是null
	// 要能支持直接发送put请求 需要配置 HttpPutContentFilter
	// 他将请求的数据解析包装成一个map.request被重新包装 request.getPARAMETER()被重写
	@ResponseBody
	@RequestMapping(value = "/updateMp/{mpId}", method = RequestMethod.PUT)
	public String updateMp(@Valid ModernPoetry modernPoetry) {// id是从路径value 取出
		Date date = new Date();
		DateFormat df2 = DateFormat.getDateTimeInstance();// 可以精确到时分秒
//		System.out.println(df2.format(date));
		modernPoetry.setMpDatetime(df2.format(date));
		modernPoetryService.updateMp(modernPoetry);
		return null;

	}

	@ResponseBody
	@PostMapping(value = "/addViews")
	public JsonResult addViews(Integer views, Integer mpId) throws Exception {
		System.out.println(mpId);
		// 得到处理后的浏览量
		Integer i = modernPoetryService.addViews(views, mpId);

		return JsonResult.ok(i);
	}
	
	@ResponseBody
	@RequestMapping(value = "/deleteMp/{mpId}", method = RequestMethod.DELETE)
	public JsonResult deleteMp(@PathVariable("mpId")Integer mpId,@Valid ModernPoetry modernPoetry) {// id是从路径value 取出
		
		modernPoetryService.deleteMp(mpId);
		return JsonResult.ok();
	}

	@ResponseBody
	@RequestMapping(value = "/getMpById/{id}", method = RequestMethod.GET)
	public JsonResult getMpById(@PathVariable("id") Integer id) {// id是从路径value 取出
		ModernPoetry modernPoetry = modernPoetryService.getModernPoetryById(id);
		System.out.println(id);
		return JsonResult.ok(modernPoetry);
	}



/*	@ResponseBody
	@RequestMapping(value = "/myCollect/{uId}", method = RequestMethod.GET) // ajax方法
	public JsonResult myCollect(@PathVariable("uId") String uId) throws Exception {

		List<ModernPoetry> list = modernPoetryService.queryOwnCollect(uId);

		return JsonResult.ok(list);
	}*/

	
}
