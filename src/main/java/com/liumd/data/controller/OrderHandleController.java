package com.liumd.data.controller;

import com.liumd.data.dto.OrderDto;
import com.liumd.data.dto.OrderPageDto;
import com.liumd.data.dto.ResponsePageDto;
import com.liumd.data.dto.vo.OrderVo;
import com.liumd.data.pageObject.Paging;
import com.liumd.data.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author liumuda
 * @date 2022/2/17 14:46
 */
@RestController
@RequestMapping("/user/order")
public class OrderHandleController {

    @Autowired
    private OrderService orderService;

    /**
     * 获取用户历史订单列表信息
     * @param orderPageDto
     * @return
     */
    @RequestMapping(value = "/pageInfo", method = RequestMethod.GET)
    public ResponsePageDto<OrderVo> OrderPageInfo(OrderPageDto orderPageDto, Paging paging){
        return orderService.getUserOrderPageInfo(orderPageDto, paging);
    }

    /**
     * 修改用户订单取消
     * @param orderDto
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public OrderVo editOrder(@RequestBody OrderDto orderDto){
        return orderService.editOrder(orderDto);
    }

    /**
     * 用户支付待确认
     * @param orderId
     * @return
     */
    @RequestMapping(value = "/pay", method = RequestMethod.POST)
    public OrderVo userPay(@RequestParam Integer orderId) {
        return orderService.userPay(orderId);
    }

}
