package com.liumd.data.controller;

import com.liumd.data.dto.BookDto;
import com.liumd.data.dto.OrderDto;
import com.liumd.data.dto.ResponsePageDto;
import com.liumd.data.dto.UserDto;
import com.liumd.data.dto.vo.BookOrderVo;
import com.liumd.data.dto.vo.BookVo;
import com.liumd.data.dto.vo.ShowBookVo;
import com.liumd.data.pageObject.Paging;
import com.liumd.data.service.BookService;
import com.liumd.data.service.OrderService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author liumuda
 * @date 2022/2/14 11:18
 */
@RestController
@RequestMapping("/choice")
public class ChoiceBookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private OrderService orderService;

    /**
     * 获取书籍信息
     * @return
     */
    @RequestMapping(value = "/bookInfo", method = RequestMethod.GET)
    public BookVo getBookInfo(@RequestParam Integer bookId){
        return bookService.getBookInfo(bookId);
    }

    /**
     * 获取书籍列表信息
     * @param bookDto
     * @param paging
     * @return
     */
    @SneakyThrows
    @RequestMapping(value = "/homePage", method = RequestMethod.GET)
    public ShowBookVo bookPage(UserDto userDto, BookDto bookDto, Paging paging) {
        List<BookVo> recBooks = bookService.getRecBooks(userDto);
        ResponsePageDto<BookVo> bookPageList = bookService.pageList(bookDto, paging);
        ShowBookVo showBookVo = new ShowBookVo();
        if (!ObjectUtils.isEmpty(recBooks)){
            showBookVo.setRecBooks(recBooks);
        }
        if (!ObjectUtils.isEmpty(bookPageList)){
            showBookVo.setBookPageList(bookPageList);
        }
        return showBookVo;
    }

    /**
     * 选择书籍查看订单信息
     * @param mailbox
     * @param bookId
     * @return
     */
    @RequestMapping(value = "/setOrder", method = RequestMethod.GET)
    public BookOrderVo getBookOrder(@RequestParam String mailbox, @RequestParam Integer bookId) {
        return orderService.getBookOrder(mailbox, bookId);
    }

    /**
     * 确认订单
     * @param orderDto
     * @return
     */
    @RequestMapping(value = "/confirm", method = RequestMethod.POST)
    public Boolean confirmOrder(@RequestBody OrderDto orderDto) {
        return orderService.insertOrder(orderDto);
    }

}
