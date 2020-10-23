package com.delivery.dao;

import com.delivery.entity.Line;

import java.util.List;

/**
 * @author fujianian
 * @Description line 的 dao 层
 * @Classname LineDao
 * @Date 2020/10/22 14:45
 */
public interface LineDao {

    /**
     * @param line line
     * @return boolean
     */
    boolean addLine(Line line);

    /**
     *
     * @param line line
     * @return boolean
     */
    boolean updateAndRemoveLine(Line line);

    /**
     *
     * @return list
     */
    List<Line> selectLine();

}
