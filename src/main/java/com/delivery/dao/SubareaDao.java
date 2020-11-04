package com.delivery.dao;

import com.delivery.entity.Subarea;
import com.delivery.utilentity.ShowSubarea;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wenJ
 * @Description
 * @Classname SubareaDao
 * @Date 2020/10/30 16:16
 */
public interface SubareaDao {

    boolean insertSubarea(Subarea subarea);

    List<Subarea> getSubareaLimit(ShowSubarea showSubarea);

    int getSubareaTotal();

    List<Subarea> getSubareaLimitDim( ShowSubarea showSubarea);

    int getSubareaTotalDim(ShowSubarea showSubarea);

    List<Subarea> getSubareaAll();

}
