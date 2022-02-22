package com.liumd.data.service;

import com.liumd.data.dto.OrderDto;
import com.liumd.data.dto.OrderPageDto;
import com.liumd.data.dto.ResponsePageDto;
import com.liumd.data.dto.vo.BookOrderVo;
import com.liumd.data.dto.vo.OrderVo;
import com.liumd.data.pageObject.Paging;

/**
 * @author liumuda
 * @date 2022/2/14 16:04
 */
public interface OrderService {

    /**
     * 根据用户ID和书籍ID形成订单信息
     * @param mailbox
     * @param bookId
     * @return
     */
    BookOrderVo getBookOrder(String mailbox, Integer bookId);

    /**
     * 创建订单信息
     * @param orderDto
     * @return
     */
    Boolean insertOrder(OrderDto orderDto);

    /**
     * 获取用户所有订单信息
     * @param orderPageDto
     * @param paging
     * @return
     */
    ResponsePageDto<OrderVo> getOrderPageInfo(OrderPageDto orderPageDto, Paging paging);

    /**
     * 取消订单
     * @param orderDto
     * @return
     */
    OrderVo cancelOrder(OrderDto orderDto);
}
