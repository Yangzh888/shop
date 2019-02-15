package com.sise.shop.entity;

/**
 * @Author Yangzhenhua
 *  辅助类-存放批发商可选择信息
 * @date 2019/2/15 13:49
 */
public class WholesalerParam {
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    private String value;
    private String label;
}
