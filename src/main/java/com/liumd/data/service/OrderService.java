package com.liumd.data.service;

import com.liumd.data.dto.OrderDto;
import com.liumd.data.dto.vo.BookOrderVo;

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
}
