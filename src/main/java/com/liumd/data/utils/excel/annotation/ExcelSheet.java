package com.liumd.data.utils.excel.annotation;

import org.apache.poi.hssf.util.HSSFColor;

import java.lang.annotation.*;

/**
 * @author liumd
 * @date 2022/2/7 9:15
 */

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface ExcelSheet {

    /**
     * 表名称
     *
     * @return String
     */
    String name() default "Sheet";

    /**
     * 表头/首行的颜色
     *
     * @return HSSFColorPredefined
     */
    HSSFColor.HSSFColorPredefined headColor() default HSSFColor.HSSFColorPredefined.LIGHT_GREEN;

}
