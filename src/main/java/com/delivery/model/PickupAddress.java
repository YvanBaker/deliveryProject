package com.delivery.model;

import lombok.Data;

/**
 * @author Yvan
 * @Description 取东西实体类
 * @Classname PickupAddress
 * @Date 2020/11/6 15:09
 */
@Data
public class PickupAddress {

    private Integer id;
    private String name;
    private String cityName;
    private String areasName;
    private String provinceName;
    private String cityId;
    private String areaId;
    private String provinceId;
    private String addressDetail;
    private Integer userId;
    private Integer del;
    private String phone;
}
