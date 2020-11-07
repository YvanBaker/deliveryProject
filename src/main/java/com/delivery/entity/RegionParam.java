package com.delivery.entity;

/**
 * @author wenJ
 * @Description 地址参数
 * @Classname RegionParam
 * @Date 2020/11/7 22:52
 */
public class RegionParam {
    private String arriveCityArea;
    private String arriveCitySuffix;
    private String addressArea;
    private String addressSuffix;

    @Override
    public String toString() {
        return "RegionParam{" +
                "arriveCityArea='" + arriveCityArea + '\'' +
                ", arriveCitySuffix='" + arriveCitySuffix + '\'' +
                ", addressArea='" + addressArea + '\'' +
                ", addressSuffix='" + addressSuffix + '\'' +
                '}';
    }

    public String getArriveCityArea() {
        return arriveCityArea;
    }

    public void setArriveCityArea(String arriveCityArea) {
        this.arriveCityArea = arriveCityArea;
    }

    public String getArriveCitySuffix() {
        return arriveCitySuffix;
    }

    public void setArriveCitySuffix(String arriveCitySuffix) {
        this.arriveCitySuffix = arriveCitySuffix;
    }

    public String getAddressArea() {
        return addressArea;
    }

    public void setAddressArea(String addressArea) {
        this.addressArea = addressArea;
    }

    public String getAddressSuffix() {
        return addressSuffix;
    }

    public void setAddressSuffix(String addressSuffix) {
        this.addressSuffix = addressSuffix;
    }
}
