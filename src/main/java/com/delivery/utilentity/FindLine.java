package com.delivery.utilentity;

/**
 * Created by LEO15 on 2020/10/30.
 */
public class FindLine {
    private int page;//当前页
    private int rows;//记录条数
    private String lineType; //uuid
    private String driver;//定区
    private String weightControl;//标准

    public FindLine() {
    }

    public FindLine(int page, int rows, String lineType, String driver, String weightControl) {
        this.page = page;
        this.rows = rows;
        this.lineType = lineType;
        this.driver = driver;
        this.weightControl = weightControl;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public String getLineType() {
        return lineType;
    }

    public void setLineType(String lineType) {
        this.lineType = lineType;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getWeightControl() {
        return weightControl;
    }

    public void setWeightControl(String weightControl) {
        this.weightControl = weightControl;
    }

    public boolean isNull(){
        if (("".equals(lineType)||null==lineType)&&("".equals(driver)||null==driver)&&("".equals(weightControl)||null==weightControl)){
            return true;
        }else{
            return false;
        }


    }
}
