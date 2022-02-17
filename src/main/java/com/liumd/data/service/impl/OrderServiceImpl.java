package com.liumd.data.service.impl;

import com.liumd.data.dto.OrderDto;
import com.liumd.data.dto.vo.BookOrderVo;
import com.liumd.data.dto.vo.BookVo;
import com.liumd.data.dto.vo.UserVo;
import com.liumd.data.service.BookService;
import com.liumd.data.service.OrderService;
import com.liumd.data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author liumuda
 * @date 2022/2/14 16:04
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @Override
    public BookOrderVo getBookOrder(String mailbox, Integer bookId) {

        UserVo userVo = userService.queryUserByMailbox(mailbox);
        BookVo bookVo = bookService.getBookInfo(bookId);

        return new BookOrderVo(userVo, bookVo);
    }

    @Override
    @Transactional
    public Boolean insertOrder(OrderDto orderDto) {

        //TODO 创建订单信息
        return null;
    }
}
