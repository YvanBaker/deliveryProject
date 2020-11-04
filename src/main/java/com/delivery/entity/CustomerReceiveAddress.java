package com.delivery.entity;

public class CustomerReceiveAddress {

  private Integer id;
  private Integer customerId;
  private String receiveName;
  private String receiveProvinceId;
  private String receiveCityId;
  private String receiveAreaId;
  private String receiveDetailedAddress;
  private Integer del;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public Integer getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Integer customerId) {
    this.customerId = customerId;
  }


  public String getReceiveName() {
    return receiveName;
  }

  public void setReceiveName(String receiveName) {
    this.receiveName = receiveName;
  }


  public String getReceiveProvinceId() {
    return receiveProvinceId;
  }

  public void setReceiveProvinceId(String receiveProvinceId) {
    this.receiveProvinceId = receiveProvinceId;
  }


  public String getReceiveCityId() {
    return receiveCityId;
  }

  public void setReceiveCityId(String receiveCityId) {
    this.receiveCityId = receiveCityId;
  }


  public String getReceiveAreaId() {
    return receiveAreaId;
  }

  public void setReceiveAreaId(String receiveAreaId) {
    this.receiveAreaId = receiveAreaId;
  }


  public String getReceiveDetailedAddress() {
    return receiveDetailedAddress;
  }

  public void setReceiveDetailedAddress(String receiveDetailedAddress) {
    this.receiveDetailedAddress = receiveDetailedAddress;
  }


  public Integer getDel() {
    return del;
  }

  public void setDel(Integer del) {
    this.del = del;
  }

}
