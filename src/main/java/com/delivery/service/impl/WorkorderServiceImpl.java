package com.delivery.service.impl;

import com.delivery.dao.WorkorderDao;
import com.delivery.entity.QpWorkorder;
import com.delivery.entity.Region;
import com.delivery.service.RegionService;
import com.delivery.service.WorkorderService;
import com.delivery.util.CutAddressUtil;
import com.delivery.util.PageUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class WorkorderServiceImpl implements WorkorderService {
    @Resource
    WorkorderDao workorderDao;
    RegionService regionService;

    @Override
    public boolean workorderAdd(QpWorkorder qpWorkorder) {
        return workorderDao.addWorkorder(qpWorkorder);
    }

    /**
     * 查询所有工作单
     * @return
     * @param page
     * @param rows
     */
    @Override
    public PageUtil workorderAll(int page, int rows) {
        List<QpWorkorder> allWorkorder = workorderDao.getAllWorkorder((page-1)*rows,rows);
        int total=workorderDao.getAllWorkorderCount();
        return new PageUtil(total,allWorkorder);
    }

    /**
     * TODO 未完成
     * 工作站内自动分单（派单——转运部分）
     * @param qpWorkorder
     * @return
     */
    @Override
    public boolean autoDistribution(QpWorkorder qpWorkorder) {
        String receiverArea = CutAddressUtil.addressResolution(qpWorkorder.getReceiveraddr()).get("area");
        String receiverCity = CutAddressUtil.addressResolution(qpWorkorder.getReceiveraddr()).get("city");
        String receiverProv = CutAddressUtil.addressResolution(qpWorkorder.getReceiveraddr()).get("prov");
        String sendArea=CutAddressUtil.addressResolution(qpWorkorder.getSenderaddr()).get("area");
        String sendCity=CutAddressUtil.addressResolution(qpWorkorder.getSenderaddr()).get("city");
        String sendProv=CutAddressUtil.addressResolution(qpWorkorder.getSenderaddr()).get("prov");
        /*省/直辖市内（即省内）*/
        if (receiverProv.equals(sendProv)){
            /*省内且同城*/
            if(receiverCity.equals(sendCity)){
                /*省内同城且同区*/
                if(receiverArea.equals(sendArea)){
                    /*分配给和这个区关联的取派员*/
                    Region region=new Region();
                    region.setAreasName(sendArea);
                    int regionid=regionService.selectOneRegion(region).getId();
                    String regionId=Integer.toString(regionid);

                }else{
                 /*省内同城但不同区*/
                }
            }else{
            /*省内不同城*/
            }
        }else{
        /*省/直辖市外（即跨省/直辖市）*/
        }

        return false;
    }
}
