package com.hqm.rabbit.controller;

import com.hqm.rabbit.domain.entity.SysMmGgTem;
import com.hqm.rabbit.domain.vo.CommBillVo;
import com.hqm.rabbit.service.CommService;
import com.hqm.rabbit.utils.responsemsg.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/comm")
public class CommController {

    @Autowired
    public CommService CommService;
     /*
     * 模板格式查询
     */
     @PostMapping("/selectmubanlist")
     public AjaxResult selectmubanlist(@RequestBody SysMmGgTem SysMmGgTem){
         System.out.println(SysMmGgTem);
         HashMap<String, Object> selectmubanlist = CommService.selectmubanlist(SysMmGgTem);
         System.out.println(selectmubanlist);
         return AjaxResult.success("返回成功",selectmubanlist);
     }
    /*
        档案全查
    */
    public AjaxResult getdatalist(@RequestBody SysMmGgTem SysMmGgTem) {
        //档案编码 //模板编码 //查询用户 //
        return null;
    }

}
