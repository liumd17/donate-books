package com.liumd.data.controller;

import com.liumd.data.dto.BookDto;
import com.liumd.data.dto.ResponsePageDto;
import com.liumd.data.dto.vo.BookVo;
import com.liumd.data.pageObject.Paging;
import com.liumd.data.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liumuda
 * @date 2022/2/14 16:23
 */
@RestController
@RequestMapping("/admin/book")
public class BookManageController {

    @Autowired
    private BookService bookService;

    /**
     * 返回所有书籍分页列表
     * @param bookDto
     * @param paging
     * @return
     */
    @RequestMapping(value = "/pageInfo", method = RequestMethod.GET)
    public ResponsePageDto<BookVo> queryAdmin(BookDto bookDto, Paging paging) {
        return bookService.pageList(bookDto, paging);
    }

    /**
     * 编辑书籍信息
     * @param bookDto
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public BookVo editAdmin(@RequestBody BookDto bookDto) {
        return bookService.updateBook(bookDto);
    }

}
