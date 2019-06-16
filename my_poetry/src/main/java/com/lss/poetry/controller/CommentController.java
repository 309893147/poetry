package com.lss.poetry.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baidu.ai.aip.AuthService;
import com.baidu.ai.aip.HttpUtil;
import com.lss.poetry.pojo.Comment;
import com.lss.poetry.service.CommentService;
import com.lss.poetry.utils.JsonResult;
import com.lss.poetry.utils.PagedResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/wxComment")
public class CommentController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CommentService commentService;

	@RequestMapping(value = "/getComments", method = RequestMethod.POST)
	@ResponseBody
	private JsonResult getComments(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                   @RequestParam("mpId") Integer mpId) {
		logger.info("getComments");
		// 引入PageHelper分页插件
		// 查询前调用，传入页码和记录数
//		PageHelper.startPage(pageNum, 2);
		List<Comment> list = new ArrayList<Comment>();
		

		PagedResult pagedResult = commentService.getComments(mpId,pageNum);
		// PageInfo包装查询结果，封装了详细的分页信息和详细数据
		// 连续显示5页
//		PageInfo pageInfo = new PageInfo(list, 2);

		return JsonResult.ok(pagedResult);

	}
	
	@RequestMapping(value = "/addComment", method = RequestMethod.POST)
	@ResponseBody
	private JsonResult addComment(Comment comment) {
		String params;
		String content=comment.getContent();
		System.out.print(content);
		String url="https://aip.baidubce.com/rest/2.0/antispam/v2/spam";
		String result;
		try {



			// 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
			String accessToken = AuthService.getAuth();//"【调用鉴权接口获取的token】";
			params = "content="+content;
			result = HttpUtil.post(url, accessToken, "application/x-www-form-urlencoded",params);
            System.out.println(result);




			JSONObject jsonObject = JSONObject.parseObject(result);
			JSONObject jsonObj = (JSONObject) jsonObject.get("result");
			System.out.println(jsonObj);
			Integer spam = jsonObj.getInteger("spam");
			System.out.println(spam);
			if(spam!=0){
				return JsonResult.errorMap("评论未通过!");
			}else{
				logger.info("addComment");
				Date date = new Date();
				DateFormat df2 = DateFormat.getDateTimeInstance();// 可以精确到时分秒

				comment.setTime(df2.format(date));
				commentService.addComment(comment);
				return JsonResult.ok("评论通过!");
			}
//			JSONArray reject = jsonObj.getJSONArray("reject");
//
//			System.out.println(reject);
//
//
//
//			JSONObject row = null;
//			String hit = null;
//			Integer label = null;
//			for (int i = 0; i < reject.size(); i++) {
//				// 提取出family中的所有
//				row = reject.getJSONObject(i);
////				hit = (String) row.get("hit");
//				label = (Integer) row.get("label");
//				System.out.println(row);
////				System.out.println(hit);
//				System.out.println(label);
////				System.out.println("user_id:" + pinYin);
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}


		return null;
		
	}

//	@RequestMapping(value = "/addComment", method = RequestMethod.POST)
//	private @ResponseBody
//	Result<Map<String,String>>insertComment(@RequestParam("userId") String userId,
//			@RequestParam("mpId") int mpId,@RequestParam("comment") String comment,
//			) {
//		logger.info("addComment");
//		Map<String, String> resultMap = new HashMap<String, String>();
//		try{
//			Integer rCId = -1;
//			if(!replyCommentId.equals(""))
//				rCId = Integer.parseInt(replyCommentId);
//			commentService.insertComment(Integer.parseInt(sourceId), comment, userId,userName,rCId,replyUserName,userPhoto);
//			resultMap.put("msg", "insertComment success");
//		}catch(Exception e){
//			System.out.print(e);
//			resultMap.put("msg", "insertComment error");
//		}
//		return new Result<Map<String, String>>(true, resultMap);
//	}
//	
//	@RequestMapping(value = "/deleteComment", method = RequestMethod.POST)
//	private @ResponseBody
//	Result<Map<String,String>>deleteComment(@RequestParam("commentId") String commentId) {
//		logger.info("deleteComment");
//		Map<String, String> resultMap = new HashMap<String, String>();
//		try{
//			commentService.deleteComment(commentId);
//			resultMap.put("msg", "deleteComment success");
//		}catch(Exception e){
//			System.out.print(e);
//			resultMap.put("msg", "deleteComment error");
//		}
//		return new Result<Map<String, String>>(true, resultMap);
//	}
}
