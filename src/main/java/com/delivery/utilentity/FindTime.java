package com.delivery.utilentity;

/**
 * Created by LEO15 on 2020/11/2.
 */
public class FindTime {

    private String timeName;
    private String station;

    public FindTime() {
    }

    public FindTime(String timeName, String station) {
        this.timeName = timeName;
        this.station = station;
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

    @Override
    public String toString() {
        return "FindTime{" +
                "timeName='" + timeName + '\'' +
                ", station='" + station + '\'' +
                '}';
    }
}
