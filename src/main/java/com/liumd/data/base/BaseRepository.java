package com.liumd.data.base;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author liumuda
 * @date 2022/2/16 10:47
 */
public interface BaseRepository<T> extends Mapper<T>, MySqlMapper<T> {
}
