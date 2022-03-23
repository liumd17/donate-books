package com.liumd.data.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liumd.data.constant.BookFitUserConstant;
import com.liumd.data.constant.Constant;
import com.liumd.data.dto.BookDto;
import com.liumd.data.dto.BookImportDto;
import com.liumd.data.dto.ResponsePageDto;
import com.liumd.data.dto.UserChoiceBookDto;
import com.liumd.data.dto.vo.BookVo;
import com.liumd.data.dto.vo.UserVo;
import com.liumd.data.entity.BookEntity;
import com.liumd.data.mapper.BookMapper;
import com.liumd.data.pageObject.Paging;
import com.liumd.data.service.BookService;
import com.liumd.data.service.UserService;
import com.liumd.data.utils.DataUtil;
import com.liumd.data.utils.exceptionUtil.ServiceException;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author liumuda
 * @date 2022/2/14 11:25
 */
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private UserService userService;

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
    public ResponsePageDto<BookVo> pageList(UserChoiceBookDto userChoiceBookDto, Paging paging) {
        PageHelper.startPage(paging.getPageNum(), paging.getPageSize());
        Example example = new Example(BookEntity.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtil.isNotEmpty(userChoiceBookDto.getBookName())){
            criteria.andLike("bookName", "%" + userChoiceBookDto.getBookName() + "%");
        }
        if (StringUtil.isNotEmpty(userChoiceBookDto.getFitUser())){
            criteria.andLike("FitUser", "%" + userChoiceBookDto.getFitUser() + "%");
        }
        criteria.andGreaterThan("bookAmount", 0);
        example.orderBy("createTime").desc();
        List<BookEntity> result = bookMapper.selectByExample(example);

        PageInfo<BookEntity> pageInfo = new PageInfo<>(bookMapper.selectByExample(example));

        return new ResponsePageDto<>(
                DataUtil.getDataList(pageInfo.getList(), BookVo.class),
                pageInfo.getTotal(), pageInfo.getPageSize(), pageInfo.getPageNum());
    }

    @SneakyThrows
    @Override
    public List<BookVo> getRecBooks(String mailbox) {
        UserVo user = userService.queryUserByMailbox(mailbox);
        if (ObjectUtils.isEmpty(user)){
            throw new ServiceException(Constant.NULL_ERROR, "该用户数据为空!");
        }

        List<BookEntity> recBooks = bookMapper.getRecBooks(user.getId(), selectFitUserByAge(user.getAge()));;
        if (ObjectUtils.isEmpty(recBooks) || recBooks.size() < 3){
            recBooks = bookMapper.getRecBooks(user.getId(), null);
        }

        return DataUtil.getDataList(recBooks, BookVo.class);
    }

    @Transactional
    @Override
    public BookVo updateBook(BookDto bookDto) {
        Integer bookId = bookDto.getId();
        if (ObjectUtils.isEmpty(bookId)){
            throw new ServiceException(Constant.NULL_ERROR, "书籍ID为空!");
        }
        BookEntity bookEntity = bookMapper.selectByPrimaryKey(bookId);
        if (ObjectUtils.isEmpty(bookEntity)){
            throw new ServiceException(Constant.DATA_ERROR, "该书籍不存在!");
        }
        if (StringUtil.isNotEmpty(bookDto.getBookName())){
            bookEntity.setBookName(bookDto.getBookName());
        }
        if (StringUtil.isNotEmpty(bookDto.getBookPicture())){
            bookEntity.setBookPicture(bookDto.getBookPicture());
        }
        if (StringUtil.isNotEmpty(bookDto.getFitUser())){
            bookEntity.setFitUser(bookDto.getFitUser());
        }
        if (!ObjectUtils.isEmpty(bookDto.getBookAmount())){
            bookEntity.setBookAmount(bookDto.getBookAmount());
        }
        bookEntity.setUpdateTime(new Date());
        bookMapper.updateByPrimaryKey(bookEntity);
        BookVo bookVo = new BookVo();
        BeanUtils.copyProperties(bookEntity, bookVo);
        return bookVo;
    }

    @Transactional
    @Override
    public Boolean saveImportBook(List<BookImportDto> bookImportDtos) {
        bookImportDtos.forEach(bookImportDto -> {
            BookEntity bookEntity = bookMapper.getBookByBookName(bookImportDto.getBookName());
            if (ObjectUtils.isEmpty(bookEntity)){
                try {
                    BookEntity importBook = DataUtil.getTransData(bookImportDto, BookEntity.class);
                    importBook.setCreateTime(new Date());
                    bookMapper.insert(importBook);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else {
                if (bookImportDto.getBookAmount() >= 0){
                    bookEntity.setBookAmount(bookEntity.getBookAmount() + bookImportDto.getBookAmount());
                }
                if (StringUtil.isNotEmpty(bookImportDto.getBookPicture())){
                    bookEntity.setBookPicture(bookImportDto.getBookPicture());
                }
                if (StringUtil.isNotEmpty(bookImportDto.getFitUser())){
                    bookEntity.setFitUser(bookImportDto.getFitUser());
                }
                bookEntity.setUpdateTime(new Date());
                bookMapper.updateByPrimaryKey(bookEntity);
            }
        });

        return Boolean.TRUE;
    }

    /**
     * 通过用户年龄分析用户类型
     * @param age
     * @return
     */
    private String selectFitUserByAge(Integer age){
        if (age <= BookFitUserConstant.AGE_PUPIL){
            return BookFitUserConstant.FIT_PUPIL;
        }else if (age <= BookFitUserConstant.AGE_MIDDLE){
            return BookFitUserConstant.FIT_MIDDLE;
        }else if (age <= BookFitUserConstant.AGE_COLLEGE){
            return BookFitUserConstant.FIT_COLLEGE;
        }else {
            return BookFitUserConstant.FIT_ADULT;
        }
    }

}
