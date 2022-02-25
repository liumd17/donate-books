package com.liumd.data.utils.excel.entity;

import lombok.Data;

import java.util.List;

/**
 * @author liumd
 * @date 2022/5/13 15:10
 */

@Data
public class ClassListEntity {

    /**
     * list中泛型的class类型
     */
    private Class<?> clazz;

    /**
     * 需要导出的数据
     */
    private List<?> list;
}
