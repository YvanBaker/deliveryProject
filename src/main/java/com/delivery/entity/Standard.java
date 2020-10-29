package com.delivery.entity;

/**
 * Created by LEO15 on 2020/10/21.
 *@author fujianian
 */
public class Standard {
    private int id;
    private String standardName;
    private double minWeight;
    private double maxWeight;
    private double minVolume;
    private double maxVolume;
    private int status;
    private String operator;
    private String oStation;
    private String oTime;

    public Standard() {
    }

    public Standard(String standardName, double minWeight, double maxWeight, int status) {
        this.standardName = standardName;
        this.minWeight = minWeight;
        this.maxWeight = maxWeight;
        this.status = status;
    }

    public Standard(int id, String standardName, double minWeight, double maxWeight, double minVolume, double maxVolume, int status, String operator, String oStation, String oTime) {
        this.id = id;
        this.standardName = standardName;
        this.minWeight = minWeight;
        this.maxWeight = maxWeight;
        this.minVolume = minVolume;
        this.maxVolume = maxVolume;
        this.status = status;
        this.operator = operator;
        this.oStation = oStation;
        this.oTime = oTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStandardName() {
        return standardName;
    }

    public void setStandardName(String standardName) {
        this.standardName = standardName;
    }

    public double getMinWeight() {
        return minWeight;
    }

    public void setMinWeight(double minWeight) {
        this.minWeight = minWeight;
    }

    public double getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(double maxWeight) {
        this.maxWeight = maxWeight;
    }

    public double getMinVolume() {
        return minVolume;
    }

    public void setMinVolume(double minVolume) {
        this.minVolume = minVolume;
    }

    public double getMaxVolume() {
        return maxVolume;
    }

    public void setMaxVolume(double maxVolume) {
        this.maxVolume = maxVolume;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOStation() {
        return oStation;
    }

    public void setoStation(String oStation) {
        this.oStation = oStation;
    }

    public String getOTime() {
        return oTime;
    }

    public void setoTime(String oTime) {
        this.oTime = oTime;
    }

    @Override
    public String toString() {
        return "Standard{" +
                "id=" + id +
                ", standardName='" + standardName + '\'' +
                ", minWeight=" + minWeight +
                ", maxWeight=" + maxWeight +
                ", minVolume=" + minVolume +
                ", maxVolume=" + maxVolume +
                ", status=" + status +
                ", operator='" + operator + '\'' +
                ", oStation='" + oStation + '\'' +
                ", oTime='" + oTime + '\'' +
                '}';
    }
}
