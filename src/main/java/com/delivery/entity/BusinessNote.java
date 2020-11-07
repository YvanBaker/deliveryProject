package com.delivery.entity;

import lombok.Data;

@Data
public class BusinessNote {
    private Integer id;
    private String uuid;
    private String clientNum;
    private String clientName;
    private String clientPhone;
    private String contacts;
    private String conPhone;
    private String address;
    private String arriveCity;
    private String product;
    private String proDate;
    private Integer number;
    private Double weight;
    private String volume;
    private String remark;
    private String singleType;
    private String receiver;
    private Staff staff;
    private String buildTime;
}
