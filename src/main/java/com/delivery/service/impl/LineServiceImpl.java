package com.delivery.service.impl;

import com.delivery.dao.LineDao;
import com.delivery.entity.Line;
import com.delivery.service.LineService;
import com.delivery.util.PageUtil;
import com.delivery.utilentity.FindLine;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LEO15 on 2020/10/30.
 */
@Service
public class LineServiceImpl implements LineService {
@Resource
private LineDao lineDao;

    @Override
    public PageUtil selectLine(FindLine findLine) {
        List<Line> lineList=lineDao.selectLine(findLine);
        int i=lineDao.selectLineCount();
        return new PageUtil(i,lineList);
    }

    @Override
    public int lineCount() {
        return lineDao.selectLineCount();
    }

    @Override
    public boolean addLine(Line line) {
        return lineDao.addLine(line);
    }

    @Override
    public boolean updateLine(Line line) {
        return lineDao.updateLine(line);
    }
    @Override
    public boolean removeLine(Line line) {
        return lineDao.removeLine(line);
    }

}
