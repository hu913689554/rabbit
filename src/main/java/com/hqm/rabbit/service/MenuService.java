package com.hqm.rabbit.service;

import com.hqm.rabbit.domain.entity.SysMuen;
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
        List<SysMuen> userMenuList = menumapper.getUserMenus(getUsername);
        //List<Map<String, Object>> userMenuList = menumapper.getUserMenu(getUsername);
        ArrayList Toplist = new ArrayList();
        LinkedList<Map<String, Object>> LinkedList = new LinkedList<Map<String, Object>>();
        LinkedHashMap<String, Map<String, Object>> LinkedHashMap = new LinkedHashMap<String, Map<String, Object>>();
        //构建菜单树
        for (SysMuen userMenuMap : userMenuList) {
            LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
            map.put("path", userMenuMap.getMenuCode());
            map.put("component", userMenuMap.getMenuPath()==null?"Layout":userMenuMap.getMenuPath());
            LinkedList childrenList = new LinkedList();


            LinkedHashMap<String, Object> metaMap = new LinkedHashMap<String, Object>();
            metaMap.put("title", userMenuMap.getMenuName());
            metaMap.put("icon", userMenuMap.getIcon());
            metaMap.put("affix", userMenuMap.getAffix());
            metaMap.put("hidden", userMenuMap.getNoCache());


            if(userMenuMap.getMenuParent() == 0){
                map.put("path", "/" + userMenuMap.getMenuTopCode());
                map.put("component", "Layout");
                LinkedList.add(map);
            }
            //是否末级，末级直接将菜单信息添加至自身
            if ( userMenuMap.getMenuParent() == 0 && userMenuMap.getMenuLast()) {
                LinkedHashMap<String, Object> childrenMap = new LinkedHashMap<String, Object>();
                childrenMap.put("path", userMenuMap.getMenuCode());
                childrenMap.put("name", userMenuMap.getMenuCode());
                childrenMap.put("component", userMenuMap.getMenuPath());
                childrenMap.put("meta", metaMap);
                childrenList.add(childrenMap);
                map.put("children", childrenList);
                //LinkedList.add(map);
            } else {
                map.put("meta", metaMap);
                map.put("children", childrenList);
            }
            LinkedHashMap.put((String) userMenuMap.getMenuCode(), map);
        }
        for (SysMuen userMenuMap : userMenuList) {
            if (userMenuMap.getMenuParent() != 0) {
                Map<String, Object> menu_top_code = LinkedHashMap.get((String) userMenuMap.getMenuTopCode());
                Map<String, Object> menu_code = LinkedHashMap.get((String) userMenuMap.getMenuCode());
                LinkedList children = (LinkedList) menu_top_code.get("children");
                children.add(menu_code);
            }
        }
//        for (String key: LinkedHashMap.keySet()) {
//            Map<String, Object> userMenuMap=LinkedHashMap.get(key);
//            if (userMenuMap.get("children") != null ) {
//                LinkedList children = (LinkedList) userMenuMap.get("children");
//                if(children==null||children.size()==0){
//                    userMenuMap.remove("children");
//                }
//            }
//        }
        return LinkedList;
    }
}

