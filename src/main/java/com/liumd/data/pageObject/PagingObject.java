package com.liumd.data.pageObject;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.io.Serializable;
import java.util.List;

/**
 * @author liumuda
 * @date 2022/2/15 17:04
 */
@JsonDeserialize(
        as = SimplePagingObject.class
)
public interface PagingObject<T> extends Iterable<T>, Serializable {
    int getPageNum();

    int getPageSize();

    int getTotalPages();

    long getTotalElements();

    int getNumberOfElements();

    boolean hasPrevious();

    boolean hasNext();

    boolean isFirst();

    boolean isLast();

    boolean hasContent();

    List<T> getContent();
}
