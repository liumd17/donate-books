package com.liumd.data.dto.vo;

import lombok.Data;

/**
 * @author liumuda
 * @date 2022/2/17 13:42
 */
@Data
public class BookOrderVo {

    /**
     * 用户编号
     */
    private Integer userId;

    /**
     * 书籍编号
     */
    private Integer bookId;

    /**
     * 收货人昵称
     */
    private String userNickname;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 收货地址
     */
    private String receivingAddress;

    /**
     * 书籍名称
     */
    private String bookName;

    /**
     * 书籍图片
     */
    private String bookPicture;


    public BookOrderVo() {
    }

    public BookOrderVo(UserVo userVo, BookVo bookVo){
        this.userId = userVo.getId();
        this.bookId = bookVo.getId();
        this.userNickname = userVo.getNickname();
        this.mobile = userVo.getMobile();
        this.receivingAddress = userVo.getReceivingAddress();
        this.bookName = bookVo.getBookName();
        this.bookPicture = bookVo.getBookPicture();
    }

}
