package com.liumd.data.entity;

import com.liumd.data.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author liumuda
 * @date 2022/2/14 17:22
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "bds_order")
public class OrderEntity extends BaseEntity {

    /**
     * 用户编号
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 书籍编号
     */
    @Column(name = "book_id")
    private Integer bookId;

    /**
     * 管理员编号
     */
    @Column(name = "admin_id")
    private Integer adminId;

    /**
     * 收货人昵称
     */
    @Column(name = "user_nickname")
    private String userNickname;

    /**
     * 收货地址
     */
    @Column(name = "receiving_address")
    private String receivingAddress;

    /**
     * 联系电话
     */
    @Column(name = "mobile")
    private String mobile;

    /**
     * 书籍名称
     */
    @Column(name = "book_name")
    private String bookName;

    /**
     * 订单状态
     */
    @Column(name = "order_status")
    private String orderStatus;

    /**
     * 订单日期
     */
    @Column(name = "order_date")
    private String orderDate;

    /**
     * 处理人姓名
     */
    @Column(name = "admin_name")
    private String adminName;

}
