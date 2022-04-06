package com.hqm.rabbit.service;


import com.hqm.rabbit.domain.entity.SysMmGgTem;
import com.hqm.rabbit.domain.vo.CommBillVo;
import com.hqm.rabbit.domain.vo.SysUserVo;
import com.hqm.rabbit.mapper.CommMapper;
import com.hqm.rabbit.mapper.GetDatabaseMapper;
import com.hqm.rabbit.mapper.LoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;


/**
 * @作者 胡勤明
 * @时间 2022-01-10 16:11
 * @版本 1.0
 */
@Service
public class CommService {

    @Autowired
    CommMapper commMapper;


    public HashMap<String, Object> selectmubanlist(SysMmGgTem SysMmGgTem) {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();

        //获取模板头
        HashMap<String, Object> topmubanMap = commMapper.selectmubanToplist(SysMmGgTem);
        if (topmubanMap == null || topmubanMap.size() == 0) {
            return null;
        }
        hashMap.put("mubantop", topmubanMap);
        //储存特殊字段
        HashMap<String, Object> teshumap = new HashMap<String, Object>();
        //存储字段
        HashMap<String, Object> filedmap = new HashMap<String, Object>();
        //栏目过滤
        if ("档案".equals(topmubanMap.get("sleixing")) || "单据".equals(topmubanMap.get("sleixing"))) {
            List<HashMap<String, Object>> t = commMapper.selectmubanBodyFiledlist(SysMmGgTem, "T");
            //获取模板字段头
            List<HashMap<String, Object>> t1 =  commMapper.selectmubanBodyFiledlist(SysMmGgTem, "B");
            //获取模板字段体
            filedmap.put("heandfiled", listtoMap(t, "tFieldName"));
            filedmap.put("bodyfiled", listtoMap(t1, "tFieldName"));
            HashMap<String, HashMap<String, Object>> special = listtoMap(t1, "special");
            HashMap<String, HashMap<String, Object>> special1 = listtoMap(t, "special");
            teshumap.putAll(special);
            teshumap.putAll(special1);
        } else if ("列表".equals(topmubanMap.get("sleixing"))) {
            List<HashMap<String, Object>> t =  commMapper.selectmubanBodyFiledlist(SysMmGgTem, "B");
            filedmap.put("bodyfiled", t);
            HashMap<String, HashMap<String, Object>> special1 = listtoMap(t, "special");
            teshumap.putAll(special1);
        }
        hashMap.put("teshumap", teshumap);
        hashMap.put("mubanfiled", filedmap);
        //获取该模板其它栏目对象
//        //获取模板过滤头
//        HashMap<String, Object> stringObjectHashMap = commMapper.selectFilterToplist(SysMmGgTem);
//        //获取模板过滤体
//        HashMap<String, Object> stringObjectHashMap1 = commMapper.selectFilterBodylist(SysMmGgTem);
        return hashMap;
    }
    public static void getdatalist(SysMmGgTem SysMmGgTem){

    }


    public static HashMap<String,HashMap<String,Object>>  listtoMap(List<HashMap<String, Object>> list,String filed){
        HashMap<String,HashMap<String,Object>> hasmap=new HashMap<String,HashMap<String,Object>>();
        for(HashMap<String, Object> map:list){
            if(map.get(filed)!=null&&!"".equals(map.get(filed))){
                hasmap.put((String)map.get(filed),map);
            }
        }
        return hasmap;
    }


}

