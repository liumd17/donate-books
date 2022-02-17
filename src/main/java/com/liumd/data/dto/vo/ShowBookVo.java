package com.liumd.data.dto.vo;

import com.liumd.data.dto.ResponsePageDto;
import lombok.Data;

import java.util.List;

/**
 * @author liumuda
 * @date 2022/2/17 14:33
 */
@Data
public class ShowBookVo {

    /**
     * 推荐书籍列表
     */
    private List<BookVo> recBooks;

    /**
     * 可选书籍分页列表
     */
    private ResponsePageDto<BookVo> bookPageList;

    public ShowBookVo() {
    }

    public ShowBookVo(List<BookVo> recBooks, ResponsePageDto<BookVo> bookPageList) {
        this.recBooks = recBooks;
        this.bookPageList = bookPageList;
    }
}
