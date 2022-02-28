package com.liumd.data.dto;

import com.liumd.data.base.BaseEntity;
import com.liumd.data.utils.excel.annotation.ExcelField;
import com.liumd.data.utils.excel.annotation.ExcelSheet;
import lombok.Data;


/**
 * @author liumuda
 * @date 2022/1/10 11:10
 */
@Data
@ExcelSheet(name = "Sheet1")
public class BookImportDto extends BaseEntity {

    /**
     * 书籍名称
     */
    @ExcelField(name = "书籍名称")
    private String bookName;

    /**
     * 书籍数量
     */
    @ExcelField(name = "书籍数量")
    private Integer bookAmount;

    /**
     * 书籍信息
     */
    @ExcelField(name = "书籍信息")
    private String bookPicture;

    /**
     * 适宜用户
     */
    @ExcelField(name = "适宜用户")
    private String fitUser;
}
