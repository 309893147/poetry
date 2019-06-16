package com.lss.poetry.pojo;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 *
 * @author ls
 */
public class Msg {
    //状态码 100-成功 200-失败
    private int code;
    //提示信息
    private String msg;

    private String ok;    // 不使用
    //用户要返回给浏览器的数据
    private Map<String, Object> extend = new HashMap<String, Object>();

    //状态码
    public static Msg success() {
        Msg result = new Msg();
        result.setCode(100);
        result.setMsg("处理成功");
        return result;
    }

    public static Msg fail() {
        Msg result = new Msg();
        result.setCode(200);
        result.setMsg("处理失败");
        return result;
    }

    public static Msg ok(String data) {
        Msg msg = new Msg();
        msg.setOk(data);
        return msg;
    }

    public static Msg ok() {
        Msg msg = new Msg();
        msg.setOk(null);
        return msg;
    }

    public Msg add(String key, Object value) {
        this.getExtend().put(key, value);
        return this;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getExtend() {
        return extend;
    }

    public void setExtend(Map<String, Object> extend) {
        this.extend = extend;
    }

    public String getOk() {
        return ok;
    }

    public void setOk(String ok) {
        this.ok = ok;
    }


}
