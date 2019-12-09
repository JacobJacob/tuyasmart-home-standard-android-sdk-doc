package com.tuya.smart.android.demo.device;

/**
 * @author xushun
 * @Des:
 * @data 2019-12-06.
 */
public class StandardSchemaBean {
    private String dpCode;
    private String standardType;
    private Object value;

    public String getDpCode() {
        return dpCode;
    }

    public void setDpCode(String dpCode) {
        this.dpCode = dpCode;
    }

    public String getStandardType() {
        return standardType;
    }

    public void setStandardType(String standardType) {
        this.standardType = standardType;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
