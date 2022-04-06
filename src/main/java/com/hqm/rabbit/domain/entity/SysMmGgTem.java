package com.hqm.rabbit.domain.entity;


public class SysMmGgTem {

  public String sTemCode;
  public String sTemName;
  public long sCol;
  public String kzbdCode;
  public String sleixing;
  public String viewTable;
  public String linkTemCode;
  public String tabhead;
  public String tabbody;
  public String viewtabhead;
  public String viewtabbody;
  public String viewtablist;


  public String getSTemCode() {
    return sTemCode;
  }

  public void setSTemCode(String sTemCode) {
    this.sTemCode = sTemCode;
  }


  public String getSTemName() {
    return sTemName;
  }

  public void setSTemName(String sTemName) {
    this.sTemName = sTemName;
  }


  public long getSCol() {
    return sCol;
  }

  public void setSCol(long sCol) {
    this.sCol = sCol;
  }


  public String getKzbdCode() {
    return kzbdCode;
  }

  public void setKzbdCode(String kzbdCode) {
    this.kzbdCode = kzbdCode;
  }


  public String getSleixing() {
    return sleixing;
  }

  public void setSleixing(String sleixing) {
    this.sleixing = sleixing;
  }


  public String getViewTable() {
    return viewTable;
  }

  public void setViewTable(String viewTable) {
    this.viewTable = viewTable;
  }


  public String getLinkTemCode() {
    return linkTemCode;
  }

  public void setLinkTemCode(String linkTemCode) {
    this.linkTemCode = linkTemCode;
  }


  public String getTabhead() {
    return tabhead;
  }

  public void setTabhead(String tabhead) {
    this.tabhead = tabhead;
  }


  public String getTabbody() {
    return tabbody;
  }

  public void setTabbody(String tabbody) {
    this.tabbody = tabbody;
  }


  public String getViewtabhead() {
    return viewtabhead;
  }

  public void setViewtabhead(String viewtabhead) {
    this.viewtabhead = viewtabhead;
  }


  public String getViewtabbody() {
    return viewtabbody;
  }

  public void setViewtabbody(String viewtabbody) {
    this.viewtabbody = viewtabbody;
  }


  public String getViewtablist() {
    return viewtablist;
  }

  public void setViewtablist(String viewtablist) {
    this.viewtablist = viewtablist;
  }

  @Override
  public String toString() {
    return "SysMmGgTem{" +
            "sTemCode='" + sTemCode + '\'' +
            ", sTemName='" + sTemName + '\'' +
            ", sCol=" + sCol +
            ", kzbdCode='" + kzbdCode + '\'' +
            ", sleixing='" + sleixing + '\'' +
            ", viewTable='" + viewTable + '\'' +
            ", linkTemCode='" + linkTemCode + '\'' +
            ", tabhead='" + tabhead + '\'' +
            ", tabbody='" + tabbody + '\'' +
            ", viewtabhead='" + viewtabhead + '\'' +
            ", viewtabbody='" + viewtabbody + '\'' +
            ", viewtablist='" + viewtablist + '\'' +
            '}';
  }
}
