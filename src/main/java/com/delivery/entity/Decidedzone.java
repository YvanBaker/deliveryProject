package com.delivery.entity;

public class Decidedzone {

  private Integer id;
  private String decidedName;
  private Staff staff;
  private Region region;

  @Override
  public String toString() {
    return "Decidedzone{" +
            "id=" + id +
            ", decidedName='" + decidedName + '\'' +
            ", staff=" + staff +
            ", region=" + region +
            '}';
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getDecidedName() {
    return decidedName;
  }

  public void setDecidedName(String decidedName) {
    this.decidedName = decidedName;
  }

  public Staff getStaff() {
    return staff;
  }

  public void setStaff(Staff staff) {
    this.staff = staff;
  }

  public Region getRegion() {
    return region;
  }

  public void setRegion(Region region) {
    this.region = region;
  }
}
