package com.hqm.rabbit.utils.security;

import com.hqm.rabbit.domain.vo.SysUserVo;
import com.hqm.rabbit.utils.error.MsgException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @作者 胡勤明
 * @时间 2022-01-13 14:35
 * @版本 1.0
 */
@Service("ss")
public class PermissionService {

    public Boolean hasPermi (String permission) throws MsgException {
        System.out.println("开始认证");
        if(permission==null||"".equals(permission)){
            return true;
        }

        SysUserVo Uservo = (SysUserVo)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if("admin".equals(Uservo.getUsername())){
            return true;
        }
        Set<String> permissions = Uservo.getPermissions();
        System.out.println("认证获取的结果"+permissions);
        if(permissions.contains(permission)){
            return true;
        }else{
           // throw  new MsgException("权限验证失败！");
           return false;
        }

    }
}
