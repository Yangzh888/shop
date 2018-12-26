package com.sise.shop.utilis;

import java.util.List;
import java.util.Map;

public class MapRequestVO {
    private Map<String,String> map;

    private  Map<String,List<Map>> listMap;

    public Map<String, List<Map>> getListMap() {
        return listMap;
    }

    public void setListMap(Map<String, List<Map>> listMap) {
        this.listMap = listMap;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }


}
