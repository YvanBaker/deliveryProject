package com.delivery.dao;

import com.delivery.entity.Decidedzone;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wenJ
 * @Description
 * @Classname DecidedzoneDao
 * @Date 2020/11/2 14:50
 */
@Repository
public interface DecidedzoneDao {
    boolean addDecidedzone(Decidedzone decidedzone);

    List<Decidedzone> getDecidedZoneAll();

    List<Decidedzone> getDecidedZonelimit(int page, int rows);

    int DecidedzoneCount();

    boolean addDecidedzonePuls(@Param("areaId") String areaId,@Param("staffId") String staffId,@Param("decidedName") String decidedName);

    Decidedzone getDecidedZone(String deciname);
}
