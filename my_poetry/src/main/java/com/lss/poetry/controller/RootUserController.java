package com.lss.poetry.controller;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lss.poetry.pojo.Msg;
import com.lss.poetry.pojo.RootUser;
import com.lss.poetry.pojo.User;
import com.lss.poetry.service.UserService;
import com.lss.poetry.utils.JsonResult;

@Controller
@RequestMapping("/root_user")
public class RootUserController {
    // 自动注入
    @Autowired
    UserService userService;

    @Autowired
    private Sid sid;

    // 正常访问login页面
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    // 登陆表单提交过来的路径
    @RequestMapping(value = "/checkLogin", method = RequestMethod.POST)
    public String checkLogin(HttpServletRequest request) throws Exception {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String smi = convertMD5(password);
        RootUser user = new RootUser();
        user.setUsername(username);
        user.setPassword(password);
        RootUser resultUser = userService.checkLogin(user);
        if (resultUser == null) {
            // 返回登录界面
            request.setAttribute("user", user);
            request.setAttribute("errorMsg", "请认真核对账号、密码！");
            return "login";
        } else {
            // 跳到主界面
            HttpSession session = request.getSession();
            session.setAttribute("currentUser", resultUser);
                return "index";
        }
    }

//    @ResponseBody
//    @PostMapping("/logout")
//    public JsonResult logout(String userId, HttpServletRequest request) {
//        // 移除session
//        HttpSession session = request.getSession();
//
//        session.removeAttribute(userId);
//        return JsonResult.ok();
//    }


    // MD5加密算法
    public static String convertMD5(String inStr) throws Exception {

        String MD5 = "";

        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] bytes = inStr.getBytes();
        byte[] digest = md5.digest(bytes);
        for (int i = 0; i < digest.length; i++) {
            // 摘要字节数组中各个字节的"十六进制"形式.
            String s1 = Integer.toHexString(digest[i]);

            // 如果是8个长度的,把前面的6个f去掉,只获取后面的
            if (s1.length() == 8) {
                s1 = s1.substring(6);
            }
            if (s1.length() == 1) {
                s1 = "0" + s1;
            }
            MD5 += s1;
        }
        return MD5;
    }

}