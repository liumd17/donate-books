package com.liumd.data.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author liumuda
 * @date 2022/1/10 11:10
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@TableName("bds_book")
public class BookEntity extends BaseEntity {

    /**
     * 书籍名称
     */
    @Column(name = "book_name")
    private String bookName;

    /**
     * 书籍数量
     */
    @Column(name = "book_amount")
    private Integer bookAmount;

    /**
     * 书籍信息
     */
    @Column(name = "book_info")
    private String bookInfo;

    /**
     * 书籍关键字
     */
    @Column(name = "book_keyword")
    private String bookKeyword;
}
