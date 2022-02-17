package com.liumd.data.pageObject;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.springframework.util.Assert;

public class SimplePagingObject<T> implements PagingObject<T> {
    private int pageNum;
    private int pageSize;
    private int totalPages;
    private long totalElements;
    private List<T> content;

    @JsonCreator
    public SimplePagingObject(@JsonProperty("content") List<T> content, @JsonProperty("pageNum") int pageNum, @JsonProperty("pageSize") int pageSize, @JsonProperty("totalElements") long totalElements) {
        Assert.isTrue(pageNum > 0, "pageNum must be positive.");
        Assert.isTrue(pageSize > 0, "pageSize must be positive.");
        Assert.isTrue(totalElements >= 0L, "totalElements must net be negative.");
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.totalElements = totalElements;
        this.totalPages = (int)(totalElements / (long)pageSize + (long)(totalElements % (long)pageSize == 0L ? 0 : 1));
        this.content = content == null ? Collections.EMPTY_LIST : content;
    }

    protected void setContent(List<T> content) {
        this.content = content;
    }

    public int getPageNum() {
        return this.pageNum;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public int getTotalPages() {
        return this.totalPages;
    }

    public long getTotalElements() {
        return this.totalElements;
    }

    public int getNumberOfElements() {
        return this.content.size();
    }

    public boolean hasPrevious() {
        return this.pageNum > 1;
    }

    public boolean hasNext() {
        return this.pageNum < this.totalPages;
    }

    public boolean isFirst() {
        return !this.hasPrevious();
    }

    public boolean isLast() {
        return !this.hasNext();
    }

    public boolean hasContent() {
        return !this.content.isEmpty();
    }

    public List<T> getContent() {
        return Collections.unmodifiableList(this.content);
    }

    public Iterator<T> iterator() {
        return this.content.iterator();
    }
}
