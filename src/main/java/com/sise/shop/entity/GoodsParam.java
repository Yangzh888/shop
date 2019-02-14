package com.sise.shop.entity;

/**
 * 辅助类-存放商品可选择信息
 */
public class GoodsParam {
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
