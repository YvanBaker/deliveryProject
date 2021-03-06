package com.delivery.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * generated by Generate POJOs.groovy
 * @Date 2020-10-30 10:40
 *
 * @author Yvan
 */

/*@Data
@Entity
@Table ( name ="workor_dermanage" )
public class WorkorDermanage implements Serializable {

	private static final long serialVersionUID = 4998981176739501250L;

 	@Column(name = "id" )
	@Id
	@GeneratedValue
	private Integer id;

 	@Column(name = "workor_id" )
	private String workorId;

	*//**
	 * 到达地
	 *//*
 	@Column(name = "arrivecity" )
	private String arrivecity;

	*//**
	 * 产品
	 *//*
 	@Column(name = "product" )
	private String product;

	*//**
	 * 件数

	 *//*
 	@Column(name = "num" )
	private Integer num;

	*//**
	 * 重量
	 *//*
 	@Column(name = "weight" )
	private Double weight;

	*//**
	 * 配载要求
	 *//*
 	@Column(name = "floadreqr" )
	private String floadreqr;

	*//**
	 * 产品时限
	 *//*
 	@Column(name = "prodtimelimit" )
	private String prodtimelimit;

	*//**
	 * 产品类型
	 *//*
 	@Column(name = "prodtype" )
	private String prodtype;

	*//**
	 * 寄件人姓名
	 *//*
 	@Column(name = "sendername" )
	private String sendername;

	*//**
	 * 寄件人电话
	 *//*
 	@Column(name = "senderphone" )
	private String senderphone;

	*//**
	 * 寄件人地址
	 *//*
 	@Column(name = "senderaddr" )
	private String senderaddr;

	*//**
	 * 收件人姓名
	 *//*
 	@Column(name = "receivername" )
	private String receivername;

	*//**
	 * 收件人电话
	 *//*
 	@Column(name = "receiverphone" )
	private String receiverphone;

	*//**
	 * 收货人地址
	 *//*
 	@Column(name = "receiveraddr" )
	private String receiveraddr;

	*//**
	 * 计费件数
	 *//*
 	@Column(name = "feeitemnum" )
	private Integer feeitemnum;

	*//**
	 * 实际重量
	 *//*
 	@Column(name = "actlweit" )
	private Double actlweit;

	*//**
	 * 体积
	 *//*
 	@Column(name = "vol" )
	private String vol;

	*//**
	 * 是否审核配送
	 *//*
 	@Column(name = "managerCheck" )
	private String managerCheck;

	*//**
	 * 更新时间
	 *//*
 	@Column(name = "updatetime" )
	private Date updatetime;*/
public class WorkorDermanage {

  private Integer id;
  private String uuid;
  private String staff;
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


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }


  public String getStaff() {
    return staff;
  }

  public void setStaff(String staff) {
    this.staff = staff;
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
