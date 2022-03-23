package com.liumd.data.pageObject;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author liumuda
 * @date 2022/2/15 17:06
 */
public class Paging {
    @ApiModelProperty("分页大小")
    private int pageSize = 5;
    @ApiModelProperty("页码, 默认从1开始")
    private int pageNum = 1;

    public Paging() {
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public int getPageNum() {
        return this.pageNum;
    }

    public void setPageSize(final int pageSize) {
        this.pageSize = pageSize;
    }

    public void setPageNum(final int pageNum) {
        this.pageNum = pageNum;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Paging)) {
            return false;
        } else {
            Paging other = (Paging)o;
            if (!other.canEqual(this)) {
                return false;
            } else if (this.getPageSize() != other.getPageSize()) {
                return false;
            } else {
                return this.getPageNum() == other.getPageNum();
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Paging;
    }

    public int hashCode() {
        int PRIME = 1;
        int result = 1;
        result = result * 59 + this.getPageSize();
        result = result * 59 + this.getPageNum();
        return result;
    }

    public String toString() {
        return "Paging(pageSize=" + this.getPageSize() + ", pageNum=" + this.getPageNum() + ")";
    }
}
