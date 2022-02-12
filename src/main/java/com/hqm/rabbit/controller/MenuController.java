package com.hqm.rabbit.controller;

import com.hqm.rabbit.domain.vo.SysMuen;
import com.hqm.rabbit.utils.responsemsg.AjaxResult;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.awt.*;

/**
 * @作者 胡勤明
 * @时间 2022-01-05 16:31
 * @版本 1.0
 * @作用 登陆测试
 */

@RestController
public class MenuController {
    /**
     * 创建人 胡勤明
     * 创建时间 2022/1/10 15:44
     * 方法作用 登陆验证并验证存储信息
     */
    @PostMapping("/menuLogin")
    @PreAuthorize("@ss.hasPermi(\"p1\")")
    public AjaxResult menu() {
        System.out.println("菜单查询完成");
        return new AjaxResult(1, "菜单查询完成");
    }


    @RequestMapping(value = "article", method = RequestMethod.GET)
    public AjaxResult getArticleInfo() {
        return new AjaxResult(1, "菜单查询完成");
    }
    /**
     * 创建人 胡勤明
     * 创建时间 2022/1/10 15:44
     * 方法作用 增加菜单
     */
    @PostMapping("/insertmenu")
    //@PreAuthorize("@ss.hasPermi(\"p12\")")
    public AjaxResult insertmenu(SysMuen sysMuen) {
        System.out.println("sysMuen:" + sysMuen);
        return new AjaxResult(1, "菜单查询完成");
    }

}
