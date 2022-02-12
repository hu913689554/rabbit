package com.hqm.rabbit.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
@Mapper
public interface GetDatabaseMapper {
    public List<HashMap<String,String>> gettable(String database);
    public List<HashMap<String,String>> getcolumn(String database,String table);
    //开发测试用来执行sql
    public List<HashMap<String,String>> select(String sql);
}
