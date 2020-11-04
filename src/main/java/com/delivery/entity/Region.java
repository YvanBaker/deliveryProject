package com.delivery.entity;

import lombok.Data;
import lombok.ToString;
import org.junit.Test;

import javax.persistence.Entity;


@Data
@Entity
public class Region {
    private int id;
    private String provinceName;
    private String cityName;
    private String areasName;
    private String areasId;
    public String getName() {
        return provinceName + "-" + cityName + "-" + areasName;
    }

    public Region() {
    }

    public Region(String provinceName, String cityName, String areasName) {
        this.provinceName = provinceName;
        this.cityName = cityName;
        this.areasName = areasName;
    }

    public Region(int id, String provinceName, String cityName, String areasName, String areasId) {
        this.id = id;
        this.provinceName = provinceName;
        this.cityName = cityName;
        this.areasName = areasName;
        this.areasId = areasId;
    }
}
