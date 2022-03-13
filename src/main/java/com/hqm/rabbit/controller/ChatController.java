package com.hqm.rabbit.controller;


import com.hqm.rabbit.domain.entity.SysChat;
import com.hqm.rabbit.domain.vo.SysUserVo;
import com.hqm.rabbit.service.ChatService;
import com.hqm.rabbit.service.LoginService;
import com.hqm.rabbit.utils.responsemsg.AjaxResult;
import com.hqm.rabbit.utils.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * @作者 胡勤明
 * @时间 2022-01-05 16:31
 * @版本 1.0
 * @作用 登陆测试
 */

@RestController
@RequestMapping("/system/chat")
public class ChatController {


    @Autowired
    public ChatService chatService;


    @PostMapping("/getUserChat")
    public AjaxResult getUserChat(@RequestBody SysChat syschat)  {
        System.out.println("吊用接受"+syschat);
        List<SysChat> userChat = chatService.getUserChat(syschat);
        return AjaxResult.success("token生成成功",userChat);
    }
}
