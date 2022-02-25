package com.liumd.data.utils.excel;

import com.liumd.data.utils.excel.annotation.ExcelField;
import com.liumd.data.utils.excel.comparator.IndexComparator;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;


/**
 * @author liumd
 * @date 2022/2/9 17:32
 */

public class ReflectUtil {

    public static List<Field> getFieldsNotStatic(Class<?> clazz) {
        List<Field> fields = new ArrayList<>();
        getFieldsNotStatic(fields, clazz);
        return fields;
    }

    public static List<Field> getFieldsNotStaticByIndex(Class<?> clazz) {
        List<Field> fields = new ArrayList<>();
        getFieldsNotStatic(fields, clazz);
        fields.sort(new IndexComparator());
        return fields;
    }

    private static void getFieldsNotStatic(List<Field> list, Class<?> clazz) {
        if (clazz.getDeclaredFields() != null && clazz.getDeclaredFields().length > 0) {
            for (Field field: clazz.getDeclaredFields()) {
                if (Modifier.isStatic(field.getModifiers())) {
                    continue;
                }
                if(!field.isAnnotationPresent(ExcelField.class)) {
                    continue;
                }
                list.add(field);
            }
        }
    }

}
