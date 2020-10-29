package com.delivery.utilentity;

/**
 * Created by LEO15 on 2020/10/29.
 */
public class FindStandard {
    private String standardName;

    public FindStandard() {
    }

    public FindStandard(String standardName) {
        this.standardName = standardName;
    }

    public String getStandardName() {
        return standardName;
    }

    public void setStandardName(String standardName) {
        this.standardName = standardName;
    }

    @Override
    public String toString() {
        return "FindStandard{" +
                "standardName='" + standardName + '\'' +
                '}';
    }
}
