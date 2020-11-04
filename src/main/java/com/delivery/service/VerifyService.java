package com.delivery.service;

import com.delivery.entity.Decidedzone;

/**
 * @author wenJ
 * @Description 验证service
 * @Classname VerifyService
 * @Date 2020/11/3 17:07
 */
public interface VerifyService {
    /**
     * 验证区域名
     *
     * @param deciname
     * @return
     */
    Decidedzone getDecidedZone(String deciname);
}
