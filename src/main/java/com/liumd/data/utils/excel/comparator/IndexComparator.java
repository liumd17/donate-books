package com.liumd.data.utils.excel.comparator;



import com.liumd.data.utils.excel.annotation.ExcelField;

import java.lang.reflect.Field;
import java.util.Comparator;

/**
 * @author liumd
 * @date 2022/2/10 11:20
 */

public class IndexComparator implements Comparator<Field> {

    @Override
    public int compare(Field o1, Field o2) {

        ExcelField excelField1 = o1.getAnnotation(ExcelField.class);
        ExcelField excelField2 = o2.getAnnotation(ExcelField.class);

        Integer index1 = excelField1.index();
        Integer index2 = excelField2.index();

        return index1.compareTo(index2);
    }
}
