package com.hqm.rabbit.utils.responsemsg;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @作者 胡勤明
 * @时间 2022-01-05 17:12
 * @版本 1.0
 */
public class AjaxResult {
    public int code;
    public String msg;
    public Object data;
    public AjaxResult(){

    }
    public AjaxResult(int code) {
        this.code = code;
    }
    public AjaxResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public  AjaxResult(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    public static AjaxResult msg(int code, String msg){
        return new AjaxResult(code,msg);
    }
    public static AjaxResult msg(int code, String msg, Object data){
        return new AjaxResult(code,msg,data);
    }
    public static AjaxResult error(String msg){
        return new AjaxResult(0,msg);
    }
    public static AjaxResult error(String msg, Object data){
        return new AjaxResult(0,msg,data);
    }
    public static AjaxResult success( String msg, Object data){
        return new AjaxResult(1,msg,data);
    }
    public static AjaxResult success(String msg){
        return new AjaxResult(1,msg);
    }
    @Override
    public String toString() {
        return "{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}

