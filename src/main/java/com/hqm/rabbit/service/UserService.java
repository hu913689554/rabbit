package com.hqm.rabbit.service;

import com.hqm.rabbit.domain.vo.SysUserVo;
import com.hqm.rabbit.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @作者 胡勤明
 * @时间 2022-01-10 16:11
 * @版本 1.0
 */
@Service
public class UserService {

    @Autowired
    UserMapper UserMapper;


    public List<SysUserVo> selectUserVo(){
        List<SysUserVo> sysUsers = UserMapper.selectUserVo();
        return sysUsers;
    }

    public int insertUser(List<SysUserVo> sysUserList){
        int i = UserMapper.inserUser(sysUserList);
        return i;
    }

}
