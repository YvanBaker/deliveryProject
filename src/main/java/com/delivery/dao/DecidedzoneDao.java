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

    List<Decidedzone> getDecidedZoneAll();

    List<Decidedzone> getDecidedZonelimit(int page, int rows);

    int DecidedzoneCount();

//    Decidedzone getDecidedZone(String deciname);

    List<Decidedzone> getDecidedZonelimitDim(@Param("page") int page,@Param("rows") int rows,@Param("id") int id,@Param("station") String station);

    int getDecidedZoneConutDim(@Param("id") int id,@Param("station") String station);

    boolean delectDecidedzone(@Param("id") int id);

    boolean addDecidedzonePuls(@Param("areaId") String areaId,@Param("staffId") String staffId,@Param("decidedName") String decidedName);


    Decidedzone getDecidedZone(String deciname);

    /**
     * 查询单条数据,用于自动分单
     * @param regionId
     * @return
     */
    List<Decidedzone> selectOneDecidedzone(String regionId);

    boolean updateDecidedZone(@Param("areaId") String areaId,@Param("staffId") String staffId,@Param("decidedName") String decidedName,@Param("id") int id);

    Decidedzone getDecidedZoneById(@Param("id") int id);

}
