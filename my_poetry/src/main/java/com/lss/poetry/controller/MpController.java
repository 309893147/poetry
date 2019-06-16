package com.lss.poetry.controller;

import com.lss.poetry.pojo.Mp;
import com.lss.poetry.service.impl.MpService;
import com.lss.poetry.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mpels")
public class MpController {

    @Autowired
    private MpService mpService;

    @RequestMapping(method = RequestMethod.POST)
    public JsonResult save(@RequestBody Mp mp){
        mpService.save(mp);
        return JsonResult.ok("添加成功");

    }

    @RequestMapping(value = "/{key}",method = RequestMethod.GET)
    public  JsonResult findByKey(@PathVariable String key){

      List<Mp> mpList= mpService.findByKey(key);
        return JsonResult.ok(mpList);
    }

}
