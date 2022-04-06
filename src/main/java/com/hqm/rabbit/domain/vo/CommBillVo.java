package com.hqm.rabbit.domain.vo;

import com.hqm.rabbit.domain.entity.SysUser;

import java.util.Map;

public class CommBillVo {
    //模板编码
    public String mubanCode;
    //模板名称
    public String mubanName;
    //登录人信息
    public SysUser dengluUser;
    //单据信息
    public Map<String,Object> danju;

    public String getMubanCode() {
        return mubanCode;
    }

    public void setMubanCode(String mubanCode) {
        this.mubanCode = mubanCode;
    }

    public String getMubanName() {
        return mubanName;
    }

    public void setMubanName(String mubanName) {
        this.mubanName = mubanName;
    }

    public SysUser getDengluUser() {
        return dengluUser;
    }

    public void setDengluUser(SysUser dengluUser) {
        this.dengluUser = dengluUser;
    }

    public Map<String, Object> getDanju() {
        return danju;
    }

    public void setDanju(Map<String, Object> danju) {
        this.danju = danju;
    }

    @Override
    public String toString() {
        return "CommBillVo{" +
                "mubanCode='" + mubanCode + '\'' +
                ", mubanName='" + mubanName + '\'' +
                ", dengluUser=" + dengluUser +
                ", danju=" + danju +
                '}';
    }
}
