package com.delivery.entity;

public class BusinessNote {


  private Integer id;
  private String uuid;
  private String takeToSenderNum;
  private String clientNum;
  private String clientName;
  private String clientPhone;
  private String contacts;
  private String conPhone;
  private String address;
  private String arriveCity;
  private String product;
  private String proDate;
  private Integer number;
  private double weight;
  private String volume;
  private String remark;
  private String singleType;
  private String receiver;

  @Override
  public String toString() {
    return "BusinessNote{" +
            "id=" + id +
            ", uuid='" + uuid + '\'' +
            ", takeToSenderNum='" + takeToSenderNum + '\'' +
            ", clientNum='" + clientNum + '\'' +
            ", clientName='" + clientName + '\'' +
            ", clientPhone='" + clientPhone + '\'' +
            ", contacts='" + contacts + '\'' +
            ", conPhone='" + conPhone + '\'' +
            ", address='" + address + '\'' +
            ", arriveCity='" + arriveCity + '\'' +
            ", product='" + product + '\'' +
            ", proDate='" + proDate + '\'' +
            ", number=" + number +
            ", weight=" + weight +
            ", volume='" + volume + '\'' +
            ", remark='" + remark + '\'' +
            ", singleType='" + singleType + '\'' +
            ", receiver='" + receiver + '\'' +
            '}';
  }

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  public Integer getId() {
    return id;
  }

    private Integer id;
    private String uuid;

    private Staff staff;
    private String clientNum;
    private String clientName;
    private String clientPhone;
    private String contacts;
    private String conPhone;
    private String address;
    private String arriveCity;
    private String product;
    private Integer number;
    private double weight;
    private String volume;
    private String remark;
    private String singleType;
    private String receiver;
    private String buildTime;
    @Override
    public String toString() {
        return "BusinessNote{" +
                "id=" + id +
                "uuid=" + uuid +
                ", staff='" + staff + '\'' +
                ", clientNum='" + clientNum + '\'' +
                ", clientName='" + clientName + '\'' +
                ", clientPhone='" + clientPhone + '\'' +
                ", contacts='" + contacts + '\'' +
                ", conPhone='" + conPhone + '\'' +
                ", address='" + address + '\'' +
                ", arriveCity='" + arriveCity + '\'' +
                ", product='" + product + '\'' +
                ", number=" + number +
                ", weight=" + weight +
                ", volume='" + volume + '\'' +
                ", remark='" + remark + '\'' +
                ", singleType='" + singleType + '\'' +
                ", receiver='" + receiver + '\'' +
                '}';
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getBuildTime() {
        return buildTime;
    }

    public void setBuildTime(String buildTime) {
        this.buildTime = buildTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public String getClientNum() {
        return clientNum;
    }

    public void setClientNum(String clientNum) {
        this.clientNum = clientNum;
    }


    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }


    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }


    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }


    public String getConPhone() {
        return conPhone;
    }

    public void setConPhone(String conPhone) {
        this.conPhone = conPhone;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getArriveCity() {
        return arriveCity;
    }

    public void setArriveCity(String arriveCity) {
        this.arriveCity = arriveCity;
    }


    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }


    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }


    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }


    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }


    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    public String getSingleType() {
        return singleType;
    }

    public void setSingleType(String singleType) {
        this.singleType = singleType;
    }


    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

}
