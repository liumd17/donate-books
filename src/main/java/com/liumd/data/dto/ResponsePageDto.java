package com.liumd.data.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author liumuda
 * @date 2021/7/12 16:28
 */
public class ResponsePageDto<T> implements Serializable {
    private List<T> data;
    private Long total;
    private Integer pageSize;
    private Integer current;
    private Boolean success = true;

    public ResponsePageDto(List<T> data, Long total, Integer pageSize, Integer current) {
        this.data = data;
        this.total = total;
        this.pageSize = pageSize;
        this.current = current;
        this.success = true;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
