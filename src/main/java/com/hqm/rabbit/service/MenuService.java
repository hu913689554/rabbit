package com.hqm.rabbit.service;

import com.hqm.rabbit.domain.vo.SysUserVo;
import com.hqm.rabbit.mapper.LoginMapper;
import com.hqm.rabbit.mapper.MenuMapper;
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
import java.util.*;

/**
 * @作者 胡勤明
 * @时间 2022-03-10 10:41
 * @版本 1.0
 */
@Service
public class MenuService {

    @Autowired
    MenuMapper menumapper;

    public List<Map<String, Object>> getUserMenu(String getUsername) {
        List<Map<String, Object>> userMenuList = menumapper.getUserMenu(getUsername);
        ArrayList Toplist = new ArrayList();
        LinkedList< Map<String, Object>> LinkedList = new LinkedList<Map<String, Object>>();
        LinkedHashMap<String,Map<String, Object>> LinkedHashMap = new LinkedHashMap<String,Map<String, Object>>();
        //构建菜单树
        for (Map<String, Object> userMenuMap : userMenuList) {
            LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
            map.put("path", "/"+userMenuMap.get("menu_top_code"));
            map.put("component", "Layout");
            LinkedList childrenList = new LinkedList();
            LinkedHashMap<String, Object> metaMap= new LinkedHashMap<String, Object>();
            metaMap.put("title", userMenuMap.get("menu_name"));
            metaMap.put("icon", userMenuMap.get("icon"));
            metaMap.put("affix", true);
            //是否末级，末级直接将菜单信息添加至自身
            if((Boolean)userMenuMap.get("menu_last")){
                LinkedHashMap<String, Object> childrenMap = new LinkedHashMap<String, Object>();
                childrenMap.put("path", userMenuMap.get("menu_code"));
                childrenMap.put("component", userMenuMap.get("menu_path"));
                childrenMap.put("name", "Documentation");//userMenuMap.get("menu_name")
                childrenMap.put("meta", metaMap);
                childrenList.add(childrenMap);
                map.put("children", childrenList);
                //LinkedList.add(map);
            }else{
                map.put("meta",metaMap);
                map.put("children",childrenList);
            }
            LinkedHashMap.put((String)userMenuMap.get("menu_code"),map);
            if((Integer)userMenuMap.get("menu_parent")==0){
                LinkedList.add(map);
            }
        }
        for (Map<String, Object> userMenuMap : userMenuList) {
            if((Integer)userMenuMap.get("menu_parent")!=0){
                Map<String, Object> menu_top_code = LinkedHashMap.get((String) userMenuMap.get("menu_top_code"));
                Map<String, Object> menu_code = LinkedHashMap.get((String) userMenuMap.get("menu_code"));
                LinkedList children = (LinkedList)menu_top_code.get("children");
                children.add(menu_code);
            }
        }
        return LinkedList;
    }
}

