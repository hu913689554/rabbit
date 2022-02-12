package com.hqm.rabbit.service;
import com.hqm.rabbit.domain.vo.SysUser;
import com.hqm.rabbit.utils.security.JwtUtils;
import com.hqm.rabbit.mapper.LoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @作者 胡勤明
 * @时间 2022-01-10 16:11
 * @版本 1.0
 */
@Service
public class LoginService implements UserDetailsService {
    @Autowired
    private JwtUtils jwtutils;

    @Autowired
    public RedisTemplate redisTemplate;

    @Autowired
    LoginMapper SysLoginMapper;

    @Resource
    private AuthenticationManager authenticationManager;

    public String login(SysUser UserVo){
        //生成token
        String token=null;
        // 用户验证
        Authentication authentication = null;
        try
        {
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(UserVo.getUsername(), UserVo.getPassword()));
        }
        catch (Exception e)
        {
            throw new RuntimeException("账号或密码错误");

        }
        //SysUser loginUser = (SysUser) authentication.getPrincipal();
        return jwtutils.createToken(UserVo);
    }

    //根据账号查询账号信息
   @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       SysUser userVo = SysLoginMapper.selectLogininfo(username);
       List<Map<String, String>> list = SysLoginMapper.selectUserFun(username);
       Set<String> set=new HashSet();
       for(Map map:list){
           set.add(map.get("funcode").toString());
       }
       //maps.stream().map(Map::values).collect(Collectors.toList());
       System.out.println("权限：：：：：："+set);
       userVo.setPermissions(set);
       return userVo;
    }
}
