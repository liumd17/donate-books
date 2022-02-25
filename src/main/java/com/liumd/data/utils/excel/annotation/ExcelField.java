package com.liumd.data.utils.excel.annotation;

import org.apache.poi.ss.usermodel.HorizontalAlignment;

import java.lang.annotation.*;

/**
 * @author liumd
 * @date 2022/2/7 9:14
 */

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface ExcelField {

    /**
     * 列名称
     *
     * @return String
     */
    String name() default "";

    /**
     * 列宽 (大于0时生效; 如果不指定列宽，将会自适应调整宽度；)
     *
     * @return int
     */
    int width() default 0;

    /**
     * 水平对齐方式
     *
     * @return HorizontalAlignment
     */
    HorizontalAlignment align() default HorizontalAlignment.LEFT;

    /**
     * 时间格式化，日期类型时生效
     *
     * @return String
     */
    String dateformat() default "yyyy-MM-dd HH:mm:ss";

    /**
     * 输出列表顺序
     * @return
     */
    int index() default Integer.MAX_VALUE;

}
