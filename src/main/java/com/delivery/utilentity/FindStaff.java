package com.delivery.utilentity;

public class FindStaff {
    private String staffNum;
    private String region;
    private String standard;
    private String station;

    @Override
    public String toString() {
        return "findStaff{" +
                "staffNum='" + staffNum + '\'' +
                ", region='" + region + '\'' +
                ", standard='" + standard + '\'' +
                ", station='" + station + '\'' +
                '}';
    }

    public String getStaffNum() {
        return staffNum;
    }

    public void setStaffNum(String staffNum) {
        this.staffNum = staffNum;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }
}
