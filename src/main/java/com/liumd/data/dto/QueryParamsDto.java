package com.liumd.data.dto;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @author liumuda
 * @date 2021/7/12 16:25
 */
public class QueryParamsDto implements Serializable {
    private String current = "1";
    private String pageSize = "10";
    private String sort;
    private String keyString;
    private String keyString2;
    private String keyString3;
    private HashMap<String, Object> searchObject = new HashMap<>();

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getKeyString() {
        return keyString;
    }

    public void setKeyString(String keyString) {
        this.keyString = keyString;
    }

    public String getKeyString2() {
        return keyString2;
    }

    public void setKeyString2(String keyString2) {
        this.keyString2 = keyString2;
    }

    public String getKeyString3() {
        return keyString3;
    }

    public void setKeyString3(String keyString3) {
        this.keyString3 = keyString3;
    }

    public HashMap<String, Object> getSearchObject() {
        return searchObject;
    }

    public void setSearchObject(HashMap<String, Object> searchObject) {
        this.searchObject = searchObject;
    }
}
