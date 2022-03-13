package com.hqm.rabbit.domain.entity;

public class SysMuen {

  private String menuCode;
  private String menuName;
  private String menuPath;
  private long menuParent;
  private String menuTopCode;
  private long menuOrder;
  private Boolean menuLast;
  private String icon;
  private Boolean noCache;
  private Boolean affix;
  private Boolean hidden;
  private Boolean menuEnable;
  private String menuFun;
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


  public Boolean getMenuLast() {
    return menuLast;
  }

  public void setMenuLast(Boolean menuLast) {
    this.menuLast = menuLast;
  }


  public String getIcon() {
    return icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }


  public Boolean getNoCache() {
    return noCache;
  }

  public void setNoCache(Boolean noCache) {
    this.noCache = noCache;
  }


  public Boolean getAffix() {
    return affix;
  }

  public void setAffix(Boolean affix) {
    this.affix = affix;
  }


  public Boolean getHidden() {
    return hidden;
  }

  public void setHidden(Boolean hidden) {
    this.hidden = hidden;
  }


  public Boolean getMenuEnable() {
    return menuEnable;
  }

  public void setMenuEnable(Boolean menuEnable) {
    this.menuEnable = menuEnable;
  }


  public String getMenuFun() {
    return menuFun;
  }

  public void setMenuFun(String menuFun) {
    this.menuFun = menuFun;
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

  @Override
  public String toString() {
    return "SysMuen{" +
            "menuCode='" + menuCode + '\'' +
            ", menuName='" + menuName + '\'' +
            ", menuPath='" + menuPath + '\'' +
            ", menuParent=" + menuParent +
            ", menuTopCode='" + menuTopCode + '\'' +
            ", menuOrder=" + menuOrder +
            ", menuLast=" + menuLast +
            ", icon='" + icon + '\'' +
            ", noCache=" + noCache +
            ", affix=" + affix +
            ", hidden=" + hidden +
            ", menuEnable=" + menuEnable +
            ", menuFun='" + menuFun + '\'' +
            ", createName='" + createName + '\'' +
            ", createTime=" + createTime +
            ", updateName='" + updateName + '\'' +
            ", updateTime=" + updateTime +
            '}';
  }
}
