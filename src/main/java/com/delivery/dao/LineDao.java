package com.delivery.dao;

import com.delivery.entity.Line;
import com.delivery.utilentity.FindLine;
import org.apache.ibatis.annotations.Param;

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
     *修改路线
     * @param line line
     * @return boolean
     */
    boolean updateLine(Line line);

    boolean removeLine(Line line);
    /**
     *查询全部路线，分页
     * @return list
     */
    List<Line> selectLine(FindLine findLine);

    int selectLineCount();

    Line selectLineForRemoveStaff(String driver);

    Line selectLineByID(int id);

}
