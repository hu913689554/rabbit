package com.hqm.rabbit.domain.vo;


public class SysMuen {

  private String menuCode;
  private String menuName;
  private String menuPath;
  private long menuParent;
  private String menuTopCode;
  private long menuOrder;
  private String menuLast;
  private String menuEnable;
  private String menuRole;
  private String createName;
  private java.sql.Timestamp createTime;
  private String updateName;
  private java.sql.Timestamp updateTime;


  public String getMenuCode() {
    return menuCode;
  }

  public void setMenuCode(String menuCode) {
    this.menuCode = menuCode;
  }


  public String getMenuName() {
    return menuName;
  }

  public void setMenuName(String menuName) {
    this.menuName = menuName;
  }


  public String getMenuPath() {
    return menuPath;
  }

  public void setMenuPath(String menuPath) {
    this.menuPath = menuPath;
  }


  public long getMenuParent() {
    return menuParent;
  }

  public void setMenuParent(long menuParent) {
    this.menuParent = menuParent;
  }


  public String getMenuTopCode() {
    return menuTopCode;
  }

  public void setMenuTopCode(String menuTopCode) {
    this.menuTopCode = menuTopCode;
  }


  public long getMenuOrder() {
    return menuOrder;
  }

  public void setMenuOrder(long menuOrder) {
    this.menuOrder = menuOrder;
  }


  public String getMenuLast() {
    return menuLast;
  }

  public void setMenuLast(String menuLast) {
    this.menuLast = menuLast;
  }


  public String getMenuEnable() {
    return menuEnable;
  }

  public void setMenuEnable(String menuEnable) {
    this.menuEnable = menuEnable;
  }


  public String getMenuRole() {
    return menuRole;
  }

  public void setMenuRole(String menuRole) {
    this.menuRole = menuRole;
  }


  public String getCreateName() {
    return createName;
  }

  public void setCreateName(String createName) {
    this.createName = createName;
  }


  public java.sql.Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.sql.Timestamp createTime) {
    this.createTime = createTime;
  }


  public String getUpdateName() {
    return updateName;
  }

  public void setUpdateName(String updateName) {
    this.updateName = updateName;
  }


  public java.sql.Timestamp getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(java.sql.Timestamp updateTime) {
    this.updateTime = updateTime;
  }

}
