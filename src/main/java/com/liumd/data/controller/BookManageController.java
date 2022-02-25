package com.liumd.data.controller;

import com.liumd.data.dto.BookDto;
import com.liumd.data.dto.BookImportDto;
import com.liumd.data.dto.ResponsePageDto;
import com.liumd.data.dto.vo.BookVo;
import com.liumd.data.pageObject.Paging;
import com.liumd.data.service.BookService;
import com.liumd.data.utils.excel.ExcelImportUtil;
import com.liumd.data.utils.exceptionUtil.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

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

    /**
     *
     * @param file
     * @return
     */
    @RequestMapping(value = "/import", method = RequestMethod.POST)
    public Boolean bookListImport(@RequestParam MultipartFile file) {
        try {
            List<BookImportDto> bookImportDtos = ExcelImportUtil.importExcel(file.getInputStream(), BookImportDto.class);
            return bookService.saveImportBook(bookImportDtos);
        } catch (IOException e) {
            e.printStackTrace();

        }

        throw new ServiceException("导入数据异常, 请联系相关人员");
    }

}
