package com.delivery.service;

import com.delivery.entity.CustomerWorkOrder;
import com.delivery.model.CustomerWorkOrderInfo;

import java.util.List;

/**
 * @author Yvan
 * @Description customer work order 的业务逻辑层
 * @Classname CustomerWorkOrderService
 * @Date 2020/11/5 21:21
 */
public interface CustomerWorkOrderService {

    /**
     * 生成订单
     *
     * @param customerWorkOrder 订单
     * @return CustomerWorkOrder
     */
    CustomerWorkOrder generateOrder(CustomerWorkOrder customerWorkOrder);

    /**
     * 查询 订单 信息
     *
     * @param customerId 用户 id
     * @return List<CustomerWorkOrderInfo>
     */
    List<CustomerWorkOrderInfo> queryOrder(int customerId);

    /**
     * 删除 订单
     * @param id id
     * @return boolean
     */
    boolean delOrder(int id);

    /**
     * 完全删除 订单
     * @param id id
     * @return int
     */
    int deleteOrder(int id);

    /**
     * 更新 订单
     * @param order 订单
     * @return boolean
     */
    boolean renewOrder(CustomerWorkOrder order);
}
