package com.hqm.rabbit.controller;

import com.hqm.rabbit.domain.vo.SysUserVo;
import com.hqm.rabbit.service.GetDatabaseService;
import com.hqm.rabbit.utils.common.annotation.Excel;
import com.hqm.rabbit.utils.common.utils.poi.ExcelUtil;

import com.hqm.rabbit.utils.responsemsg.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * @作者 胡勤明
 * @时间 2022-01-05 16:31
 * @版本 1.0
 * @作用 登陆测试
 */

@RestController
@RequestMapping("/system")
public class   GetDatabaseController {
    @Autowired
   public GetDatabaseService GetDatabaseService;

    @Value("${tuzi.database}")
    private String systemdatabase;
    /**
     * 创建人 胡勤明
     * 创建时间 2022/1/10 15:44
     * 方法作用 查询所有的表结构
     */
    @GetMapping("/gettable")
    //@PreAuthorize("@ss.hasPermi(\"admin\")")
    public AjaxResult gettable(String database) {
        if(database==null||"".equals(database)){
            database=systemdatabase;
        }

        System.out.println(systemdatabase);
        List<HashMap<String, String>> gettabledata = GetDatabaseService.gettable(database);
        return new AjaxResult(1, "查询成功", gettabledata);
    }
    /**
     * 创建人 胡勤明
     * 创建时间 2022/1/10 15:44
     * 方法作用 查询所有列
     */
    @GetMapping("/getcolumn")
    //@PreAuthorize("@ss.hasPermi(\"admin\")")
    public AjaxResult getcolumn(String database,String table) {

        if(database==null||"".equals(database)){
            database=systemdatabase;
        }
        List<HashMap<String, String>> getcolumn = GetDatabaseService.getcolumn(database,table);
        System.out.println(getcolumn);

        return new AjaxResult(1, "查询成功",getcolumn);
    }
    @GetMapping("/getUser")
    //@PreAuthorize("@ss.hasPermi(\"admin\")")
    public AjaxResult getUser() {
        List<SysUserVo> user = GetDatabaseService.getUser();
        ExcelUtil excelUtil = new ExcelUtil(SysUserVo.class);
        excelUtil.init(user,"测试页","测试标题", Excel.Type.EXPORT);
        final com.hqm.rabbit.utils.common.core.domain.AjaxResult ajaxResult = excelUtil.exportExcel();
        System.out.println(ajaxResult);
        return new AjaxResult(1, "查询成功",user);
    }
}
