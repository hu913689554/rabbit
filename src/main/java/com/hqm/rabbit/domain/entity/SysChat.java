package com.hqm.rabbit.domain.entity;

import java.util.Date;

public class SysChat {

  private long id;
  private String cfrom;
  private String cto;
  private String ctext;
  private Date ddatetime;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getCfrom() {
    return cfrom;
  }

  public void setCfrom(String cfrom) {
    this.cfrom = cfrom;
  }

  public String getCto() {
    return cto;
  }

  public void setCto(String cto) {
    this.cto = cto;
  }

  public String getCtext() {
    return ctext;
  }

  public void setCtext(String ctext) {
    this.ctext = ctext;
  }

  public Date getDdatetime() {
    return ddatetime;
  }

  public void setDdatetime(Date ddatetime) {
    this.ddatetime = ddatetime;
  }

  @Override
  public String toString() {
    return "SysChat{" +
            "id=" + id +
            ", cfrom='" + cfrom + '\'' +
            ", cto='" + cto + '\'' +
            ", ctext='" + ctext + '\'' +
            ", ddatetime=" + ddatetime +
            '}';
  }
}
