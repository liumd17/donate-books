package com.liumd.data.dto;

import lombok.Data;

/**
 * @author liumuda
 * @date 2022/2/14 17:22
 */
@Data
public class OrderPageDto {

    /**
     * 用户ID
     */
    private Integer userId;

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
     * 订单开始日期  yyyy-MM
     */
    private String orderStartDate;

    /**
     * 订单结束日期  yyyy-MM
     */
    private String orderEndDate;

}
