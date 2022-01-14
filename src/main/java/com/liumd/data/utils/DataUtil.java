package com.liumd.data.utils;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * entity的list转换成dto的list工具
 * @author liumuda
 * @date 2021/7/13 9:58
 */
public class DataUtil {
    /**
     * 将源列表转换成新的列表
     * @param source 源列表
     * @param clazz 新列表类型对应的class类
     * @param <T> 源列表类型
     * @param <E> 新类别类型
     * @return
     * @throws Exception
     */
    public static <T, E> List<E> getDataList(List<T> source, Class<E> clazz) throws Exception {
        List<E> des = new ArrayList<>();
        if (source != null){
            for (T t : source) {
                E e = clazz.newInstance();
                BeanUtils.copyProperties(t, e);
                des.add(e);
            }
        }
        return des;
    }
}
