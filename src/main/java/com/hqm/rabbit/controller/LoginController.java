package com.hqm.rabbit.controller;

import com.hqm.rabbit.utils.security.JwtUtils;
import com.hqm.rabbit.domain.vo.SysUser;
import com.hqm.rabbit.service.LoginService;
import com.hqm.rabbit.utils.responsemsg.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @作者 胡勤明
 * @时间 2022-01-05 16:31
 * @版本 1.0
 * @作用 登陆测试
 */

@RestController
@RequestMapping("/system/post")
public class    LoginController {
    @Autowired
    private JwtUtils jwtutils;

    @Autowired
    public RedisTemplate redisTemplate;

    @Autowired
    public LoginService loginService;

    @RequestMapping("/getLogin")
    public String getLogin(String name) {
        return "请求成功" + name;
    }

    @Autowired
    JwtUtils jwtUtils;
    /**
     * 创建人 胡勤明
     * 创建时间 2022/1/10 15:44
     * 方法作用 登陆验证并验证存储信息
     */
    @PostMapping("/postLogin")
    public AjaxResult login(@RequestBody SysUser UserVo) {
        if(UserVo.getUsername()==null|| UserVo.getPassword() == null) {
            return new AjaxResult(0, "账号或密码不能为空");
        }
        String token=loginService.login(UserVo);
        HashMap<String, String> stringStringHashMap = new HashMap<String, String>();
        stringStringHashMap.put("token",token);
        return AjaxResult.success("token生成成功",stringStringHashMap);
    }


    @PostMapping("/userinfo")
    public AjaxResult userinfo(String token) {
        System.out.println("token获取用户信息");
        SysUser userVo = jwtUtils.getTokentoUserVO(token);
        return AjaxResult.success("token生成成功",userVo);
    }
}
