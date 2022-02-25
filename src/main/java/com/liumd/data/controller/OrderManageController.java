package com.liumd.data.controller;

import com.liumd.data.constant.Constant;
import com.liumd.data.dto.OrderPageDto;
import com.liumd.data.dto.ResponsePageDto;
import com.liumd.data.dto.vo.OrderVo;
import com.liumd.data.pageObject.Paging;
import com.liumd.data.service.OrderService;
import com.liumd.data.utils.exceptionUtil.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liumuda
 * @date 2022/2/24 11:07
 */
@RestController
@RequestMapping("/order/mange")
public class OrderManageController {

    @Autowired
    private OrderService orderService;

    /**
     * 获取所有订单信息
     * @param orderPageDto
     * @param paging
     * @return
     */
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponsePageDto<OrderVo> pageList(OrderPageDto orderPageDto, Paging paging){
        return orderService.getAllOrderPageInfo(orderPageDto, paging);
    }

    /**
     * 管理员确认支付
     * @param orderId
     * @param adminId
     * @return
     */
    @RequestMapping(value = "/confirm/pay", method = RequestMethod.POST)
    public OrderVo adminConfirmPay(@RequestParam Integer orderId, @RequestParam Integer adminId) {
        return orderService.adminConfirmPay(orderId, adminId);
    }

    /**
     * 发货成功通知按钮
     * @param orderId
     * @return
     */
    @RequestMapping(value = "/deliveryMail")
    public Boolean deliveryMail(@RequestParam Integer orderId){
        if (orderService.sendDeliveryMail(orderId)){
            return Boolean.TRUE;
        }else {
            throw new ServiceException(Constant.DATA_ERROR, "邮件发送失败!");
        }
    }

}
