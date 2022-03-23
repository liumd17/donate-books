package com.liumd.data.dto;

import com.liumd.data.base.BaseEntity;
import com.liumd.data.pageObject.Paging;
import lombok.Data;


/**
 * @author liumuda
 * @date 2022/1/10 11:10
 */
@Data
public class UserChoiceBookDto extends BaseEntity {

    /**
     * 用户邮箱
     */
    private String mailbox;

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
     * 适宜用户
     */
    private String fitUser;

}
