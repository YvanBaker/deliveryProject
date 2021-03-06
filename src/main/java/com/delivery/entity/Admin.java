package com.delivery.entity;

public class Admin {

  private Integer id;
  private String userName;
  private String password;
  private String adminName;
  private String stations;

  @Override
  public String toString() {
    return "Admin{" +
            "id=" + id +
            ", userName='" + userName + '\'' +
            ", password='" + password + '\'' +
            ", adminName='" + adminName + '\'' +
            ", stations='" + stations + '\'' +
            '}';
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  public String getAdminName() {
    return adminName;
  }

  public void setAdminName(String adminName) {
    this.adminName = adminName;
  }

  public String getStations() {
    return stations;
  }
  public void setStations(String stations) {
    this.stations = stations;
  }
}
