package com.lss.poetry.controller;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baidu.ai.aip.AuthService;
import com.baidu.ai.aip.Base64Util;
import com.baidu.ai.aip.FileUtil;
import com.baidu.ai.aip.GsonUtils;
import com.baidu.ai.aip.HttpUtil;
import com.lss.poetry.pojo.ModernPoetry;
import com.lss.poetry.service.ModernPoetryService;
import com.lss.poetry.utils.JsonResult;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/upload")
public class UploadController {

	@Autowired
	ModernPoetryService modernPoetryService;

	@Value("${file-upload-path}")
	private String fileUploadPath;
	@Value("${file-upload-poetpath}")
	private String fileUploadPoetPath;

	@Value("${wxfile-upload-path}")
	private String wxfileUploadPath;
	@Value("${spring.tengxun.accessKey}")
	private String accessKey;
	@Value("${spring.tengxun.secretKey}")
	private String secretKey;
	@Value("${spring.tengxun.bucket}")
	private String bucket;
	@Value("${spring.tengxun.bucketName}")
	private String bucketName;
	@Value("${spring.tengxun.path}")
	private String path;
	@Value("${spring.tengxun.qianzui}")
	private String qianzui;

	@RequestMapping("/insert")
	@ResponseBody
	public Map<String, Object> uploadFile(MultipartFile img, Model model, HttpServletRequest request)
			throws IllegalStateException, IOException {

		// 原始名称
		String oldFileName = img.getOriginalFilename(); // 获取上传文件的原名
		Map<String, Object> map = new HashMap<String, Object>();
		// 新的图片名称
		String newFileName = UUID.randomUUID() + oldFileName.substring(oldFileName.lastIndexOf("."));
		// 新图片
		File newFile = new File(fileUploadPath + newFileName);
		System.out.println(newFile);
		// 将内存中的数据写入磁盘
		img.transferTo(newFile);

		// 请求url
		String url = "https://aip.baidubce.com/rest/2.0/solution/v1/img_censor/user_defined";
		try {
			// 本地文件路径fileUploadPath + "\\" + newFileName
			String filePath = fileUploadPath + newFileName;
			byte[] imgData = FileUtil.readFileByBytes(filePath);
			String imgStr = Base64Util.encode(imgData);
			String imgParam = URLEncoder.encode(imgStr, "UTF-8");

			String param = "image=" + imgParam;
//			System.out.println(param);
			// 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
			String accessToken = AuthService.getAuth();
			String result = HttpUtil.post(url, accessToken, param);
//			System.out.println(result);
			JSONObject jsonResult = JSONObject.parseObject(result);// 将java数组对象转换为json数组对象
			String conclusion = jsonResult.getString("conclusion");

			if (conclusion.equals("不合规")) {
				JSONArray data = jsonResult.getJSONArray("data");// 通过键值名获取数组格式的json对象
//				System.out.println(data);
				String msg = null;
				for (int i = 0; i < data.size(); i++) {
					JSONObject job = data.getJSONObject(i);
					msg = job.getString("msg");
				}
//				System.out.println(msg);
				map.put("error", msg);

			} else {
				// 将新图片名称返回到前端
				map.put("success", "成功啦");
				map.put("result2", result);
				map.put("url", newFileName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	@RequestMapping("/poetinsert")
	@ResponseBody
	public Map<String, Object> uploadPoetFile(MultipartFile img, Model model, HttpServletRequest request)
			throws IllegalStateException, IOException {

		// 原始名称
		String oldFileName = img.getOriginalFilename(); // 获取上传文件的原名
		Map<String, Object> map = new HashMap<String, Object>();
		// 新的图片名称
		String newFileName = UUID.randomUUID() + oldFileName.substring(oldFileName.lastIndexOf("."));
		// 新图片
		File newFile = new File(fileUploadPoetPath + newFileName);
		System.out.println(newFile);
		// 将内存中的数据写入磁盘
		img.transferTo(newFile);

		// 请求url
		String url = "https://aip.baidubce.com/rest/2.0/solution/v1/img_censor/user_defined";
		try {
			// 本地文件路径fileUploadPath + "\\" + newFileName
			String filePath = fileUploadPoetPath + newFileName;
			byte[] imgData = FileUtil.readFileByBytes(filePath);
			String imgStr = Base64Util.encode(imgData);
			String imgParam = URLEncoder.encode(imgStr, "UTF-8");

			String param = "image=" + imgParam;
//			System.out.println(param);
			// 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
			String accessToken = AuthService.getAuth();
			String result = HttpUtil.post(url, accessToken, param);
//			System.out.println(result);
			JSONObject jsonResult = JSONObject.parseObject(result);// 将java数组对象转换为json数组对象
			String conclusion = jsonResult.getString("conclusion");

			if (conclusion.equals("不合规")) {
				JSONArray data = jsonResult.getJSONArray("data");// 通过键值名获取数组格式的json对象
//				System.out.println(data);
				String msg = null;
				for (int i = 0; i < data.size(); i++) {
					JSONObject job = data.getJSONObject(i);
					msg = job.getString("msg");
				}
//				System.out.println(msg);
				map.put("error", msg);

			} else {
				// 将新图片名称返回到前端
				map.put("success", "成功啦");
				map.put("result2", result);
				map.put("url", newFileName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	// 人脸匹配
	@RequestMapping("/faceMatch")
	@ResponseBody
	public JsonResult faceMatch2(@RequestParam("file") MultipartFile img) throws IllegalStateException, IOException {

		Map<String, Object> map = new HashMap<String, Object>();
		String oldFileName = img.getOriginalFilename();
		String eName = oldFileName.substring(oldFileName.lastIndexOf("."));
		String newFileName = UUID.randomUUID() + eName;

		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DATE);
		// 1 初始化用户身份信息(secretId, secretKey)
		COSCredentials cred = new BasicCOSCredentials(accessKey, secretKey);
		// 2 设置bucket的区域, COS地域的简称请参照
		// https://cloud.tencent.com/document/product/436/6224
		ClientConfig clientConfig = new ClientConfig(new Region(bucket));
		// 3 生成cos客户端
		COSClient cosclient = new COSClient(cred, clientConfig);
		// bucket的命名规则为{name}-{appid} ，此处填写的存储桶名称必须为此格式
		String bucketName = this.bucketName;

		// 简单文件上传, 最大支持 5 GB, 适用于小文件上传, 建议 20 M 以下的文件使用该接口
		// 大文件上传请参照 API 文档高级 API 上传
		File localFile = null;

		// 请求url
		String aiFaceUrl = "https://aip.baidubce.com/rest/2.0/face/v3/search";
		try {

			localFile = File.createTempFile("temp", null);
			img.transferTo(localFile);
			// 指定要上传到 COS 上的路径
			String key = "/" + this.qianzui + "/" + year + "/" + month + "/" + day + "/" + newFileName;
			PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, localFile);
			PutObjectResult putObjectResult = cosclient.putObject(putObjectRequest);
			Date expiration = new Date(new Date().getTime() + 5 * 60 * 10000);
			URL url = cosclient.generatePresignedUrl(bucketName, key, expiration);

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// 设置请求方式为"GET"
			conn.setRequestMethod("GET");
			// 超时响应时间为5秒
			conn.setConnectTimeout(5 * 1000);
			// 通过输入流获取图片数据
			InputStream inStream = conn.getInputStream();
			// 得到图片的二进制数据，以二进制封装得到数据，具有通用性
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			// 创建一个Buffer字符串
			byte[] buffer = new byte[1024];
			// 每次读取的字符串长度，如果为-1，代表全部读取完毕
			int len = 0;
			// 使用一个输入流从buffer里把数据读取出来
			while ((len = inStream.read(buffer)) != -1) {
				// 用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
				outStream.write(buffer, 0, len);
			}
			// 关闭输入流
			inStream.close();
			byte[] imgData = outStream.toByteArray();
			// 对字节数组Base64编码

			// byte[] imgData = FileUtil.readFileByBytes(filePath);
			String imgStr = Base64Util.encode(imgData);
			String imgParam = URLEncoder.encode(imgStr, "UTF-8");

			// String param = "image=" + imgParam;

			// 本地文件路径fileUploadPath + "\\" + newFileName
			// String filePath = fileUploadPath + "\\" + newFileName;
//			byte[] imgData = FileUtil.readFileByBytes(filePath);
//			String imgStr = Base64Util.encode(imgData);
//
//			String imgParam = URLEncoder.encode(imgStr, "UTF-8");

//			System.out.println(imgParam);
			map.put("image", imgStr);
			map.put("liveness_control", "NONE");
			//组名
//			map.put("group_id_list", "poetry");
			map.put("group_id_list", "poetry");
			map.put("image_type", "BASE64");
			map.put("quality_control", "NONE");

			String param = GsonUtils.toJson(map);
			String accessToken = AuthService.getAuth();
			String result = HttpUtil.post(aiFaceUrl, accessToken, "application/json", param);
//		System.out.println(result);

//			 List<ModernPoetry> list= modernPoetryService.getMpByPinYin();

			JSONObject jsonObject = JSONObject.parseObject(result);
			JSONObject jsonObj = (JSONObject) jsonObject.get("result");

			JSONArray user_list = jsonObj.getJSONArray("user_list");

			JSONObject row = null;
			String pinYin = null;
			for (int i = 0; i < user_list.size(); i++) {
				// 提取出family中的所有
				row = user_list.getJSONObject(i);
				pinYin = (String) row.get("user_id");

//				System.out.println("user_id:" + pinYin);
			}

			List<ModernPoetry> list = modernPoetryService.getMpByPinYin(pinYin);
			String jsonString = JSON.toJSONString(list);
			JSONArray listJsonArray = JSONArray.parseArray(jsonString);
//			System.out.println(list);

			JSONArray twoJSONArray = joinJSONArray(user_list, listJsonArray);

			return JsonResult.ok(twoJSONArray);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.ok();
	}

	// 人脸匹配
	@RequestMapping("/faceMatch2")
	@ResponseBody
	public JsonResult faceMatch(@RequestParam("file") MultipartFile img) throws IllegalStateException, IOException {

		// 原始名称
		String oldFileName = img.getOriginalFilename(); // 获取上传文件的原名
		Map<String, Object> map = new HashMap<String, Object>();
		// 新的图片名称
		String newFileName = UUID.randomUUID() + oldFileName.substring(oldFileName.lastIndexOf("."));
		// 新图片
		File newFile = new File(fileUploadPath + "\\" + newFileName);
		System.out.println(newFile);
		// 将内存中的数据写入磁盘
		img.transferTo(newFile);

		// 请求url
		String url = "https://aip.baidubce.com/rest/2.0/face/v3/search";
		try {
			// 本地文件路径fileUploadPath + "\\" + newFileName
			String filePath = fileUploadPath + "\\" + newFileName;
			byte[] imgData = FileUtil.readFileByBytes(filePath);
			String imgStr = Base64Util.encode(imgData);

			String imgParam = URLEncoder.encode(imgStr, "UTF-8");

//			System.out.println(imgParam);
			map.put("image", imgStr);
			map.put("liveness_control", "NONE");
			map.put("group_id_list", "mp");
			map.put("image_type", "BASE64");
			map.put("quality_control", "NONE");

			String param = GsonUtils.toJson(map);
			String accessToken = AuthService.getAuth();
			String result = HttpUtil.post(url, accessToken, "application/json", param);
//			System.out.println(result);

			// List<ModernPoetry> list= modernPoetryService.getMpByPinYin();

			JSONObject jsonObject = JSONObject.parseObject(result);
			JSONObject jsonObj = (JSONObject) jsonObject.get("result");

			JSONArray user_list = jsonObj.getJSONArray("user_list");

			JSONObject row = null;
			String pinYin = null;
			for (int i = 0; i < user_list.size(); i++) {
				// 提取出family中的所有
				row = user_list.getJSONObject(i);
				pinYin = (String) row.get("user_id");

//				System.out.println("user_id:" + pinYin);
			}

			List<ModernPoetry> list = modernPoetryService.getMpByPinYin(pinYin);
			String jsonString = JSON.toJSONString(list);
			JSONArray listJsonArray = JSONArray.parseArray(jsonString);
//			System.out.println(list);

			JSONArray twoJSONArray = joinJSONArray(user_list, listJsonArray);

			return JsonResult.ok(twoJSONArray);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResult.ok();
	}

	/**
	 * 上传道腾讯云服务器（https://cloud.tencent.com/document/product/436/10199）
	 *
	 * @return
	 */
//	@RequestMapping(value = "/tengxun", method = RequestMethod.POST)
	@ResponseBody
	@RequestMapping("/tx")
	public Map<String, Object> Upload(MultipartFile img, HttpSession session) {
//		if (file == null) {
//			return new UploadMsg(0, "文件为空", null);
//		}
		Map<String, Object> map = new HashMap<String, Object>();
		String oldFileName = img.getOriginalFilename();
		String eName = oldFileName.substring(oldFileName.lastIndexOf("."));
		String newFileName = UUID.randomUUID() + eName;
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DATE);
		// 1 初始化用户身份信息(secretId, secretKey)
		COSCredentials cred = new BasicCOSCredentials(accessKey, secretKey);
		// 2 设置bucket的区域, COS地域的简称请参照
		// https://cloud.tencent.com/document/product/436/6224
		ClientConfig clientConfig = new ClientConfig(new Region(bucket));
		// 3 生成cos客户端
		COSClient cosclient = new COSClient(cred, clientConfig);
		// bucket的命名规则为{name}-{appid} ，此处填写的存储桶名称必须为此格式
		String bucketName = this.bucketName;

		// 简单文件上传, 最大支持 5 GB, 适用于小文件上传, 建议 20 M 以下的文件使用该接口
		// 大文件上传请参照 API 文档高级 API 上传
		File localFile = null;

		try {
			localFile = File.createTempFile("temp", null);
			img.transferTo(localFile);
			// 指定要上传到 COS 上的路径
			String key = "/" + this.qianzui + "/" + year + "/" + month + "/" + day + "/" + newFileName;
			PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, localFile);
			PutObjectResult putObjectResult = cosclient.putObject(putObjectRequest);
			Date expiration = new Date(new Date().getTime() + 5 * 60 * 10000);
			URL url = cosclient.generatePresignedUrl(bucketName, key, expiration);
			System.out.println(url);
			map.put("success", "成功啦");

			map.put("url", url);

//			return new UploadMsg(1, "上传成功", );
		} catch (IOException e) {
//			return new UploadMsg(-1, e.getMessage(), null);
		} finally {
			// 关闭客户端(关闭后台线程)
			cosclient.shutdown();
		}
		return map;
	}

	/**
	 * 上传道腾讯云服务器（https://cloud.tencent.com/document/product/436/10199）
	 *
	 * @return
	 */
//	@RequestMapping(value = "/tengxun", method = RequestMethod.POST)
	@ResponseBody
	@RequestMapping("/tx2")
	public Map<String, Object> Upload2(MultipartFile img, HttpSession session) {
//		if (file == null) {
//			return new UploadMsg(0, "文件为空", null);
//		}
		Map<String, Object> map = new HashMap<String, Object>();
		String oldFileName = img.getOriginalFilename();
		String eName = oldFileName.substring(oldFileName.lastIndexOf("."));
		String newFileName = UUID.randomUUID() + eName;
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DATE);
		// 1 初始化用户身份信息(secretId, secretKey)
		COSCredentials cred = new BasicCOSCredentials(accessKey, secretKey);
		// 2 设置bucket的区域, COS地域的简称请参照
		// https://cloud.tencent.com/document/product/436/6224
		ClientConfig clientConfig = new ClientConfig(new Region(bucket));
		// 3 生成cos客户端
		COSClient cosclient = new COSClient(cred, clientConfig);
		// bucket的命名规则为{name}-{appid} ，此处填写的存储桶名称必须为此格式
		String bucketName = this.bucketName;

		// 简单文件上传, 最大支持 5 GB, 适用于小文件上传, 建议 20 M 以下的文件使用该接口
		// 大文件上传请参照 API 文档高级 API 上传
		File localFile = null;
		// 请求url
		String aiUrl = "https://aip.baidubce.com/rest/2.0/solution/v1/img_censor/user_defined";
		try {
			localFile = File.createTempFile("temp", null);
			img.transferTo(localFile);
			// 指定要上传到 COS 上的路径
			String key = "/" + this.qianzui + "/" + year + "/" + month + "/" + day + "/" + newFileName;
			PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, localFile);
			PutObjectResult putObjectResult = cosclient.putObject(putObjectRequest);
			Date expiration = new Date(new Date().getTime() + 5 * 60 * 10000);
			URL url = cosclient.generatePresignedUrl(bucketName, key, expiration);

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// 设置请求方式为"GET"
			conn.setRequestMethod("GET");
			// 超时响应时间为5秒
			conn.setConnectTimeout(5 * 1000);
			// 通过输入流获取图片数据
			InputStream inStream = conn.getInputStream();
			// 得到图片的二进制数据，以二进制封装得到数据，具有通用性
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			// 创建一个Buffer字符串
			byte[] buffer = new byte[1024];
			// 每次读取的字符串长度，如果为-1，代表全部读取完毕
			int len = 0;
			// 使用一个输入流从buffer里把数据读取出来
			while ((len = inStream.read(buffer)) != -1) {
				// 用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
				outStream.write(buffer, 0, len);
			}
			// 关闭输入流
			inStream.close();
			byte[] imgData = outStream.toByteArray();
			// 对字节数组Base64编码

			// byte[] imgData = FileUtil.readFileByBytes(filePath);
			String imgStr = Base64Util.encode(imgData);
			String imgParam = URLEncoder.encode(imgStr, "UTF-8");

			String param = "image=" + imgParam;
//			System.out.println(param);
			// 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
			String accessToken = AuthService.getAuth();
			String result = HttpUtil.post(aiUrl, accessToken, param);
//			System.out.println(result);
			JSONObject jsonResult = JSONObject.parseObject(result);// 将java数组对象转换为json数组对象
			String conclusion = jsonResult.getString("conclusion");

			if (conclusion.equals("不合规")) {
				JSONArray data = jsonResult.getJSONArray("data");// 通过键值名获取数组格式的json对象
//				System.out.println(data);
				String msg = null;
				for (int i = 0; i < data.size(); i++) {
					JSONObject job = data.getJSONObject(i);
					msg = job.getString("msg");
				}
//				System.out.println(msg);
				map.put("error", msg);

			} else {
				// 将新图片名称返回到前端
				map.put("success", "成功啦");
				// map.put("result2", result);
				map.put("url", url);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	// 合并两个JSONArray
	private static JSONArray joinJSONArray(JSONArray array1, JSONArray array2) {
		StringBuffer sbf = new StringBuffer();
		JSONArray jSONArray = new JSONArray();
		try {
			int len = array1.size();
			for (int i = 0; i < len; i++) {
				JSONObject obj1 = (JSONObject) array1.get(i);
				if (i == len - 1)
					sbf.append(obj1.toString());
				else
					sbf.append(obj1.toString()).append(",");
			}
			len = array2.size();
			if (len > 0)
				sbf.append(",");
			for (int i = 0; i < len; i++) {
				JSONObject obj2 = (JSONObject) array2.get(i);
				if (i == len - 1)
					sbf.append(obj2.toString());
				else
					sbf.append(obj2.toString()).append(",");
			}

			sbf.insert(0, "[").append("]");
			jSONArray = jSONArray.parseArray(sbf.toString());
			return jSONArray;
		} catch (Exception e) {
		}
		return null;
	}

}