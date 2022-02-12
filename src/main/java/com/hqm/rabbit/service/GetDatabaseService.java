package com.hqm.rabbit.service;


import com.hqm.rabbit.domain.vo.SysUser;
import com.hqm.rabbit.mapper.GetDatabaseMapper;
import com.hqm.rabbit.mapper.LoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;


/**
 * @作者 胡勤明
 * @时间 2022-01-10 16:11
 * @版本 1.0
 */
@Service
public class GetDatabaseService {

    @Autowired
    GetDatabaseMapper systemMapper;

    @Autowired
    LoginMapper SysLoginMapper;

    public  List<HashMap<String, String>> gettable(String database) {
        List<HashMap<String, String>> gettable = systemMapper.gettable(database);
        return gettable;
    }
    public  List<HashMap<String, String>> getcolumn(String database,String table) {
        List<HashMap<String, String>> getcolumn = systemMapper.getcolumn(database,table);
        return getcolumn;
    }
    public List<SysUser> getUser(){
        return SysLoginMapper.selectUserVo();
    }
    public List<HashMap<String, String>> select(String sql){
        List<HashMap<String, String>> select = systemMapper.select(sql);
        return select;
    }


}

