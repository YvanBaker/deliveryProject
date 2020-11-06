package com.delivery.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * generated by Generate POJOs.groovy
 *
 * @author Yvan
 * @Date 2020-11-03 14:43
 */

@Data
@Entity
@Table(name = "staff_order")
public class StaffOrder implements Serializable {

    private static final long serialVersionUID = 6570491523856895229L;

    @Column(name = "id")
    @Id
    @GeneratedValue
    private Integer id;

    /**
     * 工人id
     */
    @Column(name = "staff_id")
    private Integer staffId;

    /**
     * 工单id
     */
    @Column(name = "order_id")
    private String orderId;

    /**
     * 关联地区id
     */
    @Column(name = "area_id")
    private String areaId;


    /**
     * 区域area_id
     */
    @Column(name = "area_id")
    private String areaId;


    /**
     * 状态 1 正在进行 0 完成
     */
    @Column(name = "del")
    private Integer del;



}
