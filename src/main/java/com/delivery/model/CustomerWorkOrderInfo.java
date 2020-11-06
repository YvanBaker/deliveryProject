package com.delivery.model;

import com.delivery.entity.CustomerAddress;
import com.delivery.entity.CustomerReceiveAddress;
import com.delivery.entity.CustomerWorkOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Yvan
 * @Description 用户订单信息的实体类
 * @Classname CustomerWorkOrderInfo
 * @Date 2020/11/5 21:33
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerWorkOrderInfo implements Serializable {

    private static final long serialVersionUID = 9054921763768362251L;

    private CustomerWorkOrder customerWorkOrder;

    private CustomerAddress customerAddress;

    private CustomerReceiveAddress customerReceiveAddress;
}
