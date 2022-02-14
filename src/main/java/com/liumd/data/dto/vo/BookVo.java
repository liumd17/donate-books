package com.liumd.data.dto.vo;

import com.liumd.data.entity.BaseEntity;
import lombok.Data;


/**
 * @author liumuda
 * @date 2022/1/10 11:10
 */
@Data
public class BookVo extends BaseEntity {

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
    private String bookPicture;

    /**
     * 书籍关键字
     */
    private String bookKeyword;

}
