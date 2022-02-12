package com.hqm.rabbit.domain.vo;


public class SysDept {

  private String deptcode;
  private String deptname;
  private String parentId;
  private long orderNum;


  public String getDeptcode() {
    return deptcode;
  }

  public void setDeptcode(String deptcode) {
    this.deptcode = deptcode;
  }


  public String getDeptname() {
    return deptname;
  }

  public void setDeptname(String deptname) {
    this.deptname = deptname;
  }


  public String getParentId() {
    return parentId;
  }

  public void setParentId(String parentId) {
    this.parentId = parentId;
  }


  public long getOrderNum() {
    return orderNum;
  }

  public void setOrderNum(long orderNum) {
    this.orderNum = orderNum;
  }

}
