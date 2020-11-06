package com.delivery.entity;

/**
 * Created by LEO15 on 2020/10/22.
 */
public class Line {
    private int id;
    private String lineType;
    private String lineName;
    private String licensePlate;
    private String carModel;
    private String driver;
    private int status;
    private double weightControl;
    private String operator;
    private String oStation;
    private String oTime;

    public Line() {
    }

    public Line(int id, String lineType, String lineName, String licensePlate, String carModel, String driver, int status, double weightControl, String operator, String oStation, String oTime) {
        this.id = id;
        this.lineType = lineType;
        this.lineName = lineName;
        this.licensePlate = licensePlate;
        this.carModel = carModel;
        this.driver = driver;
        this.status = status;
        this.weightControl = weightControl;
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

    public String getLineType() {
        return lineType;
    }

    public void setLineType(String lineType) {
        this.lineType = lineType;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getWeightControl() {
        return weightControl;
    }

    public void setWeightControl(double weightControl) {
        this.weightControl = weightControl;
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
        return "Line{" +
                "id=" + id +
                ", lineType='" + lineType + '\'' +
                ", lineName='" + lineName + '\'' +
                ", licensePlate='" + licensePlate + '\'' +
                ", carModel='" + carModel + '\'' +
                ", driver='" + driver + '\'' +
                ", status=" + status +
                ", weightControl=" + weightControl +
                ", operator='" + operator + '\'' +
                ", oStation='" + oStation + '\'' +
                ", oTime='" + oTime + '\'' +

                '}';
    }
}
