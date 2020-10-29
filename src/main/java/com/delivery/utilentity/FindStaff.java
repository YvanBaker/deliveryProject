package com.delivery.utilentity;

public class FindStaff {
    private int page;//当前页
    private int rows;//记录条数
    private String staffNum; //uuid
    private String region;//定区
    private String standard;//标准
    private String station;//所属单位

    public boolean isNull() {
        if (("".equals(staffNum) || null == staffNum)&&("".equals(region) || null == region)&&("".equals(standard) || null == standard)&&("".equals(station) || null == station)) {
            return true;
        }else {
            return false;
        }
    }

    @Override

    public String toString() {
        return "findStaff{" +
                "staffNum='" + staffNum + '\'' +
                ", region='" + region + '\'' +
                ", standard='" + standard + '\'' +
                ", station='" + station + '\'' +
                '}';
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

    public String getStaffNum() {
        return staffNum;
    }

    public void setStaffNum(String staffNum) {
        if ((!"".equals(staffNum) && null != staffNum)) {
            staffNum = "%" + staffNum + "%";
        }
        this.staffNum = staffNum;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        if ((!"".equals(region) && null != region)) {
            region = "%" + region + "%";
        }
        this.region = region;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        if ((!"".equals(standard) && null != standard)) {
            standard = "%" + standard + "%";
        }
        this.standard = standard;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }
}
