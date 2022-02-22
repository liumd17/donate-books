package com.liumd.data.controller;

import com.liumd.data.dto.OrderDto;
import com.liumd.data.dto.OrderPageDto;
import com.liumd.data.dto.ResponsePageDto;
import com.liumd.data.dto.vo.OrderVo;
import com.liumd.data.pageObject.Paging;
import com.liumd.data.service.OrderService;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liumuda
 * @date 2022/2/17 14:46
 */
@RestController
@RequestMapping("/order")
public class OrderManageController {

    @Autowired
    private OrderService orderService;

    /**
     * 获取历史订单列表信息
     * @param orderPageDto
     * @return
     */
    @RequestMapping(value = "/pageInfo", method = RequestMethod.GET)
    public ResponsePageDto<OrderVo> OrderPageInfo(OrderPageDto orderPageDto, Paging paging){
        return orderService.getOrderPageInfo(orderPageDto, paging);
    }

    /**
     * 用户订单取消
     * @param orderDto
     * @return
     */
    @RequestMapping(value = "/cancel", method = RequestMethod.POST)
    public OrderVo cancelOrder(@RequestBody OrderDto orderDto){
        return orderService.cancelOrder(orderDto);
    }

}
