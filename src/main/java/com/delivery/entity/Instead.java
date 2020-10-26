package com.delivery.entity;

/**
 * Created by LEO15 on 2020/10/23.
 * @author fujianian
 */
public class Instead {
    private int id;
    private String replacedStaffUuid;
    private String replacedStaffName;
    private String insteadStaffUuid;
    private String insteadStaffName;
    private String startTime;
    private String endTime;
    private int status;
    private String operator;
    private String oStation;
    private String oTime;

    public Instead() {
    }

    public Instead(int id, String replacedStaffUuid, String replacedStaffName, String insteadStaffUuid, String insteadStaffName, String startTime, String endTime, int status, String operator, String oStation, String oTime) {
        this.id = id;
        this.replacedStaffUuid = replacedStaffUuid;
        this.replacedStaffName = replacedStaffName;
        this.insteadStaffUuid = insteadStaffUuid;
        this.insteadStaffName = insteadStaffName;
        this.startTime = startTime;
        this.endTime = endTime;
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

    public String getReplacedStaffUuid() {
        return replacedStaffUuid;
    }

    public void setReplacedStaffUuid(String replacedStaffUuid) {
        this.replacedStaffUuid = replacedStaffUuid;
    }

    public String getReplacedStaffName() {
        return replacedStaffName;
    }

    public void setReplacedStaffName(String replacedStaffName) {
        this.replacedStaffName = replacedStaffName;
    }

    public String getInsteadStaffUuid() {
        return insteadStaffUuid;
    }

    public void setInsteadStaffUuid(String insteadStaffUuid) {
        this.insteadStaffUuid = insteadStaffUuid;
    }

    public String getInsteadStaffName() {
        return insteadStaffName;
    }

    public void setInsteadStaffName(String insteadStaffName) {
        this.insteadStaffName = insteadStaffName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
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
        return "Instead{" +
                "id=" + id +
                ", replacedStaffUuid='" + replacedStaffUuid + '\'' +
                ", replacedStaffName='" + replacedStaffName + '\'' +
                ", insteadStaffUuid='" + insteadStaffUuid + '\'' +
                ", insteadStaffName='" + insteadStaffName + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", status=" + status +
                ", operator='" + operator + '\'' +
                ", oStation='" + oStation + '\'' +
                ", oTime='" + oTime + '\'' +
                '}';
    }
}
