package com.hqm.rabbit.service;
import com.hqm.rabbit.domain.vo.SysUserVo;
import com.hqm.rabbit.utils.error.MsgException;
import com.hqm.rabbit.utils.security.JwtUtils;
import com.hqm.rabbit.mapper.LoginMapper;
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
import java.util.*;

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

    public String login(SysUserVo UserVo){
        System.out.println("-------------------");
        System.out.println(UserVo);
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
        catch (RuntimeException e)
        {
            throw new MsgException("账号或密码错误");
        }
        SysUserVo loginUser = (SysUserVo) authentication.getPrincipal();
        System.out.println("登录返回"+loginUser);
        return jwtutils.createToken(loginUser);
    }

    //根据账号查询账号信息
   @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       System.out.println("账号查询验证");
       System.out.println(username);
       SysUserVo userVo = SysLoginMapper.selectLogininfo(username);
       System.out.println(userVo);
       List<Map<String, String>> listFun = SysLoginMapper.selectUserFun(username);
       List<Map<String, String>> listRole = SysLoginMapper.selectUserRole(username);
       Set<String> setFuns=new HashSet();
       for(Map map:listFun){
           setFuns.add(map.get("funcode").toString());
       }
       Set<String> setRoles=new HashSet();
       for(Map map:listRole){
           setRoles.add(map.get("rolecode").toString());
       }
       //maps.stream().map(Map::values).collect(Collectors.toList());
       System.out.println("权限：：：：：："+setFuns);
       System.out.println("权限：：：：：："+setRoles);
       userVo.setPermissions(setFuns);
       userVo.setRoles(setRoles);
       return userVo;
    }
}
