package com.delivery.entity;

public class WorkBill {

  private String id;
  private String noticebillId;
  private String type;
  private String pickstate;
  private java.sql.Timestamp buildtime;
  private Integer attachbilltimes;
  private String remark;
  private String staffId;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getNoticebillId() {
    return noticebillId;
  }

  public void setNoticebillId(String noticebillId) {
    this.noticebillId = noticebillId;
  }


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }


  public String getPickstate() {
    return pickstate;
  }

  public void setPickstate(String pickstate) {
    this.pickstate = pickstate;
  }


  public java.sql.Timestamp getBuildtime() {
    return buildtime;
  }

  public void setBuildtime(java.sql.Timestamp buildtime) {
    this.buildtime = buildtime;
  }


  public Integer getAttachbilltimes() {
    return attachbilltimes;
  }

  public void setAttachbilltimes(Integer attachbilltimes) {
    this.attachbilltimes = attachbilltimes;
  }


  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }


  public String getStaffId() {
    return staffId;
  }

  public void setStaffId(String staffId) {
    this.staffId = staffId;
  }

}
