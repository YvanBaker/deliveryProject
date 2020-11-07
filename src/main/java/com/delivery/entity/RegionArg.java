package com.delivery.entity;

/**
 * @author wenJ
 * @Description 地址
 * @Classname RegionArg
 * @Date 2020/11/7 21:00
 */
public class RegionArg {
    // 地区ID
    private String regionID;

    // 地区名称
    private String regionName;

    // 上级地区ID
    private String parentRegionID;

    @Override
    public String toString() {
        return "RegionArg{" +
                "regionID='" + regionID + '\'' +
                ", regionName='" + regionName + '\'' +
                ", parentRegionID='" + parentRegionID + '\'' +
                '}';
    }

    public String getRegionID() {
        return regionID;
    }

    public void setRegionID(String regionID) {
        this.regionID = regionID;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getParentRegionID() {
        return parentRegionID;
    }

    public void setParentRegionID(String parentRegionID) {
        this.parentRegionID = parentRegionID;
    }
/*
    private String povinces1;
    private String city1;
    private String area1;
    private String povinces2;
    private String city2;
    private String area2;

    @Override
    public String toString() {
        return "RegionArg{" +
                "regionID='" + regionID + '\'' +
                ", regionName='" + regionName + '\'' +
                ", parentRegionID='" + parentRegionID + '\'' +
                ", povinces1='" + povinces1 + '\'' +
                ", city1='" + city1 + '\'' +
                ", area1='" + area1 + '\'' +
                ", povinces2='" + povinces2 + '\'' +
                ", city2='" + city2 + '\'' +
                ", area2='" + area2 + '\'' +
                '}';
    }

    public String getRegionID() {
        return regionID;
    }

    public void setRegionID(String regionID) {
        this.regionID = regionID;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getParentRegionID() {
        return parentRegionID;
    }

    public void setParentRegionID(String parentRegionID) {
        this.parentRegionID = parentRegionID;
    }

    public String getPovinces1() {
        return povinces1;
    }

    public void setPovinces1(String povinces1) {
        this.povinces1 = povinces1;
    }

    public String getCity1() {
        return city1;
    }

    public void setCity1(String city1) {
        this.city1 = city1;
    }

    public String getArea1() {
        return area1;
    }

    public void setArea1(String area1) {
        this.area1 = area1;
    }

    public String getPovinces2() {
        return povinces2;
    }

    public void setPovinces2(String povinces2) {
        this.povinces2 = povinces2;
    }

    public String getCity2() {
        return city2;
    }

    public void setCity2(String city2) {
        this.city2 = city2;
    }

    public String getArea2() {
        return area2;
    }

    public void setArea2(String area2) {
        this.area2 = area2;
    }*/
}
