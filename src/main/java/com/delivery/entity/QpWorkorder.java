package com.delivery.entity;

public class QpWorkorder {



  private Integer id;
  private String workorId;
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

  @Override
  public String toString() {
    return "QpWorkorder{" +
            "id=" + id +
            ", workorId='" + workorId + '\'' +
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
