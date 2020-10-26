package com.delivery.util;

import java.util.List;

public class PageUtil {
    //条件查询对象，包装查询条件
    private int total;//总记录数
    private List rows;//当前页需要展示的数据集合

    @Override
    public String toString() {
        return "PageUtil{" +
                "total=" + total +
                ", rows=" + rows +
                '}';
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }
}
