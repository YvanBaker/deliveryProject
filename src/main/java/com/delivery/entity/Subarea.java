package com.delivery.entity;

public class Subarea {

  private Integer id;
  private String decidedzoneId;
  private Region region;
  private String addresskey;
  private String startnum;
  private String endnum;
  private String single;
  private String position;

  @Override
  public String toString() {
    return "Subarea{" +
            "id=" + id +
            ", decidedzoneId='" + decidedzoneId + '\'' +
            ", region=" + region +
            ", addresskey='" + addresskey + '\'' +
            ", startnum='" + startnum + '\'' +
            ", endnum='" + endnum + '\'' +
            ", single='" + single + '\'' +
            ", position='" + position + '\'' +
            '}';
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public String getDecidedzoneId() {
    return decidedzoneId;
  }

  public void setDecidedzoneId(String decidedzoneId) {
    this.decidedzoneId = decidedzoneId;
  }

  public Region getRegion() {
    return region;
  }

  public void setRegion(Region region) {
    this.region = region;
  }

  public String getAddresskey() {
    return addresskey;
  }

  public void setAddresskey(String addresskey) {
    this.addresskey = addresskey;
  }


  public String getStartnum() {
    return startnum;
  }

  public void setStartnum(String startnum) {
    this.startnum = startnum;
  }


  public String getEndnum() {
    return endnum;
  }

  public void setEndnum(String endnum) {
    this.endnum = endnum;
  }


  public String getSingle() {
    return single;
  }

  public void setSingle(String single) {
    this.single = single;
  }


  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

}
