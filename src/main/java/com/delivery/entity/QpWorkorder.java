package com.delivery.entity;

import java.sql.Date;

public class QpWorkorder {

  private Integer id;
  private String uuid;
  private String arrivecity;
  private String product;
  private Integer num;
  private double weight;
  private String floadreqr;
  private String prodtimelimit;
  private String prodtype;
  private String sendername;
  private String senderphone;
  private String senderaddr;
  private String receivername;
  private String receiverphone;
  private String receiveraddr;
  private Integer feeitemnum;
  private double actlweit;
  private String vol;
  private String managerCheck;
  private java.sql.Date updatetime;

  public QpWorkorder() {
  }

  public QpWorkorder(Integer id, String uuid, String workorId, String arrivecity, String product, Integer num, double weight, String floadreqr, String prodtimelimit, String prodtype, String sendername, String senderphone, String senderaddr, String receivername, String receiverphone, String receiveraddr, Integer feeitemnum, double actlweit, String vol, String managerCheck, Date updatetime) {
    this.id = id;
    this.uuid = uuid;
    this.workorId = workorId;
    this.arrivecity = arrivecity;
    this.product = product;
    this.num = num;
    this.weight = weight;
    this.floadreqr = floadreqr;
    this.prodtimelimit = prodtimelimit;
    this.prodtype = prodtype;
    this.sendername = sendername;
    this.senderphone = senderphone;
    this.senderaddr = senderaddr;
    this.receivername = receivername;
    this.receiverphone = receiverphone;
    this.receiveraddr = receiveraddr;
    this.feeitemnum = feeitemnum;
    this.actlweit = actlweit;
    this.vol = vol;
    this.managerCheck = managerCheck;
    this.updatetime = updatetime;
  }

  @Override
  public String toString() {
    return "QpWorkorder{" +
            "id=" + id +
            ", uuid='" + uuid + '\'' +
            ", workorId='" + workorId + '\'' +\
            ", arrivecity='" + arrivecity + '\'' +
            ", product='" + product + '\'' +
            ", num=" + num +
            ", weight=" + weight +
            ", floadreqr='" + floadreqr + '\'' +
            ", prodtimelimit='" + prodtimelimit + '\'' +
            ", prodtype='" + prodtype + '\'' +
            ", sendername='" + sendername + '\'' +
            ", senderphone='" + senderphone + '\'' +
            ", senderaddr='" + senderaddr + '\'' +
            ", receivername='" + receivername + '\'' +
            ", receiverphone='" + receiverphone + '\'' +
            ", receiveraddr='" + receiveraddr + '\'' +
            ", feeitemnum=" + feeitemnum +
            ", actlweit=" + actlweit +
            ", vol='" + vol + '\'' +
            ", managerCheck='" + managerCheck + '\'' +
            ", updatetime=" + updatetime +
            '}';
  }

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }


  public void setId(Integer id) {
    this.id = id;
  }
  public Integer getId() {
    return id;
  }

  public String getWorkorId() {
    return workorId;
  }

  public void setWorkorId(String workorId) {
    this.workorId = workorId;
  }
  
  public String getArrivecity() {
    return arrivecity;
  }

  public void setArrivecity(String arrivecity) {
    this.arrivecity = arrivecity;
  }


  public String getProduct() {
    return product;
  }

  public void setProduct(String product) {
    this.product = product;
  }


  public Integer getNum() {
    return num;
  }

  public void setNum(Integer num) {
    this.num = num;
  }


  public double getWeight() {
    return weight;
  }

  public void setWeight(double weight) {
    this.weight = weight;
  }


  public String getFloadreqr() {
    return floadreqr;
  }

  public void setFloadreqr(String floadreqr) {
    this.floadreqr = floadreqr;
  }


  public String getProdtimelimit() {
    return prodtimelimit;
  }

  public void setProdtimelimit(String prodtimelimit) {
    this.prodtimelimit = prodtimelimit;
  }


  public String getProdtype() {
    return prodtype;
  }

  public void setProdtype(String prodtype) {
    this.prodtype = prodtype;
  }


  public String getSendername() {
    return sendername;
  }

  public void setSendername(String sendername) {
    this.sendername = sendername;
  }


  public String getSenderphone() {
    return senderphone;
  }

  public void setSenderphone(String senderphone) {
    this.senderphone = senderphone;
  }


  public String getSenderaddr() {
    return senderaddr;
  }

  public void setSenderaddr(String senderaddr) {
    this.senderaddr = senderaddr;
  }


  public String getReceivername() {
    return receivername;
  }

  public void setReceivername(String receivername) {
    this.receivername = receivername;
  }


  public String getReceiverphone() {
    return receiverphone;
  }

  public void setReceiverphone(String receiverphone) {
    this.receiverphone = receiverphone;
  }


  public String getReceiveraddr() {
    return receiveraddr;
  }

  public void setReceiveraddr(String receiveraddr) {
    this.receiveraddr = receiveraddr;
  }


  public Integer getFeeitemnum() {
    return feeitemnum;
  }

  public void setFeeitemnum(Integer feeitemnum) {
    this.feeitemnum = feeitemnum;
  }


  public double getActlweit() {
    return actlweit;
  }

  public void setActlweit(double actlweit) {
    this.actlweit = actlweit;
  }


  public String getVol() {
    return vol;
  }

  public void setVol(String vol) {
    this.vol = vol;
  }


  public String getManagerCheck() {
    return managerCheck;
  }

  public void setManagerCheck(String managerCheck) {
    this.managerCheck = managerCheck;
  }


  public java.sql.Date getUpdatetime() {
    return updatetime;
  }

  public void setUpdatetime(java.sql.Date updatetime) {
    this.updatetime = updatetime;
  }

}
