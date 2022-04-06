package com.hqm.rabbit.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hqm.rabbit.domain.vo.SysUserVo;
import com.hqm.rabbit.service.UserService;
import com.hqm.rabbit.utils.responsemsg.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService UserService;

    @GetMapping("/getuserlist")
    public AjaxResult getUserList(int pageNum,int pagesize){
        PageHelper.startPage(pageNum,pagesize);
        List<SysUserVo> sysUsers = UserService.selectUserVo();
        PageInfo pageInfo = new PageInfo(sysUsers);
        return new AjaxResult(1,"用户查询成功",pageInfo);
    }



    @PostMapping("/inseruser")
    public AjaxResult inserUser(@RequestBody SysUserVo UserVo){
        System.out.println(UserVo);
        int i = UserService.insertUser(UserVo);
        return new AjaxResult(1,"添加成功",i);
    }
}
