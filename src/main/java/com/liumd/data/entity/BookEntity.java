package com.liumd.data.entity;

import com.liumd.data.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author liumuda
 * @date 2022/1/10 11:10
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "bds_book")
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
     * 书籍图片
     */
    @Column(name = "book_picture")
    private String bookPicture;

    /**
     * 适宜用户
     */
    @Column(name = "fit_user")
    private String fitUser;
}
