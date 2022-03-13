package com.hqm.rabbit.service;

import com.hqm.rabbit.domain.entity.SysChat;
import com.hqm.rabbit.domain.vo.SysUserVo;
import com.hqm.rabbit.mapper.ChatMapper;
import com.hqm.rabbit.mapper.LoginMapper;
import com.hqm.rabbit.utils.error.MsgException;
import com.hqm.rabbit.utils.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @作者 胡勤明
 * @时间 2022-01-10 16:11
 * @版本 1.0
 */
@Service
public class ChatService  {



    @Autowired
    ChatMapper chatmapper;

    @Resource
    private AuthenticationManager authenticationManager;

    public List<SysChat> getUserChat(SysChat syschat){
        List<SysChat> sysChats = chatmapper.selectChat(syschat);
        return sysChats;
    }

}
