package com.delivery.service;

import com.delivery.entity.Line;
import com.delivery.util.PageUtil;
import com.delivery.utilentity.FindLine;

/**
 * Created by LEO15 on 2020/10/30.
 */
public interface LineService {

    PageUtil selectLine(FindLine findLine);

    int lineCount();

    boolean addLine(Line line);

    boolean updateLine(Line line);
    boolean removeLine(Line line);

}
