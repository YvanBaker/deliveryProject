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
     * 添加路线
     * @param line line
     * @return boolean
     */
    boolean addLine(Line line);

    /**
     *修改路线和删除路线
     * @param line line
     * @return boolean
     */
    boolean updateAndRemoveLine(Line line);

    /**
     *查询全部路线
     * @return list
     */
    List<Line> selectLine();

}
