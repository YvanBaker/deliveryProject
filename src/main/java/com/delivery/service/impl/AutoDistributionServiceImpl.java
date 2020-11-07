package com.delivery.service.impl;

import com.delivery.dao.*;
import com.delivery.entity.*;
import com.delivery.service.AutoDistributionService;
import com.delivery.util.CutAddressUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by LEO15 on 2020/11/4.
 * 自动分单
 */
@Service("autoDistributionService")
public class AutoDistributionServiceImpl implements AutoDistributionService {
    @Resource
    RegionDao regionDao;
    @Resource
    DecidedzoneDao decidedzoneDao;
    @Resource
    StaffOrderDao staffOrderDao;
    @Resource
    CustomerAddressDao customerAddressDao;
    @Resource
    CustomerWorkOrderDao customerWorkOrderDao;
    @Resource
    CustomerReceiveAddressDao customerReceiveAddressDao;

    /**
     * 系统端自动分单——送件
     * 即与staff_Order关联
     * @param businessNote
     * @return
     */
    @Override
    public int autoDistributionForReceiverBusinessNote(BusinessNote businessNote) {
        String sendCity=CutAddressUtil.addressResolution(businessNote.getArriveCity()).get("city");
        String sendArea=CutAddressUtil.addressResolution(businessNote.getArriveCity()).get("areas");
        String orderUuid=businessNote.getUuid();
        return autoDistributeForWenJ(sendCity,sendArea,orderUuid);
    }

    /**
     * 系统端自动分单——送件
     * @param qpWorkorder
     * @return
     */
    @Override
    public int autoDistributionForReceiverQpWork(QpWorkorder qpWorkorder) {
        //截取地址中的区信息，以分配给和这个区关联的取派员
        String sendCity=CutAddressUtil.addressResolution(qpWorkorder.getReceiveraddr()).get("city");
        String sendArea=CutAddressUtil.addressResolution(qpWorkorder.getReceiveraddr()).get("areas");
        String orderUuid=qpWorkorder.getUuid();
       return autoDistributeForWenJ(sendCity,sendArea,orderUuid);
    }

    /**
     * 网页端自动分单——取件
     * @param customerWorkOrder
     * @return
     */
    @Override
    public int autoDistributionForGetGoodsAboutStaffOrder(CustomerWorkOrder customerWorkOrder) {
        int customerAddressId=customerWorkOrder.getPickupAddressId();
        System.out.println("顾客地址Id:"+customerAddressId);
        String orderUuid=customerWorkOrder.getWorkOrderUuid();
        System.out.println("订单号码："+orderUuid);
        CustomerAddress customerAddress=customerAddressDao.selectOneCustomerAddressById(customerAddressId);
        System.out.println("消费者地址"+customerAddress);
        //获得区号，通过区号获得newRegion中的id
        String areaId=customerAddress.getAreaId();
        return autoDistributionForYangF(areaId, orderUuid);
    }

    /**
     * 网页端自动分单——送件
     * @param staffOrder
     * @return
     */
    @Override
    public int autoDistributionForSendGoodsAboutChangeStaffOrder(StaffOrder staffOrder) {
        //获得订单送达地址
        int OrderId=staffOrder.getId();
        String orderUuid=staffOrder.getOrderId();
        CustomerWorkOrder customerWorkOrder=customerWorkOrderDao.selectOrderByUUID(orderUuid);
        int receiveId=customerWorkOrder.getReceiveAddressId();
        CustomerReceiveAddress customerReceiveAddress=customerReceiveAddressDao.selectAddressesById(receiveId);
        //获得areaId
        String areaId=customerReceiveAddress.getReceiveAreaId();
        System.out.println("收货地址id"+areaId);
        Region region=regionDao.selectOneRegionByAreaId(areaId);
        if (region==null){
            staffOrder.setAreaId("wrong");
            staffOrder.setStaffId(0);
            staffOrder.setDel(-11);
            staffOrderDao.updateOrder(staffOrder);
            return -1;
        }
        String regionId=Integer.toString(region.getId());
        List<Decidedzone> staffList=decidedzoneDao.selectOneDecidedzone(regionId);
        if (staffList.size()<=0){
            staffOrder.setAreaId(areaId);
            staffOrder.setStaffId(0);
            staffOrder.setDel(-11);
            staffOrderDao.updateOrder(staffOrder);
            return -2;
        }
        int choiceStaff=(int) (Math.random() * staffList.size());
        int staffId=Integer.parseInt(staffList.get(choiceStaff).getStaffId());
        StaffOrder staffOrder1=new StaffOrder();
        staffOrder1.setStaffId(staffId);
        staffOrder1.setId(OrderId);
        staffOrder1.setAreaId(areaId);
        staffOrder1.setDel(1);
        return staffOrderDao.updateOrder(staffOrder1);
    }

    /**
     * 手动入单的重复逻辑
     * @param sendCity
     * @param sendArea
     * @param OrderUuid
     * @return
     */
    private int  autoDistributeForWenJ(String sendCity,String sendArea,String OrderUuid){
        if(sendCity.equals("北京市")||sendCity.equals("上海市")||sendCity.equals("天津市")||sendCity.equals("重庆市")){
            sendCity="市辖区";
        }
        StaffOrder staffOrder=new StaffOrder();
        Region region=new Region();
        region.setCityName(sendCity);
        region.setAreasName(sendArea);
        //获得newRegion表中的相关区的id 不是areaId
        Region region1=regionDao.selectOneRegion(region);
        if (null==region1){
            //区未找到
            staffOrder.setOrderId(OrderUuid);
            staffOrder.setDel(-11);
            staffOrderDao.insertOrder(staffOrder);
            return -1;
        }
        String areaId=region1.getAreasId();
        int regionid=region1.getId();
        String regionId=Integer.toString(regionid);
        //查询与此区关联的员工并且随机分配，获取员工id
        List<Decidedzone> staffList=decidedzoneDao.selectOneDecidedzone(regionId);
        if (staffList.size()<=0){
            //区内未查询到取派员
            staffOrder.setOrderId(OrderUuid);
            staffOrder.setAreaId(areaId);
            staffOrder.setDel(-11);
            staffOrderDao.insertOrder(staffOrder);
            return -2;
        }
        int choiceStaff=(int) (Math.random()* (staffList.size()));
        int staffId=Integer.parseInt(staffList.get(choiceStaff).getStaffId());
        //将地区id与员工id添加入staff_Order表中
        staffOrder.setOrderId(OrderUuid);
        staffOrder.setStaffId(staffId);
        staffOrder.setAreaId(areaId);
        staffOrder.setDel(1);
        return staffOrderDao.insertOrder(staffOrder);
    }

    private int autoDistributionForYangF(String areaId,String OrderUuid){
        StaffOrder staffOrder=new StaffOrder();
        Region region=regionDao.selectOneRegionByAreaId(areaId);
        if (region==null){
            staffOrder.setOrderId(OrderUuid);
            staffOrder.setDel(-10);
            staffOrderDao.insertOrder(staffOrder);
            return -1;
        }
        int regionid1=region.getId();
        String regionid=Integer.toString(regionid1);
        //在decidezone表中查询关联员工并获取员工id;
        List<Decidedzone> staffList=decidedzoneDao.selectOneDecidedzone(regionid);
        if (staffList.size()<=0){
            staffOrder.setOrderId(OrderUuid);
            staffOrder.setAreaId(areaId);
            staffOrder.setDel(-10);
            staffOrderDao.insertOrder(staffOrder);
            return -2;
        }
        int choiceStaff=(int) (Math.random() * (staffList.size()));
        int staffId=Integer.parseInt(staffList.get(choiceStaff).getStaffId());
        //添加到staff_Order表中
        staffOrder.setStaffId(staffId);
        staffOrder.setOrderId(OrderUuid);
        staffOrder.setAreaId(areaId);
        staffOrder.setDel(0);
        return staffOrderDao.insertOrder(staffOrder);
    }
}


