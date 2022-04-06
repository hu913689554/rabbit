package com.hqm.rabbit.domain.entity;


public class SysMmGgFilterfield {

  public long id;
  public String filterCode;
  public String sTemCode;
  public String tFieldName;
  public String wherename;
  public String symbol;
  public long isWhere;
  public long isbishu;
  public long whereOrder;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getFilterCode() {
    return filterCode;
  }

  public void setFilterCode(String filterCode) {
    this.filterCode = filterCode;
  }


  public String getSTemCode() {
    return sTemCode;
  }

  public void setSTemCode(String sTemCode) {
    this.sTemCode = sTemCode;
  }


  public String getTFieldName() {
    return tFieldName;
  }

  public void setTFieldName(String tFieldName) {
    this.tFieldName = tFieldName;
  }


  public String getWherename() {
    return wherename;
  }

  public void setWherename(String wherename) {
    this.wherename = wherename;
  }


  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }


  public long getIsWhere() {
    return isWhere;
  }

  public void setIsWhere(long isWhere) {
    this.isWhere = isWhere;
  }


  public long getIsbishu() {
    return isbishu;
  }

  public void setIsbishu(long isbishu) {
    this.isbishu = isbishu;
  }


  public long getWhereOrder() {
    return whereOrder;
  }

  public void setWhereOrder(long whereOrder) {
    this.whereOrder = whereOrder;
  }

}
