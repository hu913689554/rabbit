package com.hqm.rabbit.domain.entity;


import com.sun.org.apache.xpath.internal.operations.Bool;

public class SysUser {

  private long id;
  private String name;
  private String username;
  private String password;
  private String introduction;
  private String avatar;
  private Boolean isenabled;
  private String createuser;
  private String createusername;
  private String updateuser;
  private String updateusername;
  private java.sql.Timestamp createdate;
  private java.sql.Timestamp updatedate;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  public String getIntroduction() {
    return introduction;
  }

  public void setIntroduction(String introduction) {
    this.introduction = introduction;
  }


  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }


  public Boolean getisEnabled() {
    return isenabled;
  }

  public void setEnabled(Boolean isenabled) {
    this.isenabled = isenabled;
  }


  public String getCreateuser() {
    return createuser;
  }

  public void setCreateuser(String createuser) {
    this.createuser = createuser;
  }


  public String getCreateusername() {
    return createusername;
  }

  public void setCreateusername(String createusername) {
    this.createusername = createusername;
  }


  public String getUpdateuser() {
    return updateuser;
  }

  public void setUpdateuser(String updateuser) {
    this.updateuser = updateuser;
  }


  public String getUpdateusername() {
    return updateusername;
  }

  public void setUpdateusername(String updateusername) {
    this.updateusername = updateusername;
  }


  public java.sql.Timestamp getCreatedate() {
    return createdate;
  }

  public void setCreatedate(java.sql.Timestamp createdate) {
    this.createdate = createdate;
  }


  public java.sql.Timestamp getUpdatedate() {
    return updatedate;
  }

  public void setUpdatedate(java.sql.Timestamp updatedate) {
    this.updatedate = updatedate;
  }

}
