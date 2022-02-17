package com.liumd.data.mapper;

import com.liumd.data.base.BaseRepository;
import com.liumd.data.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author liumuda
 * @date 2022/1/11 14:30
 */
@Mapper
public interface UserMapper extends BaseRepository<UserEntity> {
    UserEntity selUserByMailbox(String mailbox);
}
