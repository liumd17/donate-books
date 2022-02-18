package com.liumd.data.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liumd.data.constant.Constant;
import com.liumd.data.dto.BookDto;
import com.liumd.data.dto.ResponsePageDto;
import com.liumd.data.dto.UserDto;
import com.liumd.data.dto.vo.BookVo;
import com.liumd.data.entity.BookEntity;
import com.liumd.data.mapper.BookMapper;
import com.liumd.data.pageObject.Paging;
import com.liumd.data.service.BookService;
import com.liumd.data.utils.DataUtil;
import com.liumd.data.utils.exceptionUtil.ServiceException;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author liumuda
 * @date 2022/2/14 11:25
 */
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public BookVo getBookInfo(Integer bookId) {
        BookVo bookVo = new BookVo();
        BookEntity bookEntity = bookMapper.selectByPrimaryKey(bookId);
        if (!ObjectUtils.isEmpty(bookEntity)){
            BeanUtils.copyProperties(bookEntity, bookVo);
        }else {
            throw new ServiceException(Constant.DATA_ERROR, "书籍信息不存在!");
        }

        return bookVo;
    }

    @SneakyThrows
    @Override
    public ResponsePageDto<BookVo> pageList(BookDto bookDto, Paging paging) {
        PageHelper.startPage(paging.getPageNum(), paging.getPageSize());
        Example example = new Example(BookEntity.class);
        Example.Criteria criteria = example.createCriteria();
        if (!ObjectUtils.isEmpty(bookDto.getBookName())){
            criteria.andLike("bookName", "%" + bookDto.getBookName() + "%");
        }
        criteria.orGreaterThan("bookAmount", 0);
        example.orderBy("createTime").desc();
        PageInfo<BookEntity> pageInfo = new PageInfo<>(bookMapper.selectByExample(example));

        return new ResponsePageDto<>(
                DataUtil.getDataList(pageInfo.getList(), BookVo.class),
                pageInfo.getTotal(), pageInfo.getPageSize(), pageInfo.getPageNum());
    }

    @Override
    public List<BookVo> getRecBooks(String mailbox) {
        //TODO  获取推进书籍逻辑
        return null;
    }

}
