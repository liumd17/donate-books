package com.liumd.data.mapper;

import com.liumd.data.base.BaseRepository;
import com.liumd.data.entity.BookEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author liumuda
 * @date 2022/2/14 11:24
 */
@Mapper
public interface BookMapper extends BaseRepository<BookEntity> {

    /**
     * 通过书籍名称获取书籍信息
     * @param bookName
     * @return
     */
    BookEntity getBookByBookName(String bookName);

}
