package com.liumd.data.mapper;

import com.liumd.data.base.BaseRepository;
import com.liumd.data.entity.AdminEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author liumuda
 * @date 2022/2/14 16:03
 */
@Mapper
public interface AdminMapper extends BaseRepository<AdminEntity> {
    AdminEntity selAdminByAccount(String account);
}
