package com.delivery.entity;

/**
 * Created by LEO15 on 2020/10/26.
 * @author fujianian
 */

public class TimeManagement {
    private int id;
    private String timeName;
    private String station;
    private String normalWorkTime;
    private String normalOffWorkTime;
    private String weekWorkTime;
    private String weekOffWorkTime;
    private int status;
    private String operator;
    private String oStation;
    private String oTime;

    public TimeManagement() {
    }

    public TimeManagement(int id, String timeName, String station, String normalWorkTime, String normalOffWorkTime, String weekWorkTime, String weekOffWorkTime, int status, String operator, String oStation, String oTime) {
        this.id = id;
        this.timeName = timeName;
        this.station = station;
        this.normalWorkTime = normalWorkTime;
        this.normalOffWorkTime = normalOffWorkTime;
        this.weekWorkTime = weekWorkTime;
        this.weekOffWorkTime = weekOffWorkTime;
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

    public String getTimeName() {
        return timeName;
    }

    public void setTimeName(String timeName) {
        this.timeName = timeName;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getNormalWorkTime() {
        return normalWorkTime;
    }

    public void setNormalWorkTime(String normalWorkTime) {
        this.normalWorkTime = normalWorkTime;
    }

    public String getNormalOffWorkTime() {
        return normalOffWorkTime;
    }

    public void setNormalOffWorkTime(String normalOffWorkTime) {
        this.normalOffWorkTime = normalOffWorkTime;
    }

    public String getWeekWorkTime() {
        return weekWorkTime;
    }

    public void setWeekWorkTime(String weekWorkTime) {
        this.weekWorkTime = weekWorkTime;
    }

    public String getWeekOffWorkTime() {
        return weekOffWorkTime;
    }

    public void setWeekOffWorkTime(String weekOffWorkTime) {
        this.weekOffWorkTime = weekOffWorkTime;
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

    public String getoStation() {
        return oStation;
    }

    public void setoStation(String oStation) {
        this.oStation = oStation;
    }

    public String getoTime() {
        return oTime;
    }

    public void setoTime(String oTime) {
        this.oTime = oTime;
    }

    @Override
    public String toString() {
        return "TimeManagement{" +
                "id=" + id +
                ", timeName='" + timeName + '\'' +
                ", station='" + station + '\'' +
                ", normalWorkTime='" + normalWorkTime + '\'' +
                ", normalOffWorkTime='" + normalOffWorkTime + '\'' +
                ", weekWorkTime='" + weekWorkTime + '\'' +
                ", weekOffWorkTime='" + weekOffWorkTime + '\'' +
                ", status=" + status +
                ", operator='" + operator + '\'' +
                ", oStation='" + oStation + '\'' +
                ", oTime='" + oTime + '\'' +
                '}';
    }
}
