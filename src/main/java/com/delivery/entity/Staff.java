package com.delivery.entity;

/**
 * Created by LEO15 on 2020/10/18.
 * @author fujianian
 */
public class Staff {
    private int id;
    private String uuid;
    private String name;
    private String telephone;
    private String haspda;
    private String deltag;
    private String station;
    private String standard;
    private Standard s;
    private Line line;
    private Instead instead;


    public Staff() {
    }

    public Staff(String uuid, String name, String telephone, String haspda, String standard) {
        this.uuid = uuid;
        this.name = name;
        this.telephone = telephone;
        this.haspda = haspda;
        this.standard = standard;
    }

    public Staff(int id, String uuid, String name, String telephone, String haspda, String deltag, String station, String standard) {
        this.id = id;
        this.uuid = uuid;
        this.name = name;
        this.telephone = telephone;
        this.haspda = haspda;
        this.deltag = deltag;
        this.station = station;
        this.standard = standard;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getHaspda() {
        return haspda;
    }

    public void setHaspda(String haspda) {
        this.haspda = haspda;
    }

    public String getDeltag() {
        return deltag;
    }

    public void setDeltag(String deltag) {
        this.deltag = deltag;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public Standard getS() {
        return s;
    }

    public void setS(Standard s) {
        this.s = s;
    }

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }

    public Instead getInstead() {
        return instead;
    }

    public void setInstead(Instead instead) {
        this.instead = instead;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                ", telephone='" + telephone + '\'' +
                ", haspda='" + haspda + '\'' +
                ", deltag='" + deltag + '\'' +
                ", station='" + station + '\'' +
                ", standard='" + standard + '\'' +
                ", s=" + s +
                ", line=" + line +
                ", instead=" + instead +
                '}';
    }
}
