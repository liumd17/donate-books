package com.liumd.data.service;

import com.liumd.data.dto.BookDto;
import com.liumd.data.dto.BookImportDto;
import com.liumd.data.dto.ResponsePageDto;
import com.liumd.data.dto.UserDto;
import com.liumd.data.dto.vo.BookVo;
import com.liumd.data.pageObject.Paging;

import java.util.List;

/**
 * @author liumuda
 * @date 2022/2/14 11:25
 */
public interface BookService {

    /**
     * 根据书籍ID获取书籍信息
     * @param bookId
     * @return
     */
    BookVo getBookInfo(Integer bookId);

    /**
     * 可选书籍分页列表
     * @param bookDto
     * @param paging
     * @return
     */
    ResponsePageDto<BookVo> pageList(BookDto bookDto, Paging paging);

    /**
     * 根据用户信息获取推荐书籍列表
     * @param mailbox
     * @return
     */
    List<BookVo> getRecBooks(String mailbox);

    /**
     * 更新书籍信息
     * @param bookDto
     * @return
     */
    BookVo updateBook(BookDto bookDto);

    /**
     * 保存书籍导入信息
     * @param bookImportDtos
     * @return
     */
    Boolean saveImportBook(List<BookImportDto> bookImportDtos);
}
