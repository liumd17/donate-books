package com.liumd.data.dto;

import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

/**
 * @author liumuda
 * @date 2022/2/14 17:22
 */
@Data
public class OrderDto {

    /**
     * 用户编号
     */
    private Integer userId;

    /**
     * 书籍编号
     */
    private Integer bookId;

    /**
     * 管理员编号
     */
    private Integer adminId;

    /**
     * 收货人昵称
     */
    private String userNickname;

    /**
     * 收货地址
     */
    private String receivingAddress;

    /**
     * 联系电话
     */
    private String mobile;

    /**
     * 书籍名称
     */
    private String bookName;

    /**
     * 订单状态
     */
    private String orderStatus;

    /**
     * 订单日期
     */
    private Date orderDate;

    /**
     * 处理人姓名
     */
    private String adminName;

}
