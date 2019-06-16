package com.lss.poetry.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.MessageDigest;

@Controller
public class RootController {

    // 正常访问mpList页面
    @RequestMapping("/mp/mpList")
    public String mpList() {
        return "mp/mpList";
    }



    @RequestMapping("/poet/poetList")
    public String poetList() {
        return "poet/poetList";
    }
    
    // 正常访问login页面
    @RequestMapping("/login")
    public String index() {
        return "login";
    }
    
    // 正常访问addMp页面
    @RequestMapping("/mp/addMp")
    public String addMp() {
        return "mp/addMp";
    }

    // 正常访问addMp页面
    @RequestMapping("/poet/addPoet")
    public String addPoet() {
        return "poet/addPoet";
    }

    // 正常访问updateMp页面
    @RequestMapping("/mp/updateMp")
    public String getModernPoetryById() {
        return "mp/updateMp";
    }


    // 正常访问updateMp页面
    @RequestMapping("/poet/updatePoet")
    public String getPoetById() {
        return "poet/updatePoet";
    }
  

}