package com.liumd.data.dto;

import com.liumd.data.entity.BaseEntity;
import lombok.Data;

import javax.validation.constraints.NotBlank;


/**
 * @author liumuda
 * @date 2022/1/10 11:10
 */
@Data
public class BookDto extends BaseEntity {

    /**
     * 书籍名称
     */
    private String bookName;

    /**
     * 书籍数量
     */
    private Integer bookAmount;

    /**
     * 书籍信息
     */
    private String bookInfo;

    /**
     * 书籍关键字
     */
    private String bookKeyword;
}
