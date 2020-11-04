package com.delivery.utilentity;

import com.delivery.entity.Region;

/**
 * @author wenJ
 * @Description 展示分区
 * @Classname showSubarea
 * @Date 2020/10/30 19:04
 */
public class ShowSubarea {
    private int page;
    private int rows;
    private Region region;
    private String addresskey;

    /**
     * 是否模糊查询
     * @return
     */
    public boolean isDim() {
        if (region == null && (addresskey == null || addresskey == "")) {
            return false;
        }else {
            return true;
        }
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        /*if (region.getProvinceName() != null && region.getProvinceName() != "") {
            region.setProvinceName("%"+region.getProvinceName()+"%");
        }
        if (region.getCityName() != null && region.getCityName() != "") {
            region.setCityName("%"+region.getCityName()+"%");
        }
        if (region.getAreasName() != null && region.getAreasName() != "") {
            region.setAreasName("%"+region.getAreasName()+"%");
        }*/
            this.region = region;
        }

    public String getAddresskey() {
        return addresskey;
    }

    public void setAddresskey(String addresskey) {
        /*if (addresskey != null && addresskey != "") {
            addresskey = "%" + addresskey + "%";
        }*/
        this.addresskey = addresskey;
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

    @Override
    public String toString() {
        return "ShowSubarea{" +
                "page=" + page +
                ", rows=" + rows +
                ", region=" + region +
                ", addresskey='" + addresskey + '\'' +
                '}';
    }
}
