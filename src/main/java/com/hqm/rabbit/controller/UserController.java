package com.hqm.rabbit.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hqm.rabbit.domain.vo.SysUser;
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

    @GetMapping("/getuser")
    public AjaxResult getUser(int pageNum,int pagesize){
        PageHelper.startPage(pageNum,pagesize);
        List<SysUser> sysUsers = UserService.selectUserVo();
        PageInfo pageInfo = new PageInfo(sysUsers);
        return new AjaxResult(1,"用户查询成功",pageInfo);
    }

    @PostMapping("/inseruser")
    public AjaxResult inserUser(){
        List<SysUser> list=new ArrayList();
        for (int i=2000;i<2500;i++){
            SysUser sysUser = new SysUser();
            sysUser.setUsername(i+"");
            sysUser.setPassword("aaaaaaaaaaa");
            list.add(sysUser);
        }
        int i = UserService.insertUser(list);
        return new AjaxResult(1,"添加成功",i);
    }
}
